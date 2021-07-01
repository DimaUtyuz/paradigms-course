(def constant constantly)
(defn variable [name] #(get % name))
(defn operation [f]
  (fn [& args]
    (fn [variables]
      (apply f (mapv #(% variables) args)))))
(def add (operation +))
(def subtract (operation -))
(def negate subtract)
(def multiply (operation *))
(def divide (operation (fn ([one] (/ 1.0 one))
                         ([one & arr] (/ (double one) (apply * arr))))))
(defn mean [& args] (fn [variables] (/ ((apply add args) variables)
                                       (count args))))
(defn square [a] (multiply a a))
(defn varn [& args] (fn [variables] (-
                                      ((apply mean (mapv #(square %) args)) variables)
                                      ((square (apply mean args)) variables))))
(def map-operation-func {
                         '+ add
                         '- subtract
                         '* multiply
                         '/ divide
                         'negate negate
                         'mean mean
                         'varn varn
                         })
(defn parseFunc [elements]
  (cond
    (symbol? elements)
    (variable (str elements))
    (number? elements)
    (constant elements)
    :else
    (apply (get map-operation-func (first elements))
           (mapv #(parseFunc %) (rest elements)))))
(defn parseFunction [str]
  (parseFunc (read-string str)))





(defn first-char [string] (str (Character/toLowerCase ^char (get string 0))))
(definterface IElement
  (^String toString [])
  (^String toStringInfix [])
  (^Number evaluate [args])
  (diff [v]))
(deftype Element [name calculate create-diff]
  IElement
  (toString [this] (str name))
  (toStringInfix [this] (.toString this))
  (evaluate [this args] (calculate name args))
  (diff [this v] (create-diff name v)))
(deftype Operation [name members calculate create-diff]
  IElement
  (toString [this] (str "(" name " "
                        (clojure.string/join " " (mapv #(.toString %) members))
                        ")"))
  (toStringInfix [this] (if (= 1 (count members))
                          (str name "(" (.toStringInfix (first members)) ")")
                          (str "("
                               (clojure.string/join (str " " name " ") (mapv #(.toStringInfix %) members))
                               ")")))
  (evaluate [this args] (apply calculate (mapv #(.evaluate % args) members)))
  (diff [this v] (create-diff members (mapv #(.diff % v) members))))
(defn create-elem [calculate diff]
  (fn [name] (Element. name calculate diff)))
(defn create-op [name op diff]
  (fn [& members] (Operation. name members op diff)))
(def Constant (create-elem (fn [name args] name)
                           (fn [name v] (Constant 0))))
(def Variable (create-elem #(%2 (first-char %1))
                           #(if (= %2 (first-char %1))
                              (Constant 1)
                              (Constant 0))))
(def Negate (create-op "negate" - (fn [members diffs] (apply Negate diffs))))
(def Add (create-op "+" + (fn [members diffs] (apply Add diffs))))
(def Subtract (create-op "-" - (fn [members diffs] (apply Subtract diffs))))
(declare Multiply)
(declare Divide)
(defn diff-mul [members diffs] (let [mul (apply Multiply members)]
                                 (apply Add (mapv #(Multiply (Divide mul %1) %2) members diffs))))
(def Multiply (create-op "*" * (fn [members diffs] (diff-mul members diffs))))
(def Divide (create-op "/"
                       (fn ([one] (/ 1.0 one))
                         ([one & arr] (/ (double one) (apply * arr))))
                       (fn [members diffs]
                         (if (= 1 (count members))
                           (Divide (Negate (first diffs))
                                   (Multiply (first members) (first members)))
                           (let [tail-mul (apply Multiply (rest members))]
                             (Divide (Subtract (Multiply (first diffs)
                                                         tail-mul)
                                               (Multiply (first members)
                                                         (diff-mul (rest members) (rest diffs))))
                                     (Multiply tail-mul tail-mul)))))))
(def ArithMean (create-op "arith-mean"
                          (fn [& members] (/ (apply + members) (count members)))
                          (fn [members diffs] (apply ArithMean diffs))))
(def GeomMean (create-op "geom-mean"
                         (fn [& members] (Math/pow (Math/abs (double (apply * members))) (/ 1.0 (count members))))
                         (fn [members diffs]
                           (Multiply (apply GeomMean members)
                                     (apply ArithMean (mapv (fn [a da] (Divide da a)) members diffs))))))
(def HarmMean (create-op "harm-mean"
                         (fn [& members] (/ (double (count members)) (apply + (mapv (fn [a] (/ 1.0 a)) members))))
                         (fn [members diffs]
                           (let [harm (apply HarmMean members)]
                             (Multiply (Multiply harm harm)
                                       (apply ArithMean (mapv (fn [a da] (Divide da
                                                                                 (Multiply a a)))
                                                              members diffs)))))))
(defn number-to-boolean [number] (> number 0))
(defn boolean-to-number [bool] (if (= true bool) 1 0))
(def create-boolean #(create-op %1
                                (fn [a b] (boolean-to-number (%2 (number-to-boolean a) (number-to-boolean b))))
                                (constantly 0)))
(def And (create-boolean "&&" (fn [a b] (and a b))))
(def Or (create-boolean "||" (fn [a b] (or a b))))
(def Xor (create-boolean "^^" (fn [a b] (not= a b))))
(def Impl (create-boolean "->" (fn [a b] (or (not a) b))))
(def Iff (create-boolean "<->" (fn [a b] (= a b))))
(defn evaluate [expr args] (.evaluate expr args))
(defn toString [expr] (.toString expr))
(defn toStringInfix [expr] (.toStringInfix expr))
(defn diff [expr v] (.diff expr v))
(def map-operation-obj {
                        '+ Add
                        '- Subtract
                        '* Multiply
                        '/ Divide
                        'negate Negate
                        'ArithMean ArithMean
                        'arith-mean ArithMean
                        'GeomMean GeomMean
                        'geom-mean GeomMean
                        'HarmMean HarmMean
                        'harm-mean HarmMean
                        'And And
                        '&& And
                        'Or Or
                        '|| Or
                        'Xor Xor
                        (symbol "^^") Xor
                        'Impl Impl
                        '-> Impl
                        'Iff Iff
                        '<-> Iff})
(defn parseObj [elements]
  (cond
    (symbol? elements)
    (Variable (str elements))
    (number? elements)
    (Constant elements)
    :else
    (apply (get map-operation-obj (first elements))
           (mapv #(parseObj %) (rest elements)))))
(defn parseObject [str]
  (parseObj (read-string str)))





(load-file "parser.clj")
(def *digit (+char "0123456789."))
(def *string (fn [string]
               (+ignore (apply +seq (mapv #(+char (str %)) string)))))
(def *all-chars (mapv char (range 0 128)))
(def *space (+char (apply str (filter #(Character/isWhitespace ^char %) *all-chars))))
(def *ws (+ignore (+star *space)))
(def *letter (+char (apply str (filter #(Character/isLetter ^char %) *all-chars))))
(def *number (+map read-string (+str (+or (+plus *digit)
                                          (+seqf cons *ws (+char "-") (+plus *digit))))))
(def *variable (+str (+plus *letter)))
(defn infix-left [& args]
  [(apply hash-map args) 'left])
(defn infix-right [& args]
  [(apply hash-map args) 'right])
(def *map-priority [(infix-left "<->" Iff),
                    (infix-right "->" Impl),
                    (infix-left "^^" Xor),
                    (infix-left "||" Or),
                    (infix-left "&&" And),
                    (infix-left "+" Add "-" Subtract),
                    (infix-left "*" Multiply "/" Divide)])
(def *unary-operations {"negate" Negate})
(declare *parse-max-priority)
(def *map-unary (mapv (fn [entry] (+map (val entry) (+seqn 0 *ws (*string (key entry)) *ws (delay (*parse-max-priority)) *ws)))
                   *unary-operations))
(declare *parse-level)
(def *parse-max-priority #(apply +or (flatten
                                       [(+seqn 1 *ws (+char "(") *ws (delay (*parse-level 0)) *ws (+char ")") *ws)
                                        *map-unary
                                        (+map Constant *number)
                                        (+map Variable *variable)])))
(def fold-recur #(if (empty? %2)
                   %1
                   (let [next (first %2)
                         op (first next)
                         new-left (second next)]
                     (op %1 (fold-recur new-left (rest %2))))))
(def *fold-l (partial reduce #((first %2) %1 (second %2))))
(def *fold-r #(fold-recur (first %) (rest %)))
(defn *parse-peer-operations [map-operations]
  (apply +or (mapv #(+seqf (constantly (second %)) (*string (first %))) map-operations)))
(def *next-level #(if (== (+ % 1) (count *map-priority))
                    (delay (*parse-max-priority))
                    (delay (*parse-level (+ % 1)))))
(def *choose-fold #(if (= 'left (second (nth *map-priority %)))
                     *fold-l
                     *fold-r))
(defn *parse-level [level]
  (+seqf #((*choose-fold level) (cons %1 %2))
         (*next-level level)
         (+star (+seq *ws (*parse-peer-operations (first (nth *map-priority level))) *ws (*next-level level)))))
(def parseObjectInfix (+parser (+seqn 0 *ws (*parse-level 0) *ws)))
