# 2048 Game
## Overview
The 2048 Game is a popular sliding tile puzzle game implemented in Java. The game is played on an m × n grid (commonly a 4x4 grid), where each tile has a numerical value. The objective is to merge tiles with the same value by sliding them in specific directions until you reach the tile with the value 2048.

In this implementation, I have developed the core mechanics of the game, including tile sliding, merging of tiles, and game logic. The game features a command-line interface to interact with the player, where they can slide the tiles in any of the four possible directions: up, down, left, or right. The tiles with matching values merge to form higher-value tiles. The game continues until either the player reaches the 2048 tile or no more moves can be made. You can play the game through the **command-line interface (CLI)** or use the provided **Graphical User Interface (GUI)**. The GUI allows for a more intuitive, visual experience where the player can interact with the game by clicking on buttons and dragging tiles.

## Game Rules
- The grid starts with two tiles, each with a value of 2 or 4.
- Each time the player slides the tiles, a new tile (either 2 or 4) is placed randomly on the grid.
- Tiles with the same value merge into one tile with the combined value. For example, two tiles with value 2 will merge into one tile with value 4.
- Merged tiles cannot merge again in the same move.
- The game continues until the player reaches a tile with the value 2048 or there are no more moves left.

