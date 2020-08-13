;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; prime-factors を p47 用に書き換える。
;
; * 重複をカウントしない。
; * max個を超えて素因数を探さない。
; * リストではなく5以下の整数を返す。

; n を m で割れるだけ割る
(define //
  (lambda (n m)
    (if (zero? (modulo n m))
        (// (/ n m) m)
        n)))

; 素因数のリストを返そう。
(define dpfc-aux
  (lambda (n i)
    (cond
      ((= n 1) ret)
      ((< n (* i i)) ret)
      ((zero? (modulo n i))
       (dpfc-aux (/ n i) (+1 i) (cons (i ))))
      (else (dpfc-aux n (+ 1 i) mx ret)))))

(define dpfc
  (lambda (n)
    (dpfc-aux n 2)))

(define p47-aux
  (lambda (n i c)
    (if (= n c)
      (- i n)
      (p47-aux n
               (+ i 1)
               (if (= n (dpfc i))
                  (+ 1 c)
                  0)))))

(define p47
  (lambda (n)
     (p47-aux n 1 0)))

;(p47 2)
;14
;(p47 3)
; 644
(time (p47 4))
