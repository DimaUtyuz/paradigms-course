# Учебные проекты по курсу "Парадигмы программирования"

[Основная страница курса](http://www.kgeorgiy.info/courses/paradigms/index.html)

## Памятка

Все решения реализованы на языках программирования Java, Javascipt, Clojure, Prolog. 

В решениях реализованы сложные модификации (38-39 группы).

### Список условий:

#### [1. Бинарный поиск](#домашнее-задание-1-бинарный-поиск)

#### [2. Очередь на массиве](#домашнее-задание-2-очередь-на-массиве)

#### [3. Очереди](#домашнее-задание-3-очереди)

#### [4. Вычисление в различных типах](#домашнее-задание-4-вычисление-в-различных-типах)

#### [5. Функциональные выражения на JavaScript](#домашнее-задание-5-функциональные-выражения-на-javascript)

#### [6. Объектные выражения на JavaScript](#домашнее-задание-6-объектные-выражения-на-javascript)

#### [7. Обработка ошибок на JavaScript](#домашнее-задание-7-обработка-ошибок-на-javascript)

#### [8. Линейная алгебра на Clojure](#домашнее-задание-8-линейная-алгебра-на-clojure)

#### [9. Функциональные выражения на Clojure](#домашнее-задание-9-функциональные-выражения-на-clojure)

#### [10. Объектные выражения на Clojure](#домашнее-задание-10-объектные-выражения-на-clojure)

#### [11. Комбинаторные парсеры](#домашнее-задание-11-комбинаторные-парсеры)

#### [12. Простые числа на Prolog](#домашнее-задание-12-простые-числа-на-prolog)

#### [13. Деревья поиска на Prolog](#домашнее-задание-13-деревья-поиска-на-prolog)

### Домашнее задание 1. Бинарный поиск

1.  Реализуйте итеративный и рекурсивный варианты бинарного поиска в массиве.
2.  На вход подается целое число `x` и массив целых чисел `a`, отсортированный по невозрастанию. Требуется найти минимальное значение индекса `i`, при котором `a[i] <= x`.
3.  Для функций бинарного поиска и вспомогательных функций должны быть указаны, пред- и постусловия. Для реализаций методов должны быть приведены доказательства соблюдения контрактов в терминах троек Хоара.
4.  Интерфейс программы.
    *   Имя основного класса — `BinarySearch`.
    *   Первый аргумент командной строки — число `x`.
    *   Последующие аргументы командной строки — элементы массива `a`.
5.  Пример запуска: `java BinarySearch 3 5 4 3 2 1`. Ожидаемый результат: `2`.
6.  **Модификация** *Max*
    * На вход подается массив полученный приписыванием 
      отсортированного (строго) по убыванию массива 
      в конец массива отсортированного (строго) по возрастанию
      Требуется найти в нем максимальное значение.

### Домашнее задание 2. Очередь на массиве

1.  Определите модель и найдите инвариант структуры данных «[очередь](http://ru.wikipedia.org/wiki/%D0%9E%D1%87%D0%B5%D1%80%D0%B5%D0%B4%D1%8C_(%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5))». Определите функции, которые необходимы для реализации очереди. Найдите их пред- и постусловия, при условии что очередь не содержит `null`.
2.  Реализуйте классы, представляющие циклическую очередь с применением массива.
    *   Класс `ArrayQueueModule` должен реализовывать один экземпляр очереди с использованием переменных класса.
    *   Класс `ArrayQueueADT` должен реализовывать очередь в виде абстрактного типа данных (с явной передачей ссылки на экземпляр очереди).
    *   Класс `ArrayQueue` должен реализовывать очередь в виде класса (с неявной передачей ссылки на экземпляр очереди).
    *   Должны быть реализованы следующие функции (процедуры) / методы:
        *   `enqueue` – добавить элемент в очередь;
        *   `element` – первый элемент в очереди;
        *   `dequeue` – удалить и вернуть первый элемент в очереди;
        *   `size` – текущий размер очереди;
        *   `isEmpty` – является ли очередь пустой;
        *   `clear` – удалить все элементы из очереди.
    *   Инвариант, пред- и постусловия записываются в исходном коде в виде комментариев.
    *   Обратите внимание на инкапсуляцию данных и кода во всех трех реализациях.
3.  **Модификация** *DequeToStrArray*
    * Реализовать методы
        * `push` – добавить элемент в начало очереди
        * `peek` – вернуть последний элемент в очереди
        * `remove` – вернуть и удалить последний элемент из очереди
    * Реализовать метод `toArray`, возвращающий массив,
      содержащий элементы, лежащие в очереди в порядке
      от головы к хвосту.
    * Реализовать метод `toStr`, возвращающий строковое представление
      очереди в виде '`[`' _голова_ '`, `' ... '`, `' _хвост_ '`]`'

### Домашнее задание 3. Очереди

1.  Определите интерфейс очереди `Queue` и опишите его контракт.
2.  Реализуйте класс `LinkedQueue` — очередь на связном списке.
3.  Выделите общие части классов `LinkedQueue` и `ArrayQueue` в базовый класс `AbstractQueue`.
4.  **Модификация** *Nth*
    * Добавить в интерфейс очереди и реализовать методы
        * `getNth(n)` – создать очередь, содержащую каждый n-й элемент, считая с 1
        * `removeNth(n)` – создать очередь, содержащую каждый n-й элемент, и удалить их из исходной очереди
        * `dropNth(n)` – удалить каждый n-й элемент из исходной очереди
    * Тип возвращаемой очереди должен соответствовать типу исходной очереди
    * Дублирования кода быть не должно

### Домашнее задание 4. Вычисление в различных типах

Добавьте в программу разбирающую и вычисляющую выражения трех переменных поддержку вычисления в различных типах.

1.  Создайте класс `expression.generic.GenericTabulator`, реализующий интерфейс `expression.generic.Tabulator`:
                
    Аргументы
    
    *   `mode` — режим работы 
    *   `expression` — вычисляемое выражение;
    *   `x1`, `x2`; `y1`, `y2`; `z1`, `z2` — диапазоны изменения переменны (включительно).
    
    Возвращаемое значение — таблица значений функции, где `R[i][j][k]` соответствует `x = x1 + i`, `y = y1 + j`, `z = z1 + k`. Если вычисление завершилось ошибкой, в соответствующей ячейке должен быть `null`.
    
2.  Доработайте интерфейс командной строки:
    *   Первым аргументом командной строки программа должна принимать указание на тип, в котором будут производится вычисления:
        
        `-i`
        
        `-d`
        
        `-bi`
        
        `BigInteger` [документация](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/math/BigInteger.html)
        
    *   Вторым аргументом командной строки программа должна принимать выражение для вычисления.
    *   Программа должна выводить результаты вычисления для всех целочисленных значений переменных из диапазона −2..2.
3.  Реализация не должна содержать [непроверяемых преобразований типов](https://docs.oracle.com/javase/specs/jls/se11/html/jls-5.html#jls-5.1.9).
4.  Реализация не должна использовать аннотацию [@SuppressWarnings] [ссылка](https://docs.oracle.com/javase/specs/jls/se11/html/jls-9.html#jls-9.6.4.5).
5.  При выполнении задания следует обратить внимание на простоту добавления новых типов и операциий.
6.  **Модификация** *AsmUpb*
    * Дополнительно реализовать унарные операции:
        * `abs` – модуль числа, `abs -5` равно 5;
        * `square` – возведение в квадрат, `square 5` равно 25.
    * Дополнительно реализовать бинарную операцию (максимальный приоритет):
        * `mod` – взятие по модулю, приоритет как у умножения (`1 + 5 mod 3` равно `1 + (5 mod 3)` равно `3`).
    * Дополнительно реализовать поддержку режимов:
        * `u` – вычисления в `int` без проверки на переполнение;
        * `p` – вычисления в целых числах по модулю 1009;
        * `b` – вычисления в `byte` без проверки на переполнение.

### Домашнее задание 5. Функциональные выражения на JavaScript

1.  Разработайте функции `cnst`, `variable`, `add`, `subtract`, `multiply`, `divide`, `negate` для вычисления выражений с одной переменной.
2.  Функции должны позволять производить вычисления вида:
    
    let expr = subtract(
        multiply(
            cnst(2),
            variable("x")
        ),
        cnst(3)
    );
    
    println(expr(5));
                
    
    При вычислении такого выражения вместо каждой переменной подставляется значение, переданное в качестве параметра функции `expr` (на данном этапе имена переменных игнорируются). Таким образом, результатом вычисления приведенного примера должно стать число 7.
3.  Тестовая программа должна вычислять выражение `x2−2x+1`, для `x` от 0 до 10.
4.  **Сложный вариант.** Требуется дополнительно написать функцию `parse`, осуществляющую разбор выражений, записанных в [обратной польской записи](https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C). Например, результатом
    
    parse("x x 2 - \* x \* 1 +")(5)
    
    должно быть число `76`.
5.  При выполнение задания следует обратить внимание на:
    *   Применение функций высшего порядка.
    *   Выделение общего кода для операций.
6.  **Модификация** *OneFP*
    * Дополнительно реализовать поддержку:
		* переменных: `y`, `z`;
		* констант:
			* `one` – 1;
			* `two` – 2;
		* операций:
			* `*+` (`madd`) – тернарный оператор произведение-сумма, `2 3 4 *+` равно 10;
			* `_` (`floor`) – округление вниз `2.7 _` равно 2;
			* `^` (`ceil`) – округление вверх `2.7 ^` равно 3.

### Домашнее задание 6. Объектные выражения на JavaScript

1.  Разработайте классы `Const`, `Variable`, `Add`, `Subtract`, `Multiply`, `Divide`, `Negate` для представления выражений с одной переменной.
    1.  Пример описания выражения `2x-3`:
        
        let expr = new Subtract(
            new Multiply(
                new Const(2),
                new Variable("x")
            ),
            new Const(3)
        );
        
        println(expr.evaluate(5));
                            
        
    2.  При вычислении такого выражения вместо каждой переменной подставляется её значение, переданное в качестве аргумента метода `evaluate`. Таким образом, результатом вычисления приведенного примера должно стать число 7.
    3.  Метод `toString()` должен выдавать запись выражения в [обратной польской записи](https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C). Например, `expr.toString()` должен выдавать «`2 x * 3 -`».
2.  **Сложный вариант.**
    
    Метод `diff("x")` должен возвращать выражение, представляющее производную исходного выражения по переменной `x`. Например, `expr.diff("x")` должен возвращать выражение, эквивалентное `new Const(2)` (выражения `new Subtract(new Const(2), new Const(0))` и
    
    new Subtract(
        new Add(
            new Multiply(new Const(0), new Variable("x")),
            new Multiply(new Const(2), new Const(1))
        )
        new Const(0)
    )
                     
    
    так же будут считаться правильным ответом).
    
    Функция `parse` должна выдавать разобранное объектное выражение.
    
3.  **Бонусный вариант.** Требуется написать метод `simplify()`, производящий вычисления константных выражений. Например,
    
    parse("x x 2 - \* 1 \*").diff("x").simplify().toString()
    
    должно возвращать «`x x 2 - +`».
4.  При выполнение задания следует обратить внимание на:
    *   Применение инкапсуляции.
    *   Выделение общего кода для операций.
    *   Минимизацию необходимой памяти.
5.  **Модификация** *Harmonic*
	* Дополнительно реализовать поддержку функций от двух аргументов:
		* `Hypot` (`hypot`) – квадрат гипотенузы, `3 4 hypot` равно 25;
		* `HMean` (`hmean`) – гармоническое среднее, `5 20 hmean` равно 8;

### Домашнее задание 7. Обработка ошибок на JavaScript

1.  Добавьте в предыдущее домашнее задание функцию `parsePrefix(string)`, разбирающую выражения, задаваемые записью вида «`(- (* 2 x) 3)`». Если разбираемое выражение некорректно, метод `parsePrefix` должен бросать человеко-читаемое сообщение об ошибке.
2.  Добавьте в предыдущее домашнее задание метод `prefix()`, выдающий выражение в формате, ожидаемом функцией `parsePrefix`.
3.  При выполнение задания следует обратить внимание на:
    *   Применение инкапсуляции.
    *   Выделение общего кода для операций.
    *   Минимизацию необходимой памяти.
    *   Обработку ошибок.
4.  **Модификация** *Postfix*: *Means*
	* Дополнительно реализовать поддержку постфиксной записи
    * Дополнительно реализовать поддержку операций произвольного числа аргументов:
        * `ArithMean` (`arith-mean`) – арифметическое среднее `(arith-mean 1 2 6)` равно 3;
        * `GeomMean` (`geom-mean`) – геометрическое среднее `(geom-mean 1 2 4)` равно 2;
        * `HarmMean` (`harm-mean`) – гармоническое среднее, `(harm-mean 2 3 6)` равно 3;

### Домашнее задание 8. Линейная алгебра на Clojure

1.  Разработайте функции для работы с объектами линейной алгебры, которые представляются следующим образом:
    *   скаляры – числа
    *   векторы – векторы чисел;
    *   матрицы – векторы векторов чисел.
2.  Функции над векторами:
    *   `v+`/`v-`/`v*`/`vd` – покоординатное сложение/вычитание/умножение/деление;
    *   `scalar`/`vect` – скалярное/векторное произведение;
    *   `v*s` – умножение на скаляр.
3.  Функции над матрицами:
    *   `m+`/`m-`/`m*`/`md` – поэлементное сложение/вычитание/умножение/деление;
    *   `m*s` – умножение на скаляр;
    *   `m*v` – умножение на вектор;
    *   `m*m` – матричное умножение;
    *   `transpose` – транспонирование;
4.  **Сложный вариант.**
    1.  Ко всем функциям должны быть указаны контракты. Например, нельзя складывать вектора разной длины.
    2.  Все функции должны поддерживать произвольное число аргументов. Например `(v+ [1 2] [3 4] [5 6])` должно быть равно `[9 12]`.
5.  При выполнение задания следует обратить внимание на:
    *   Применение функций высшего порядка.
    *   Выделение общего кода для операций.
6.  **Модификация** *Broadcast*
    * Назовем _тензором_ многомерную прямоугольную таблицу чисел.
    * _Форма_ тензора – последовательность чисел
        (_s_<sub>1..n</sub>)=(_s_<sub>1</sub>, _s_<sub>2</sub>, …, _s<sub>n</sub>_), где
        _n_ – размерность тензора, а _s<sub>i</sub>_ – число элементов
        по _i_-ой оси.
      Например, форма тензора `[[[2 3 4] [5 6 7]]]`  равна (1, 2, 3),
      а форма `1` равна ().
    * Тензор формы (_s_<sub>1.._n_</sub>) может быть _распространен_ (broadcast)
      до тензора формы (_u_<sub>1.._m_</sub>), если (_s_<sub>i.._n_</sub>) является
      префиксом (_u<sub>1..m</sub>_). 
      Для этого, элементы тензора копируются по недостающим осям.
      Например, распространив тензор `[[1 2]]` формы (1, 2) до
      формы (1, 2, 3) получим `[[[1 1 1] [2 2 2]]]`,
      а распространив `1` до формы (2, 3) получим `[[1 1 1] [1 1 1]]`.
    * Тензоры называются совместимыми, если один из них может быть распространен
      до формы другого.
      Например, тензоры формы (1, 2, 3) и (1, 2) совместимы, а
      (1, 2, 3) и (2, 1) – нет. Числа совместимы с тензорами любой формы.
    * Добавьте операции поэлементного 
      сложения (`tb+`), вычитания (`tb-`), умножения (`tb*`) и деления (`tbd`)
      совместимых тензоров.
      Если формы тензоров не совпадают, то тензоры меньшей размерности
      должны быть предварительно распространены до тензоров большей размерности.
      Например, `(tb+ 1 [[10 20 30] [40 50 60]] [100 200])` 
      должно быть равно `[[111 121 131] [241 251 261]]`.

### Домашнее задание 9. Функциональные выражения на Clojure

1.  Разработайте функции `constant`, `variable`, `add`, `subtract`, `multiply` и `divide` для представления арифметических выражений.
    1.  Пример описания выражения `2x-3`:
        
        (def expr
          (subtract
            (multiply
              (constant 2)
              (variable "x"))
            (constant 3)))
                            
        
    2.  Выражение должно быть функцией, возвращающей значение выражения при подстановке переменных, заданных отображением. Например, `(expr {"x" 2})` должно быть равно 1.
2.  Разработайте разборщик выражений, читающий выражения в стандартной для Clojure форме. Например,
    
    (parseFunction "(- (\* 2 x) 3)")
    
    должно быть эквивалентно `expr`.
3.  **Сложный вариант.** Функции `add`, `subtract`, `multiply` и `divide` должны принимать произвольное число аргументов. Разборщик так же должен допускать произвольное число аргументов для `+`, `-`, `*`, `/`.
4.  При выполнение задания следует обратить внимание на:
    *   Выделение общего кода для операций.
5.  **Модификация** *MeanVarn*
    * Дополнительно реализовать поддержку операций произвольного числа аргументов:
        * `mean` – математическое ожидание аргументов, `(mean 1 2 6)` равно 3;
        * `varn` – дисперсия аргументов, `(varn 2 5 11)` равно 14;

### Домашнее задание 10. Объектные выражения на Clojure

1.  Разработайте конструкторы `Constant`, `Variable`, `Add`, `Subtract`, `Multiply` и `Divide` для представления выражений с одной переменной.
    1.  Пример описания выражения `2x-3`:
        
        (def expr
          (Subtract
            (Multiply
              (Constant 2)
              (Variable "x"))
            (Constant 3)))
                            
        
    2.  Функция `(evaluate expression vars)` должна производить вычисление выражения `expression` для значений переменных, заданных отображением `vars`. Например, `(evaluate expr {"x" 2})` должно быть равно 1.
    3.  Функция `(toString expression)` должна выдавать запись выражения в стандартной для Clojure форме.
    4.  Функция `(parseObject "expression")` должна разбирать выражения, записанные в стандартной для Clojure форме. Например,
        
        (parseObject "(- (\* 2 x) 3)")
        
        должно быть эквивалентно `expr`.
    5.  Функция `(diff expression "variable")` должена возвращать выражение, представляющее производную исходного выражения по заданой пермененной. Например, `(diff expression "x")` должен возвращать выражение, эквивалентное `(Constant 2)`, при этом выражения `(Subtract (Constant 2) (Constant 0))` и
        
        (Subtract
          (Add
            (Multiply (Constant 0) (Variable "x"))
            (Multiply (Constant 2) (Constant 1)))
          (Constant 0))
                            
        
        так же будут считаться правильным ответом.
2.  **Сложный вариант.** Констуркторы `Add`, `Subtract`, `Multiply` и `Divide` должны принимать произвольное число аргументов. Разборщик так же должен допускать произвольное число аргументов для `+`, `-`, `*`, `/`.
3.  При выполнение задания можно использовать любой способ преставления объектов.
4.  **Модификация** *Means*
    * Дополнительно реализовать поддержку операций произвольного числа аргументов:
        * `ArithMean` (`arith-mean`) – арифметическое среднее `(arith-mean 1 2 6)` равно 3;
        * `GeomMean` (`geom-mean`) – геометрическое среднее `(geom-mean 1 2 4)` равно 2;
        * `HarmMean` (`harm-mean`) – гармоническое среднее, `(harm-mean 2 3 6)` равно 3;

### Домашнее задание 11. Комбинаторные парсеры

1.  **Простой вариант.** Реализуйте функцию `(parseObjectSuffix "expression")`, разбирающую выражения, записанные в суффиксной форме, и функцию `toStringSuffix`, возвращающую строковое представление выражения в этой форме. Например,
    
    (toStringSuffix (parseObjectSuffix "( ( 2 x \* ) 3 - )"))
    
    должно возвращать `((2 x *) 3 -)`.
2.  **Сложный вариант.** Реализуйте функцию `(parseObjectInfix "expression")`, разбирающую выражения, записанные в инфиксной форме, и функцию `toStringInfix`, возвращающую строковое представление выражения в этой форме. Например,
    
    (toStringInfix (parseObjectInfix "2 \* x - 3"))
    
    должно возвращать `((2 * x) - 3)`.
3.  **Бонусный вариант.** Добавьте в библиотеку комбинаторов возможность обработки ошибок и продемонстрируйте ее использование в вашем парсере.
4.  Функции разбора должны базироваться на библиотеке комбинаторов, разработанной на лекции.
5.  **Модификация** *ImplIff, Boolean и Variables*
	* Дополнительно реализовать поддержку переменных, состоящих из произвольного количества букв `XYZ` в любом регистре
        * Настоящее имя переменной определяется первой буквой ее имени
    * Дополнительно реализовать поддержку булевых операций
		* Аргументы: число больше 0 → `true`, иначе → `false`
        * Результат: `true` → 1, `false` → 0
        * `And` (`&&`) – и: `5 & -6` равно 0
        * `Or`  (`||`) - или: `5 & -6` равно 1
        * `Xor` (`^^`) - исключающее или: `5 ^ -6` равно 1
        * `Impl` (`->`) – импликация (правоассоциативна): `-4 -> 1` равно 1
        * `Iff`  (`<->`) - тогда и только тогда: `2 <-> 6` равно 1
        * операции по увеличению приоритета: `<->`, `->`, `^^`, `||`, `&&`

### Домашнее задание 12. Простые числа на Prolog

1.  Разработайте правила:
    
    *   `prime(N)`, проверяющее, что `N` – простое число.
    *   `composite(N)`, проверяющее, что `N` – составное число.
    *   `prime_divisors(N, Divisors)`, проверяющее, что список `Divisors` содержит все простые делители числа `N`, упорядоченные по возрастанию. Если `N` делится на простое число `P` несколько раз, то `Divisors` должен содержать соответствующее число копий `P`.
    
2.  Варианты
    
    *   Простой: `N` ≤ 1000.
    *   Сложный: `N` ≤ 105.
    *   Бонусный: `N` ≤ 107.
3.  Вы можете рассчитывать, на то, что до первого запроса будет выполнено правило `init(MAX_N)`.
4.  **Модификация** *Index*
    * Добавьте правило `prime_index(P, N)`, подсчитывающее номер простого числа:
        `prime_index(2, 1)`, `prime_index(101, 26)`
		
### Домашнее задание 13. Деревья поиска на Prolog

1.  Реализуйте ассоциативный массив (map) на основе деревьев поиска. Для решения можно реализовать любое дерево поиска логарифмической высоты.
2.  **Сложный вариант.** Разработайте правила:
	* `map_get(TreeMap, Key, Value)`, проверяющее, что массив содержит заданную пару ключ-значение `(O(log n))`.
	* `map_put(TreeMap, Key, Value, Result)`, добавляющее пару ключ-значение в массив, или заменяющее текущее значение для ключа `(O(log n))`;
	* `map_remove(TreeMap, Key, Result)`, удаляющее отображение для ключа `(O(log n))`;
	* `map_build(ListMap, TreeMap)`, строящее дерево из неупорядоченного списка пар ключ-значение `(O(n log n))`.
3.  **Модификация** *HeadTail*
    * Добавьте правила:
        * `map_headMapSize(Map, ToKey, Size)`, 
            возвращающее число пар меньших `ToKey`;
        * `map_tailMapSize(Map, FromKey, Size)`, 
            возвращающее число пар больших или равных `FromKey`,
