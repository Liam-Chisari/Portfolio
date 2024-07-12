def fibonacci(n):
    if n < 0:
        return 0
    elif n == 0 or n == 1:
        return n
    else:
        return fibonacci(n-1) + fibonacci(n-2)

def write_fibonacci_sequence_to_file(n):
    # Open the file in write mode
    file = open("fibonacci_sequence.txt", "w")

    # Write the Fibonacci sequence to the file
    for i in range(n + 1):
        file.write(str(fibonacci(i)) + "\n")

    # Close the file
    file.close()

def main():
    # Prompt the user for an integer
    n = int(input("Enter an integer: "))

    # Print the Fibonacci sequence to the console
    print("Fibonacci Sequence:")
    for i in range(n + 1):
        print(fibonacci(i), end=" ")
    print()

    # Write the Fibonacci sequence to a text file
    write_fibonacci_sequence_to_file(n)

    print("Fibonacci sequence written to fibonacci_sequence.txt.")

if __name__ == "__main__":
    main()
