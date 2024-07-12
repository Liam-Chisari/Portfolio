using System;

class Program
{
    static void Main(string[] args)
    {
        // Prompt the user to enter the length of the square grid.
        Console.Write("Enter the length of the square grid: ");
        int gridSize = int.Parse(Console.ReadLine());

        // Create a 2D array to represent the grid.
        char[,] grid = new char[gridSize, gridSize];

        // Initialize the grid with boundary characters and '.' for empty spaces.
        for (int i = 0; i < gridSize; i++)
        {
            grid[i, 0] = '|';
            grid[i, gridSize - 1] = '|';
            for (int j = 1; j < gridSize - 1; j++)
            {
                grid[i, j] = '.';
            }
        }

        for (int j = 0; j < gridSize; j++)
        {
            grid[0, j] = '-';
            grid[gridSize - 1, j] = '-';
        }

        // Display the initial grid.
        DisplayGrid(grid);

        // Initialize the agent's orientation and position.
        char agent = '^';  // North
        int agentX = 0;
        int agentY = 0;

        // Place the agent in the top-left corner of the grid.
        grid[agentX, agentY] = agent;

        while (true)
        {
            // Print the current grid and prompt the user for an instruction.
            DisplayGrid(grid);
            Console.Write("Enter an instruction for the agent: ");

            string input = Console.ReadLine();

            // Validate the input.
            if (int.TryParse(input, out int moveSpaces))
            {
                // Move the agent the specified number of spaces in its current direction.
                if (moveSpaces > 0)
                {
                    MoveAgent(ref agentX, ref agentY, moveSpaces, agent, grid);
                }
                else
                {
                    Console.WriteLine("Invalid move distance. Valid inputs are: + - q");
                    ListValidInstructions();
                    continue;
                }
            }
            else if (input == "-")
            {
                // Rotate the agent 90 degrees counter-clockwise.
                RotateAgentCounterClockwise(ref agent);
            }
            else if (input == "+")
            {
                // Rotate the agent 90 degrees clockwise.
                RotateAgentClockwise(ref agent);
            }
            else if (input == "q")
            {
                // Exit the program.
                Console.WriteLine("Exiting the program.");
                break;
            }
            else
            {
                // Display an error message and list the valid inputs.
                Console.WriteLine("Invalid input. Valid inputs are: + - q");
                ListValidInstructions();
            }
        }

        // Display the final grid.
        DisplayGrid(grid);
    }

    // Helper method to display the grid.
    static void DisplayGrid(char[,] grid)
    {
        for (int i = 0; i < grid.GetLength(0); i++)
        {
            for (int j = 0; j < grid.GetLength(1); j++)
            {
                Console.Write(grid[i, j]);
            }

            Console.WriteLine();
        }
    }

    // Helper method to move the agent the specified number of spaces in the given direction.
    static void MoveAgent(ref int agentX, ref int agentY, int moveSpaces, char agent, char[,] grid)
    {
        switch (agent)
        {
            case '^':  // North
                if (agentY + moveSpaces <= grid.GetLength(1) - 1)
                {
                    // Remove the agent from its current position before moving.
                    grid[agentX, agentY] = '.';

                    agentY += moveSpaces;
                }
                else
                {
                    Console.WriteLine("Insufficient space to move north. Valid inputs are: + - q");
                    ListValidInstructions();
                    return;
                }
                break;
            case '>':  // East
                if (agentX + moveSpaces <= grid.GetLength(0) - 1)
                {
                    // Remove the agent from its current position before moving.
                    grid[agentX, agentY] = '.';

                    agentX += moveSpaces;
                }
                else
                {
                    Console.WriteLine("Insufficient space to move east. Valid inputs are: + - q");
                    ListValidInstructions();
                    return;
                }
                break;
            case '\\':  // South
                if (agentY - moveSpaces >= 1)
                {
                    // Remove the agent from its current position before moving.
                    grid[agentX, agentY] = '.';

                    agentY -= moveSpaces;
                }
                else
                {
                    Console.WriteLine("Insufficient space to move south. Valid inputs are: + - q");
                    ListValidInstructions();
                    return;
                }
                break;
            case '<':  // West
                if (agentX - moveSpaces >= 1)
                {
                    // Remove the agent from its current position before moving.
                    grid[agentX, agentY] = '.';

                    agentX -= moveSpaces;
                }
                else
                {
                    Console.WriteLine("Insufficient space to move west. Valid inputs are: + - q");
                    ListValidInstructions();
                    return;
                }
                break;
        }

        // Update the agent's position in the grid.
        grid[agentX, agentY] = agent;
    }

    // Helper method to rotate the agent 90 degrees clockwise.
    static void RotateAgentClockwise(ref char agent)
    {
        switch (agent)
        {
            case '^':  // North
                agent = '>';  // East
                break;
            case '>':  // East
                agent = '\\';  // South
                break;
            case '\\':  // South
                agent = '<';  // West
                break;
            case '<':  // West
                agent = '^';  // North
                break;
        }
    }
    static void RotateAgentCounterClockwise(ref char agent)
    {
        switch (agent)
        {
            case '^':  // North
                agent = '<';  // West
                break;
            case '>':  // East
                agent = '^';  // North
                break;
            case '\\':  // South
                agent = '>';  // East
                break;
            case '<':  // West
                agent = '\\';  // South
                break;
        }
    }

    // Helper method to list the valid inputs and their function.
    static void ListValidInstructions()
    {
        Console.WriteLine("Valid inputs are:");
        Console.WriteLine("  - An integer: The number of spaces the agent is to move in its current direction.");
        Console.WriteLine("  - -: Rotates the agent 90 degrees counter-clockwise.");
        Console.WriteLine("  - +: Rotates the agent 90 degrees clockwise.");
        Console.WriteLine("  - q: Exits the program.");
    }

}

