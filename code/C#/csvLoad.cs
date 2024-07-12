using CsvHelper;
using System.Globalization;
using System.Collections.Generic;

public class csvLoad {

    public static List<string> ReadInCSV(string absolutePath)
    {
        List<string> allValues = new List<string>();

        using (TextReader fileReader = File.OpenText(absolutePath))
        {
            var csv = new CsvReader(fileReader, CultureInfo.InvariantCulture);
            csv.Configuration.HasHeaderRecord = false;

            while (csv.Read())
            {
                for (int i = 0; i < csv.Context.Record.Length; i++)
                {
                    allValues.Add(csv.GetField(i));
                }
            }
        }

        return allValues;
    }

}
