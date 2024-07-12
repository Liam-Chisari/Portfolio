using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

class Program
{
    static void Main()
    {
        string inputFileName = GetInputFileName();
        string outputFileName = GetOutputFileName();

        string[] words = ReadTextFromFile(inputFileName);
        Dictionary<string, int> wordCounts = CountWords(words);

        string mostCommonWord = FindMostCommonWord(wordCounts);
        int mostCommonWordCount = wordCounts[mostCommonWord];

        SaveWordCountsToFile(wordCounts, outputFileName);

        Console.WriteLine("Most common word: " + mostCommonWord);
        Console.WriteLine("Number of occurrences: " + mostCommonWordCount);
    }

    static string GetInputFileName()
    {
        Console.Write("Enter the name of the input file: ");
        string inputFileName = Console.ReadLine();
        return inputFileName;
    }

    static string GetOutputFileName()
    {
        Console.Write("Enter the name of the output file: ");
        string outputFileName = Console.ReadLine();
        return outputFileName;
    }

    static string[] ReadTextFromFile(string fileName)
    {
        string[] words;

        try
        {
            string text = File.ReadAllText(fileName);
            words = text.Split(new[] { ' ', ',', '.', '!', '?', ':', ';', '-', '\n' }, StringSplitOptions.RemoveEmptyEntries);
        }
        catch (Exception ex)
        {
            Console.WriteLine("Error reading from file: " + ex.Message);
            words = new string[0];
        }

        return words;
    }

    static Dictionary<string, int> CountWords(string[] words)
    {
        Dictionary<string, int> wordCounts = new Dictionary<string, int>();

        foreach (string word in words)
        {
            string lowercaseWord = word.ToLower();

            if (!wordCounts.ContainsKey(lowercaseWord))
            {
                wordCounts[lowercaseWord] = 0;
            }

            wordCounts[lowercaseWord]++;
        }

        return wordCounts;
    }

    static string FindMostCommonWord(Dictionary<string, int> wordCounts)
    {
        string mostCommonWord = "";
        int mostCommonWordCount = 0;

        foreach (KeyValuePair<string, int> entry in wordCounts)
        {
            if (entry.Value > mostCommonWordCount && !IsExcludedWord(entry.Key))
            {
                mostCommonWord = entry.Key;
                mostCommonWordCount = entry.Value;
            }
        }

        return mostCommonWord;
    }

    static bool IsExcludedWord(string word)
    {
        string[] excludedWords = { "and", "a", "I", "an", "or" };
        return excludedWords.Contains(word);
    }

    static void SaveWordCountsToFile(Dictionary<string, int> wordCounts, string fileName)
    {
        try
        {
            List<KeyValuePair<string, int>> sortedWordCounts = wordCounts.ToList();
            sortedWordCounts.Sort((x, y) => y.Value.CompareTo(x.Value));

            using (StreamWriter writer = new StreamWriter(fileName))
            {
                foreach (KeyValuePair<string, int> entry in sortedWordCounts)
                {
                    if (!IsExcludedWord(entry.Key))
                    {
                        writer.WriteLine(entry.Key + " " + entry.Value);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine("Error saving to file: " + ex.Message);
        }
    }
}
