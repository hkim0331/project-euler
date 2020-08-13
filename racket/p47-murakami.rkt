;; 47
;; それぞれ4つの異なる素因数を持つ連続する4つの数の最初の数は？

;(prime_factor 644) -> '(23 7 2 2)
;(prime_factor* 644) -> '(4 7 23)

(define member?
  (lambda (a lst)
    (cond
      ((null? lst) #f)
      ((eq? a (car lst)) #t)
      (else (member? a (cdr lst))))))

(define prime?-aux
  (lambda (x n)
    (cond
      ((> (* n n) x) #t)
      ((zero? (modulo x n)) #f)
      (else
       (prime?-aux x (+ n 2))))))
(define prime?
  (lambda (x)
    (cond
      ((<= x 1) #f)
      ((= x 2) #t)
      ((even? x) #f)
      (else (prime?-aux x 3)))))
(define prime_factor-aux
  (lambda (x n l)
    (cond
      ((prime? x) (cons x l))
      ((and (prime? n)
            (zero? (modulo x n)))
       (prime_factor-aux (/ x n) n (cons n l)))
      (else
       (prime_factor-aux x (+ n 1) l)))))
(define prime_factor
  (lambda (x)
    (prime_factor-aux x 2 '())))

(define prime_factor*
  (lambda (x)
    (define aux
      (lambda (ret ls n)
        (cond
          ((null? ls) ret)
          ((= (car ls) n)
           (aux (cons (* (car ls) (car ret)) (cdr ret))
                (cdr ls) n))
          (else
           (aux (cons (car ls) ret) (cdr ls) (car ls))))))
    (aux '() (prime_factor x) 0)))

;同じ要素を含むかどうか
(define contain_same?
  (lambda (l1 l2)
    (cond
      ((null? l1) #f)
      ((member? (car l1) l2) #t)
      (else
       (contain_same? (cdr l1) l2)))))

;(p47-aux '((1 2 3)(4 5 6)(7 8 9)) '(8 a b)) -> 3
(define p47-aux
  (lambda (lls l)
    (cond
      ((null? lls) 0)
      ((contain_same? (last lls) l) (length lls))
      (else (p47-aux (drop-right lls 1) l)))))

(define p47
  (lambda (a)
    (define aux
      (lambda (a n ls)
        (let ((pf (prime_factor* n)))
          (cond
            ((= a (length ls)) (- n a))
            ((not (= a (length pf)))
             (aux a (+ n 1) '()))
            (else
             (let ((x (p47-aux ls pf)))
               (if (= x 0)
                   (aux a (+ n 1)
                        (append ls (list pf)))
                   (aux a (+ n 1)
                        (drop (append ls (list pf)) x)))))))))
    (aux a 2 '())))

(p47 4)

(time (p47 4))
cpu time: 2584 real time: 2599 gc time: 46
134043