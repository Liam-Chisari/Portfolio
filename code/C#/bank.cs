using System;
using System.Collections.Generic;

public enum AccountType
{
   Savings,
   Transaction
}

public class BankAccount
{
   public string AccountHolder { get; set; }
   public string AccountNumber { get; set; }
   public string BSB { get; set; }
   public decimal Balance { get; set; }
   public AccountType AccountType { get; set; }

   public override string ToString()
   {
       return $"Account Holder: {AccountHolder}\nAccount Number: {AccountNumber}\nBSB: {BSB}\nBalance: ${Balance:F2}\nAccount Type: {AccountType}";
   }
}

public class BankProgram
{
   private List<BankAccount> accounts = new List<BankAccount>();

   // Initialize the bank accounts (dummy data)
   public BankProgram()
   {
       InitializeAccounts();
   }

   private void InitializeAccounts()
   {
       accounts.Add(new BankAccount
       {
           AccountHolder = "Alice Johnson",
           AccountNumber = "123456",
           BSB = "080099",
           Balance = 1000.00M,
           AccountType = AccountType.Savings
       });

       // Add more account data for four more accounts
       accounts.Add(new BankAccount { AccountHolder = "Bob Smith", AccountNumber = "678901", BSB = "090011", Balance = 500.50M, AccountType = AccountType.Transaction });  
       accounts.Add(new BankAccount { AccountHolder = "Charlie Brown", AccountNumber = "246810", BSB = "060022", Balance = 3000.75M, AccountType = AccountType.Savings });   
       accounts.Add(new BankAccount { AccountHolder = "Emma Davis", AccountNumber = "456728", BSB = "070033", Balance = 2500.00M, AccountType = AccountType.Transaction });  
       accounts.Add(new BankAccount { AccountHolder = "Frank Miller", AccountNumber = "333444", BSB = "050088", Balance = 800.80M, AccountType = AccountType.Savings });   
   }

   public void Run()
   {
       bool exit = false;

       while (!exit)
       {
           DisplayMenu();
           int choice = GetUserChoice();
           exit = HandleMenuChoice(choice);
       }

       Console.WriteLine("Thank you for using our banking program!");
   }

   private void DisplayMenu()
   {
       Console.Clear();
       Console.WriteLine("Welcome to the Simple Bank Menu");
       Console.WriteLine("-----------------------------");
       Console.WriteLine("1: Show Accounts");
       Console.WriteLine("2: Transfer Money");
       Console.WriteLine("3: Exit");
       Console.WriteLine("Please enter your choice: ");
   }

   private int GetUserChoice()
   {
       int choice;
       while (!int.TryParse(Console.ReadLine(), out choice) || choice < 1 || choice > 3)
       {
           Console.WriteLine("Invalid input. Please enter a valid choice (1, 2, or 3): ");
       }

       return choice;
   }

   private bool HandleMenuChoice(int choice)
   {
       switch (choice)
       {
           case 1:
               ShowAccounts();
               break;
           case 2:
               TransferMoney();
               break;
           case 3:
               return true;    // Exit the program
           default:
               return false;
       }
       return false;
   }

   private void ShowAccounts()
   {
       Console.WriteLine("\n--- All Accounts ---");
       foreach (var account in accounts)
       {
           Console.WriteLine(account);  
       }
       Console.WriteLine("--------------------");
   }

   private void TransferMoney()
   {
       Console.Write("Enter the source account number: ");
       string sourceAccountNumber = Console.ReadLine();
       Console.Write("Enter the destination account number: ");
       string destinationAccountNumber = Console.ReadLine();
       Console.Write("Enter the amount to transfer: $");
       decimal transferAmount = decimal.Parse(Console.ReadLine());

       BankAccount sourceAccount = accounts.Find(a => a.AccountNumber == sourceAccountNumber);
       BankAccount destinationAccount = accounts.Find(a => a.AccountNumber == destinationAccountNumber);

       if (ValidateTransfer(sourceAccount, destinationAccount, transferAmount))
       {
           sourceAccount.Balance -= transferAmount;
           destinationAccount.Balance += transferAmount;
           Console.WriteLine("Transfer successful!");
       }
   }

   private bool ValidateTransfer(BankAccount sourceAccount, BankAccount destinationAccount, decimal transferAmount)
   {
       if (sourceAccount == null)
       {
           Console.WriteLine("Source account number is invalid.");
       }
       else if (destinationAccount == null)
       {
           Console.WriteLine("Destination account number is invalid.");
       }
       else if (sourceAccount == destinationAccount)
       {
           Console.WriteLine("Source and destination accounts must be different.");
       }
       else if (transferAmount <= 0)
       {
           Console.WriteLine("Transfer amount must be positive.");
       }
       else if (sourceAccount.Balance < transferAmount)
       {
           Console.WriteLine("Insufficient balance in the source account.");
       }
       else
       {
           return true;
       }

       return false;
   }

   public static void Main()
   {
       var bank = new BankProgram();
       bank.Run();
   }
}
