#!/usr/bin/env ruby

class Array
  def max
    self.inject(0) {|m, el| m < el ? el : m}
  end
end

def parindrome_number?(n)
  n.to_s == n.to_s.reverse
end

def palindrome_numbers(from, to)
  ret = []
  (from..to).each do |x|
    (from..x).each do |y|
      z = x*y
      if parindrome_number?(z)
        ret.push z
      end
    end
  end
  ret
end

now = Time.now
sleep(1)
puts Time.now-now
# => 1.0018

now = Time.now
puts palindrome_numbers(900,1000).max
puts Time.now - now
# => 906609
# => 0.003472
#
# ruby は遅くない！

