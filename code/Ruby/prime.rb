require 'csv'

def prime?(n)
    return true if n < 2
    (2..Math.sqrt(n)).each { |i| return false if n % i == 0 }
    true
end

def find_primes_and_write_to_file(n, primes = [])
    return if n <= 1 
    if prime?(n)
        primes << n
        CSV.open("primes.csv", "w+") do |csv|
            csv << primes
        end
    end
    find_primes_and_write_to_file(n-1, primes)
end

puts "Enter a number: "
num = gets.chomp.to_i
find_primes_and_write_to_file(num)
puts "Written primes less than and including #{num} to file primes.csv"
