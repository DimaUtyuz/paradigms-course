(defn vector-equals? [elements]
  (and (every? vector? elements)
       (apply == (mapv count elements))))
(defn vector-of-num? [elements]
  (and (vector? elements)
       (every? number? elements)))
(defn matrix-of-num? [elements]
  (and (every? vector-of-num? elements)
       (vector-equals? elements)))
(defn matrix-equals? [elements]
  (and (every? vector? elements)
       (every? matrix-of-num? elements)
       (apply == (mapv count elements))
       (apply == (mapv #(count (first %)) elements))))

(defn operation-on-elements [f funCheck]
  (fn [& elements]
    {:pre [(funCheck elements)]}
    (apply mapv f elements)))

(def v+ (operation-on-elements + matrix-of-num?))
(def v- (operation-on-elements - matrix-of-num?))
(def v* (operation-on-elements * matrix-of-num?))
(def vd (operation-on-elements / matrix-of-num?))
(defn scalar [& vectors]
  {:pre [(matrix-of-num? vectors)]}
  (apply + (apply v* vectors)))
(defn vect-bin [v1 v2]
  (letfn [(cross [a b]
            (- (* (nth v1 a) (nth v2 b)) (* (nth v1 b) (nth v2 a))))]
    (vector (cross 1 2) (cross 2 0) (cross 0 1))))
(defn vect [& vectors]
  {:pre [(and
           (every? vector-of-num? vectors)
           (apply == 3 (mapv count vectors)))]}
  (reduce vect-bin vectors))
(defn v*s [v & scalars]
  {:pre [(and (vector-of-num? v) (every? number? scalars))]}
  (let [mul (apply * scalars)]
    (mapv #(* % mul) v)))

(def m+ (operation-on-elements v+ matrix-equals?))
(def m- (operation-on-elements v- matrix-equals?))
(def m* (operation-on-elements v* matrix-equals?))
(def md (operation-on-elements vd matrix-equals?))
(defn m*s [matrix & scalars]
  {:pre [(and (matrix-of-num? matrix)
              (every? number? scalars))]}
  (let [mul (apply * scalars)]
    (mapv #(v*s % mul) matrix)))
(defn m*v [matrix vector]
  {:pre [(and (matrix-of-num? matrix)
              (vector-of-num? vector)
              (== (count (first matrix)) (count vector)))]}
  (mapv #(apply + (v* % vector)) matrix))
(defn transpose [matrix]
  {:pre [(matrix-of-num? matrix)]}
  (apply mapv vector matrix))
(defn m*m2 [m1 m2]
  {:pre [(== (count (first m1)) (count m2))]}
  (let [m2 (transpose m2)]
    (transpose (mapv #(m*v m1 %) m2))))
(defn m*m [& matrixes]
  {:pre [(every? matrix-of-num? matrixes)]}
  (reduce m*m2 matrixes))

(defn shape [tensor]
  (cond
    (number? tensor) []
    (vector? tensor) (let [sh (shape (first tensor))]
                       (if (apply = sh (mapv shape (rest tensor)))
                         (cons (count tensor) sh)))))
(defn tensor-of-num? [elements]
  (not (nil? (shape elements))))

(defn broadcasting? [l1 l2]
  (let [len1 (count l1)
        len2 (count l2)]
    (cond
      (or (== 0 len2) (== 0 len1)) true
      (== (first l1) (first l2)) (broadcasting? (rest l1) (rest l2))
      :else false)))

(defn copy-tensor [list copy-diff element]
  (cond
    (> (count list) copy-diff) (mapv
                          #(copy-tensor (rest list) copy-diff %)
                          element)
    (empty? list) element
    :else (mapv
            #(copy-tensor (rest list) (- copy-diff 1) %)
            (repeat (first list) element))
    ))

(defn broadcast [t1 t2]
  {:pre [(broadcasting? (shape t1) (shape t2))]}
  (let [l1 (shape t1)
        l2 (shape t2)
        len1 (count l1)
        len2 (count l2)]
    (cond
      (== len1 len2) (vector t1 t2)
      (> len1 len2) (vector
                      t1
                      (copy-tensor l1 (- len1 len2) t2))
      :else (vector
              (copy-tensor l2 (- len2 len1) t1)
              t2))))

(defn operation-on-tensor [f unary]
  (letfn [(rOp [& elements]
            (if (every? number? elements)
              (apply f elements)
              (apply mapv rOp elements)))]
    (fn [& elements]
      {:pre [(every? tensor-of-num? elements)]}
      (if (== 1 (count elements))
         (reduce #(apply rOp (broadcast %1 %2)) unary elements)
         (reduce #(apply rOp (broadcast %1 %2)) elements)))))

(def tb+ (operation-on-tensor + 0))
(def tb- (operation-on-tensor - 0))
(def tb* (operation-on-tensor * 1))
(def tbd (operation-on-tensor / 1))
