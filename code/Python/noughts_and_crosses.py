import random

# The board is a 3x3 list
board = [[' ' for i in range(3)] for j in range(3)]

# Winning conditions (horizontal, vertical, diagonal)
winning_conditions = [
    # Horizontal
    (0, 0, 0, 1, 1, 2, 2), (0, 1, 0, 2, 1, 2, 2),
    # Vertical
    (0, 0, 1, 0, 1, 2, 0), (0, 1, 2, 0, 1, 2, 0),
    # Diagonal
    (0, 0, 2, 1, 1, 2, 0), (0, 2, 2, 1, 1, 0, 0)
]



def is_board_full(board):
    # Check if all the spaces are filled
    for row in board:
        if ' ' in row:
            return False
    return True

def is_winner(board, player):
    # Check if any of the winning conditions match for the given player
    for condition in winning_conditions:
        if board[condition[0]][condition[1]] == player and \
           board[condition[2]][condition[3]] == player and \
           board[condition[4]][condition[5]] == player and \
           board[condition[6]][condition[7]] == player:
            return True
    return False


def display_board(board):
    print('-----------------')
    for row in board:
        print('| ' + ' | '.join(row) + ' |')
        print('-----------------')

def make_ai_move(board):
    # Get a list of all possible moves (empty spaces)
    possible_moves = []
    for i in range(3):
        for j in range(3):
            if board[i][j] == ' ':
                possible_moves.append((i, j))

    # Choose a random move from the possible moves
    move = random.choice(possible_moves)
    return move

def make_human_move(board):
    while True:
        row = int(input("Enter the row (1-3): ")) - 1
        col = int(input("Enter the column (1-3): ")) - 1
        if board[row][col] == ' ':
            return (row, col)
            break
        else:
            print("That space is already taken. Please choose another one.")

def play_game():
    # Initialize the board
    board = [[' ' for i in range(3)] for j in range(3)]

    # Select the player to take the first turn randomly
    player1 = 'X'
    player2 = 'O'
    if random.randint(0, 1) == 0:
        current_player, next_player = player1, player2
    else:
        current_player, next_player = player2, player1

    while not is_board_full(board) and not is_winner(board, player1) and not is_winner(board, player2):
        display_board(board)

        if current_player == 'X':
            move = make_human_move(board)
        else:
            move = make_ai_move(board)

        board[move[0]][move[1]] = current_player

        # Switch to the next player
        current_player, next_player = next_player, current_player

    display_board(board)

    if is_winner(board, player1):
        print("Player 1 (X) wins!")
    elif is_winner(board, player2):
        print("Player 2 (O) wins!")
    else:
        print("It's a draw!")

play_game()
