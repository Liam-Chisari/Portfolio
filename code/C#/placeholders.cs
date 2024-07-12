using System;
using System.Collections.Generic;

public class Placeholders {
    public static string ReplacePlaceholders(string template, Dictionary<string, string> values) {
        return System.Text.RegularExpressions.Regex.Replace(template, @"%(\w+)%", match =>
        {
            string key = match.Groups[1].Value;
            return values.ContainsKey(key) ? values[key] : match.Value;
        });
    }

    static void Main(string[] args) {
        // Example usage


        string name = "Alice";
        int age = 30;
        string message = $"Hello, {name}! You are {age} years old.";


        var parameters = new Dictionary<string, string>
        {
            { "name", "Alice" },
            { "age", "30" }
        };

        string template = $"Hello, {name}! You are {age} years old.";
        string result = ReplacePlaceholders(template, parameters);
        Console.Out.WriteLine(result); // Outputs "Hello, Alice! You are 30 years old."
    }


}
