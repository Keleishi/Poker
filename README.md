# Poker Game
1. [Usage](#usage)
2. [Playing the game](#playing-the-game)

This is a command-line poker game written in Java. It allows multiple players to play Texas Hold'em style poker, with basic betting rounds and hand evaluation.
## Usage
***
When you run the game, you will be prompted to enter the number of players, the minimum bet amount, and the starting chips for each player. After that, the game will start.

## Playing the game
***
Each player is dealt two cards at the beginning of the game, and then there are rounds of betting and community cards revealed. To make a bet, enter the number corresponding to the action you want to take when prompted. The possible actions are:
* **Fold**: The player discards their hand and forfeits any chips they've bet so far.
* **Call**: The player bets the amount needed to match the highest * bet so far.
* **Raise**: The player bets an amount higher than the highest bet so far.
* **Show hand**: The player reveals their hand.

### Ending
***
The game ends when all but one player has folded, or when all rounds of betting have been completed and it's time to reveal the hands. The player with the best hand wins the pot.

