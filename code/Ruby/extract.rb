require 'csv'

def prime?(number)
    return false if number <= 1
    Math.sqrt(number).to_i.downto(2).each {|i| return false if number % i == 0}
    true
end

puts "Enter the path to your text file: "
input_file_path = gets.chomp

begin
  input_file = File.open(input_file_path, "r")
rescue Errno::ENOENT
  puts "File not found. Please check the file path."
  exit
end

primes = []

CSV.foreach(input_file, headers: false) do |row|
  row.each do |cell|
    begin
      num = Integer(cell)
    rescue TypeError => e
      num = 0
    end
    primes << num if prime?(num)
  end
end

input_file.close

output_file_path = input_file_path.sub(/\.[^.]+$/, '') + "_primes.csv"

CSV.open(output_file_path, "w") do |csv|
  csv << primes
  csv << ["Total prime numbers:", primes.size]
end

puts "Prime numbers have been saved to #{output_file_path}"
