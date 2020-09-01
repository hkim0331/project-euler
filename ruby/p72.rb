#!/usr/bin/env ruby

def p72(n)
  phi = (0..n).to_a
#  puts phi.join(',')
#  puts "before: #{phi[5]}"
  (2..n).each do |i|
    if phi[i]==i
      j=i
      while (j<=n) do
        phi[j] = (phi[j]*(i-1))/i
        j+=i
      end
    end
  end
#  puts "after: #{phi[5]}"
  phi.inject(:+)-1
end

raise "usage: ruby p72.rb <n>" if ARGV[0].nil?
n = ARGV[0].to_i

now = Time.now
puts p72(n)
puts Time.now - now
# 303963552391
# 0.302633
# ruby p72.rb 1000000  0.35s user 0.02s system 98% cpu 0.375 total
