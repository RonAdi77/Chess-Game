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

  *The game detects check and checkmate conditions.

# UML:

![image](https://github.com/user-attachments/assets/dfc6df8c-bed4-4ff9-a786-b7a072dd9aab)



  






