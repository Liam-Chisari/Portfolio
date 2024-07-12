using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;

class FoodItem
{
    public string Name { get; set; }
    public int Kilojoules { get; set; }
    public double Protein { get; set; }
    public double Carbohydrates { get; set; }
    public double Fat { get; set; }

    public override string ToString() => $"{Name} - {Kilojoules}kj - {Protein}g protein - {Carbohydrates}g carbs - {Fat}g fat";
}

class Meal
{
    public string Type { get; set; }
    public List<FoodItem> Items { get; set; }

    public Meal(string type)
    {
        Type = type;
        Items = new List<FoodItem>();
    }

    public override string ToString() => $"{Type}: " + string.Join(", ", Items);
}

class Day
{
    public DateTime Date { get; set; }
    public List<Meal> Meals { get; set; }

    public Day()
    {
        Meals = new List<Meal>();
    }
    public override string ToString() => $"{Date.ToString("dd/MM/yy")}: " + string.Join(", ", Meals);
}

class FoodDiary
{
    private List<Day> diary = new List<Day>();
    private string filePath;

    public FoodDiary()
    {
        filePath = "";
    }
    //Parse food diary from csv file
    public void LoadFromFile(string fileName)
    {
        if (!File.Exists(fileName))
        {
            throw new FileNotFoundException("File not found", fileName);
        }

        diary = new List<Day>();
        foreach (var line in File.ReadAllLines(fileName))
        {
            var values = line.Split(',');
            if (values.Length < 2) continue; //invalid line

            var day = new Day();
            if (!DateTime.TryParseExact(values[0], "dd/MM/yy", CultureInfo.InvariantCulture, DateTimeStyles.None, out day.Date))
            {
                throw new FormatException("Invalid date format in file");
            }

            for (int i = 1; i < values.Length; i++)
            {
                var mealValues = values[i].Split('-');
                if (mealValues.Length != 6) 
                    throw new FormatException("Invalid meal data format in file");

                var meal = new Meal(mealValues[0].Trim());
                for (int j = 1; j < mealValues.Length; j++)
                {
                    var foodItem = ParseFoodItem(mealValues[j].Trim());
                    meal.Items.Add(foodItem);
                }
                day.Meals.Add(meal);
            }
            diary.Add(day);
        }
    }
      //Convert string to FoodItem object
    private FoodItem ParseFoodItem(string input)
    {
        var values = input.Split();
        if (values.Length != 5)
            throw new FormatException("Invalid food item data format in file");

        return new FoodItem
        {
            Name = values[0],
            Kilojoules = int.Parse(values[1]),
            Protein = double.Parse(values[2]),
            Carbohydrates = double.Parse(values[3]),
            Fat = double.Parse(values[4]),
        };
    }
    //Saves the food diary to a csv file.
    public void SaveToFile()
    {
        if (string.IsNullOrWhiteSpace(filePath))
        {
            filePath = GetFilePathFromUser();
        }
          File.WriteAllLines(filePath, diary.Select(FormatDay).ToArray());  
    }
      private string FormatDay(Day day)
    {
        var result = day.Date.ToString("dd/MM/yy") + ", ";
        result += string.Join(", ", day.Meals.Select(FormatMeal));
        return result;
    }
    private string FormatMeal(Meal meal)
    {
        return $"{meal.Type} - " + string.Join(" - ", meal.Items.Select(FormatFoodItem));
    }
    private string FormatFoodItem(FoodItem foodItem)
    {
        return $"{foodItem.Name} - {foodItem.Kilojoules} - {foodItem.Protein} - {foodItem.Carbohydrates} - {foodItem.Fat}";
    }
      // handle user's option
    public void Run()
    {
        string inputFile = "";
        bool isNewDiary = true;
        //Check if user wants to start new or load existing file
        Console.Write("Enter file path (or blank for new diary): ");
        inputFile = Console.ReadLine();
        if (!string.IsNullOrWhiteSpace(inputFile))
        {
            try
            {
                LoadFromFile(inputFile); 
                isNewDiary = false;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error loading file: {ex.Message}");
                Console.WriteLine("Starting a new diary instead...");
            }
        }

        while (true)
        {
            DisplayMenu();
            int choice;
            while (!int.TryParse(Console.ReadLine(), out choice) || choice < 1 || choice > 5)
            {
                Console.Write("Invalid input. Please choose a number between 1 and 5: ");
            }

            switch (choice)
            {
                case 1:
                    AddFood(isNewDiary);
                    break;
                case 2:
                    EditFood();
                    break;
                case 3:
                    DeleteFood();
                    break;
                case 4:
                    ShowDiary();
                    break;
                case 5:
                    if(SaveBeforeExit())
                    {
                        return;
                    }
                    break;
                default:
                    break;
            }
        }
    }
     private void AddFood(bool isNewDiary)
    {
        if (isNewDiary)
        {
            Console.WriteLine("No diary loaded. Please load or create a new diary.");
            return;
        }

        Console.Write("Enter date in 'dd/MM/yyyy' format: ");
        var dateStr = Console.ReadLine();
        if (!DateTime.TryParseExact(dateStr, "dd/MM/yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out DateTime date))
        {
            Console.WriteLine("Invalid date format. Please use dd/MM/yyyy.");
            return;
        }

        var day = diary.Find(d => d.Date == date);
        if (day == null)
        {
            day = new Day { Date = date };
            diary.Add(day);
        }

        var meal = GetMealFromUser();
        if (meal != null)
        {
            day.Meals.Add(meal);
        }
    }
     private Meal GetMealFromUser()
    {
        Console.Write("Enter meal type (breakfast, lunch, dinner, snack): ");
        var mealType = Console.ReadLine().Trim().ToLower();
        if (string.IsNullOrWhiteSpace(mealType) || !ValidMealTypes.Contains(mealType))
        {
            Console.WriteLine("Invalid meal type. Please try again.");
            return null;
        }

        var meal = new Meal(mealType);
        while (true)
        {
            FoodItem foodItem = GetFoodItemFromUser();
            if (foodItem == null) break;
            meal.Items.Add(foodItem);
        }
        return meal;
    }
      private FoodItem GetFoodItemFromUser()
    {
        Console.Write("Enter food item details (or blank to finish): ");
        var input = Console.ReadLine();
        if (string.IsNullOrWhiteSpace(input)) return null;
        var values = input.Split('-');
        if (values.Length != 5)
        {
            Console.WriteLine("Invalid food item format. Please use 'name - kj - protein - carbs - fat'.");
            return null;
        }

        try
        { 
            return new FoodItem
            {
                Name = values[0].Trim(),
                Kilojoules = int.Parse(values[1].Trim()),  
                Protein = double.Parse(values[2].Trim()),  
                Carbohydrates = double.Parse(values[3].Trim()),  
                Fat = double.Parse(values[4].Trim()),               
            };
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error parsing input: {ex.Message}");
            return null;
        }
    }
     private void EditFood()
    {
         //TODO: Implement Edit Food functionality
        Console.WriteLine("Edit Food is not implemented yet.");
    }
     private void DeleteFood()
    {  
        //TODO: Implement Delete Food functionality
        Console.WriteLine("Delete Food is not implemented yet.");
    }
      private void ShowDiary()
    { 
        if (diary.Count == 0)
        {
            Console.WriteLine("No food diary entries found.");
            return;
        }
        foreach (var day in diary)  
        {
            Console.WriteLine(day);        
        }
    }
     private bool SaveBeforeExit()
    {
        Console.Write("Do you want to save your diary before exiting (y/n): ");
        char response;
        while (!char.TryParse(Console.ReadLine(), out response) || (response != 'y' && response != 'n'))
        {
            Console.Write("Invalid input. Please enter 'y' or 'n': ");
        }

        if (response == 'y')
        { 
            SaveToFile();  
        }
        return response == 'y';
    }
     private void DisplayMenu()
    {
        Console.Clear();
        Console.WriteLine("Food Diary Menu");
        Console.WriteLine("1. Add Food");
        Console.WriteLine("2. Edit Food");
        Console.WriteLine("3. Delete Food");
        Console.WriteLine("4. Show Diary");
        Console.WriteLine("5. Exit");
        Console.Write("Choose an option (1-5): ");
    }
 
     private static readonly HashSet<string> ValidMealTypes = new HashSet<string> { "breakfast", "lunch", "dinner", "snack" };
 
    public static void Main()
    {
        FoodDiary diary = new FoodDiary();         
        diary.Run();
    }
 }
