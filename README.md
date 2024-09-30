## Project by: Ron Adi & Michael Benzekri
# Summary:
This is a learning project for improving code planing, problem solving  and OOP skils.

This is a simple chess game implementation in Java.
The project includes classes that represent the game board, pieces, piece, moves, and the game logic itself.

# Features:
  *Full chess game functionality, including moves, turns, and capturing pieces.
  
  *Support for all standard chess pieces (King, Queen, Rook, Bishop, Knight, Pawn).
  
  *Basic move validation.
  
  *Visual representation of the board and moves (extendable with a GUI).
  
  *Turn-based system for player moves.

  *Resign option. 

## Game Logic Overview

# Main Classes:
  *Game: This is the main entry point of the chess game. It handles the game loop, turn management, and overall control of the game flow.

  *Board: This class represents the chessboard. It handles the placement of pieces, board state management, and the visualization of the board.

  *Pieces: This class is responsible for the different chess pieces and their respective movements and behaviors. Each piece has specific rules for how it moves on the board, which are implemented here.
  
  *Piece: This is a abstract class represnting all the individual pieces in the game (King, Queen, Rook, Bishop, Knight, Pawn).

  *Move: This class manages individual moves on the board, including validation and the effects of a move (e.g., capturing pieces, check/checkmate).

# Game Rules:
  *The game alternates turns between two players.

  *Each player can move one piece per turn.

  *Moves are validated according to chess rules (e.g., pawns can only move forward, knights move in an "L" shape).

  *The game detects check, checkmate, stalemate and all draw conditions.

# Project UML:

![image](https://github.com/user-attachments/assets/dfc6df8c-bed4-4ff9-a786-b7a072dd9aab)

# Console Board Display:

![AA50BCE0-65D6-414E-82C1-B3B237BDCFCE](https://github.com/user-attachments/assets/2f22d484-3f43-4153-8a6e-c4a8ddf41b53)

# challenges:
*In Piece class removeOwnCheck method - This is a very integral function in the program.\
Its purpose is to get a raw array of legal moves and filter from it all the moves that lead to check.\
The difficulty was to avoid entering an infinite recursion which leads to the collapse of the program.\
In addition, the difficulty was in examining each move from the raw move array which one leads to chess and which one does not.\
We solved this by creating an "experimental" board by copying the existing board and running the moves and tests on it.

*One of the difficulties was planning a large project without knowing all the small details and still maintaining a beautiful and elegant code.






  






