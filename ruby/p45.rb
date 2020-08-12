#!/usr/bin/env ruby

def penta(n)
  n*(3*n-1)/2
end

def hexa(n)
  n*(2*n -1)
end

penta(165)==hexa(143)
;true

def ph(p,h)
  while penta(p) != hexa(h)
    if penta(p) < hexa(h)
      p+=1
    else
      h+=1
    end
  end
  [p, h]
end

now = Time.now
puts ph(165, 143+1).to_s
puts Time.now - now

# [31977, 27693]
# 0.01174
# 117msec.

# racket : clojure : ruby = 5 : 60 : 120