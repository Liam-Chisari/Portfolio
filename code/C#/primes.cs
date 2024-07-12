using System;
using System.IO;
using System.Linq;

class Program
{
    static void Main()
    {
        string fileName = GetInputFileName();
        string[] numbers = ReadCsvFile(fileName);
        numbers = TrimStringArray(numbers);
        int[] intArray = ConvertToIntArray(numbers);
        int primeCount = PrintPrimeNumbers(intArray);
        SavePrimeNumbersToFile(intArray.Where(n => IsPrime(n)).ToArray(), "prime_numbers.csv");
        Console.WriteLine("Number of prime numbers found: " + primeCount);
    }

static string GetInputFileName()
{
    Console.Write("Enter the name of the CSV file: ");
    string fileName = Console.ReadLine();
    while (!File.Exists(fileName))
    {
        Console.WriteLine("File not found. Please enter a valid file name:");
        fileName = Console.ReadLine();
    }
    return fileName;
}

static string[] ReadCsvFile(string fileName)
{
    try
    {
        return File.ReadAllLines(fileName);
    }
    catch (Exception ex)
    {
        Console.WriteLine("Error reading the file: " + ex.Message);
        return new string[0];
    }
}
static string[] TrimStringArray(string[] input) {
    int i = 0;
    for(i=0;i<input.Length;i++) {
        if(input[i].Length > 1) {
           input[i] = input[i].Trim(','); 
        }
    }
    return input;

}

static int[] ConvertToIntArray(string[] numbers)
{
    try
    {
        return numbers.Select(n => int.Parse(n)).ToArray();
    }
    catch (Exception ex)
    {
        Console.WriteLine("Error converting to integer array: " + ex.Message);
        return new int[0];
    }
}

static bool IsPrime(int n)
{
    if (n <= 1)
        return false;
    for (int i = 2; i * i <= n; i++)
    {
        if (n % i == 0)
            return false;
    }
    return true;
}

static int PrintPrimeNumbers(int[] intArray)
{
    int primeCount = 0;
    foreach (int n in intArray)
    {
        if (IsPrime(n))
        {
            Console.WriteLine(n);
            primeCount++;
        }
    }
    return primeCount;
}

static void SavePrimeNumbersToFile(int[] primeNumbers, string fileName)
{
    try
    {
        File.WriteAllLines(fileName, primeNumbers.Select(n => n.ToString()).ToArray());
    }
    catch (Exception ex)
    {
        Console.WriteLine("Error saving prime numbers to file: " + ex.Message);
    }
}

}
