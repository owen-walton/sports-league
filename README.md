# Sports League

## Purpose

Allow a round-robin style league to be created with various sports, enabling teams to compete in simulated matches while tracking standings and results.

## Current Issues

- League.java and InvasionGame.java currently have a circular dependency/tight coupling which should be fixed
  - For example .updateLeagueTable() should be withing league class not InvasionGame class
- Game simulation is very inaccurate however this is unlikely to be changed as it is not the main focus of the project
- Curly brackets '{' are inconsistently on the same or new line from a function due to attempting to break habit
- Main doesn't currently utilise all functionality for a comprehensive display of the programs functionality

## Future Improvements

- Add to functionality of stadium and ground classes including tickets, revenue, bar sales, etc
- Expand to include more sports.
- Implement a user-friendly GUI.
- Utilise a merge sort to replace the bubble sort for ranking teams more efficiently

## Features

- **League Management:**
  - Create and manage leagues with different sports (Football, Rugby, Netball).
  - Add or remove teams from leagues.
  - Maintain league standings and fixtures.
  
- **Game Simulation:**
  - Play matches between teams.
  - Track scores and update league standings.
  - Simulate matches with random outcomes based on team strength.

- **Customizable Teams & Stadiums:**
  - Define teams with unique strengths and home venues (either a `Ground` or `Stadium`).
  - Adjust team strengths to influence match outcomes.

- **Venue Management:**
  - Teams can have a home venue, which can be either a `Ground` or a `Stadium`.
  - `Stadium` is a subclass of `Ground`, with additional features like capacity and ticket price (currently capacity, ticket price, etc are not used functionally).


## Key Classes

### `League.java`
This class manages all aspects of a sports league, including team registration, match scheduling, and leaderboard management. It allows adding and removing teams, recording match results, and displaying league standings.

### `InvasionGame.java`
`InvasionGame` is an abstract class that serves as the base for sports that involve scoring by moving into the opponent***s territory (e.g., Football, Rugby, and Netball). It includes scoring mechanisms, team assignments, and game simulations.

### Other Classes
- `FootballGame.java` - Implements football-specific rules and scoring.
- `NetballGame.java` - Implements netball-specific rules and scoring.
- `RugbyGame.java` - Implements rugby-specific rules and scoring.
- `Team.java` - Represents a team in the league, with attributes for name, strength, and venue (either a `Ground` or `Stadium`). Provides methods to manage team properties.
- `Ground.java` - Represents a venue (e.g., a ground or stadium) where a team plays its home games, with a name attribute.
- `Stadium.java` - Subclass of `Ground`. Represents a stadium, potentially with additional features like capacity and ticket prices (to be implemented later).
- `Main.java` - Demonstrates the functionality of the system with a `Six Nations` rugby league.
- `Sports.java` - Enum listing available sports (Football, Rugby, Netball).

## Functions That Main.java May Utilise

### From `League` Class:
- `League(String name, Sports sport, LeagueType leagueType, int winPoints, int drawPoints, int lossPoints, boolean isStadiumRequired, int forfeitGoalDifference)`
- `InvasionGame game = League.playNextGame()`
- `League.simulateNextGame()`
- `League.enterResult(int homeTeamScore, int awayTeamScore, int gameNumber)`
- `League.enterNextResult(int homeTeamScore, int awayTeamScore)`
- `League.finaliseLeague()`
- `League.addTeam(Team team)`
- `League.removeTeam(Team team)`
- `League.dropOutOfLeague(Team team)`
- `League.findTeamStanding(Object[][] arr, Team team)`
- `League.displayTable()`
- `League.displayFixtureList()`
- `League.displayTeamList()`

### From `InvasionGame` Children (Replace `InvasionGame` with the actual child class name):
- `InvasionGame(int gameNumber, League league)`
- `FootballGame.score(boolean isScorerHomeTeam)`
- `NetballGame.score(boolean isScorerHomeTeam)`
- `RugbyGame.score(boolean isScorerHomeTeam, int points)`
- `InvasionGame.endGame()`

### From `Team` Class:
- `Team(String name, int strength, Ground ground)`
- `Team(String name, int strength, Stadium stadium)`
- `Team.setGround(Ground ground)`
- `Team.setStadium(Stadium stadium)`
- `Team.setTeamStrength(int strength)`
- `Team.displayTeam()`

### Other Constructors:
- `Ground(String name)`
- `Stadium(String name, int capacity, float ticketPrice)`

## How to Run

1. Clone the repository:
   ```sh
   git clone https://github.com/owen-walton/sports-league.git

2. Compile the project:
   ```sh
   javac sportsLeague/*.java

3. Run the main method:
   ```sh
   java sportsLeague.Main

## Background Reading

- **Berger Tables Algorithm** (Used for creation of the round-robin fixture list)

## Author

[Owen Walton](https://github.com/owen-walton)

## License

This code is open-source and if it may provide any help please feel free to use it, if you do use my code please do let me know.
