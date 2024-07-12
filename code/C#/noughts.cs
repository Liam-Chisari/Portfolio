using System;

class Program
{
    static readonly int boardSize = 3;
    static readonly char humanToken = 'X';
    static readonly char aiToken = 'O';
    static char[,] board = new char[boardSize, boardSize];
    static bool humanTurn = true;

    static void Main()
    {
        InitializeBoard();
        while (!IsGameOver())
        {
            DisplayBoard();
            MakeMove();
        }
        DisplayBoard();
        DeclareWinner();
    }

    static void InitializeBoard()
    {
        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                board[i, j] = '-';
            }
        }
    }

    static void DisplayBoard()
    {
        Console.WriteLine("  1 2 3");
        Console.WriteLine("1 " + board[0, 0] + " " + board[0, 1] + " " + board[0, 2]);
        Console.WriteLine("2 " + board[1, 0] + " " + board[1, 1] + " " + board[1, 2]);
        Console.WriteLine("3 " + board[2, 0] + " " + board[2, 1] + " " + board[2, 2]);
    }


static void MakeMove()
{
    char token = humanTurn ? humanToken : aiToken;
    int row, col;

    if (humanTurn)
    {
        Console.Write("Enter your move (row, column): ");
        string input = Console.ReadLine();
        string[] coords = input.Split(',');
        row = int.Parse(coords[0]);
        col = int.Parse(coords[1]);
    }
    else
    {
        (row, col) = GetAIMove();
    }

    board[row - 1, col - 1] = token;
    humanTurn = !humanTurn;
}


static int ReadInput(int min, int max)
{
    int input;
    while (true)
    {
        string inputStr = Console.ReadLine();
        string[] coords = inputStr.Split(',');
        if (coords.Length != 2 || !int.TryParse(coords[0], out input) || input < min || input > max || !int.TryParse(coords[1], out int col) || col < min || col > max)
        {
            Console.Write("Invalid input. Please enter a valid move in the format 'row,column': ");
        }
        else
        {
            break;
        }
    }
    return input;
}


    static (int, int) GetAIMove()
    {
        // Simple AI strategy: random move
        int row = RandomNumber(1, boardSize);
        int col = RandomNumber(1, boardSize);
        while (board[row - 1, col - 1] != '-')
        {
            row = RandomNumber(1, boardSize);
            col = RandomNumber(1, boardSize);
        }
        return (row, col);
    }

    static int RandomNumber(int min, int max)
    {
        return new Random().Next(min, max);
    }


static bool IsGameOver()
{
    for (int i = 0; i < boardSize; i++)
    {
        if (board[i, 0] == board[i, 1] && board[i, 1] == board[i, 2] && board[i, 0] != '-')
        {
            return true;
        }
        if (board[0, i] == board[1, i] && board[1, i] == board[2, i] && board[0, i] != '-')
        {
            return true;
        }
    }
    if (board[0, 0] == board[1, 1] && board[1, 1] == board[2, 2] && board[0, 0] != '-')
    {
        return true;
    }
    if (board[2, 0] == board[1, 1] && board[1, 1] == board[0, 2] && board[2, 0] != '-')
    {
        return true;
    }
    for (int i = 0; i < boardSize; i++)
    {
        for (int j = 0; j < boardSize; j++)
        {
            if (board[i, j] == '-')
            {
                return false;
            }
        }
    }
    return true; // Draw
}

    static void DeclareWinner()
    {
        if (IsBoardFull())
        {
            Console.WriteLine("It's a draw!");
        }
        else
        {
            Console.WriteLine("The winner is " + (humanTurn ? "the human" : "the AI"));
        }
    }

    static bool IsBoardFull()
    {
        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                if (board[i, j] == '-')
                {
                    return false;
                }
            }
        }
        return true;
    }
}
