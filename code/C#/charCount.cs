using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

class Program
{
    static void Main()
    {
        Console.Write("Please enter the path to the text file: ");
        string filePath = Console.ReadLine();

    try
    {
        string text = File.ReadAllText(filePath);
        CountAndSortCharacters(text);
    }
    catch (Exception ex)
    {
        Console.WriteLine("Error: " + ex.Message);
    }
}

static void CountAndSortCharacters(string text)
{
    var characterCounts = new Dictionary<char, int>();

    foreach (char c in text)
    {
        if (!characterCounts.ContainsKey(c))
        {
            characterCounts[c] = 0;
        }
        characterCounts[c]++;
    }

    var sortedCharacterCounts = from entry in characterCounts
                                 orderby entry.Value descending, entry.Key
                                 select entry;

    foreach (var entry in sortedCharacterCounts)
    {
        Console.WriteLine($"{entry.Key}: {entry.Value}");
    }
}

}
