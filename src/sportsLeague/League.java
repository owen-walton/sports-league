package sportsLeague;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class League 
{
	// =====================================================================
	// class attributes
	// =====================================================================
	private String szName;
	private Sports sport;
	private final int i_WIN_POINTS;
	private final int i_DRAW_POINTS;
	private final int i_LOSS_POINTS;
	private List<Team> teamList;
	private Object[][] table;
	private boolean bTeamsFinalised;
	private boolean isStadiumRequired;
	private int gameNumber;
	private List<Team[]> fixtureList; // index 0 in Team array is home team, index 1 is away team
	private Integer[][] resultList; // index 0 in every row is home team score, index 1 in every row is away team score
	private int forfeitGoalDifference;

	// =====================================================================
	// constructor(s)
	// =====================================================================
	public League(String name, Sports sport, int winPoints, int drawPoints, int lossPoints, boolean isStadiumRequired, int forfeitGoalDifference) 
	{

		this.szName = name;
		this.sport = sport;
		this.i_WIN_POINTS = winPoints;
		this.i_DRAW_POINTS = drawPoints;
		this.i_LOSS_POINTS = lossPoints;
		this.isStadiumRequired = isStadiumRequired;
		this.teamList = new ArrayList<>();
		this.gameNumber = 0;
		this.forfeitGoalDifference = forfeitGoalDifference;

	}


	// =====================================================================
	// getters
	// =====================================================================
	public int getiWinPoints() 
	{
		return this.i_WIN_POINTS;
	}

	public int getiDrawPoints() 
	{
		return this.i_DRAW_POINTS;
	}

	public int getiLossPoints() 
	{
		return this.i_LOSS_POINTS;
	}

	public boolean getIsStadiumRequired() 
	{
		return this.isStadiumRequired;
	}


	public List<Team> getTeamList()
	{
		return this.teamList;
	}

	public Object[][] getTable()
	{
		return this.table;
	}

	public Sports getSport()
	{
		return this.sport;
	}

	public List<Team[]> getFixtureList()
	{
		return this.fixtureList;
	}

	public Integer[][] getResultList()
	{
		return this.resultList;
	}

	public boolean getbTeamsFinalised()
	{
		return this.bTeamsFinalised;
	}

	public int getGameNumber()
	{
		return this.gameNumber;
	}

	public String getName()
	{
		return this.szName;
	}

	public int getForfeitGoalDifference()
	{
		return this.forfeitGoalDifference;
	}

	// =====================================================================
	// setters
	// =====================================================================
	public void setTable(Object[][] table)
	{
		this.table = table;
	}

	public void setResultList(Integer[][] resultList)
	{
		this.resultList = resultList;
	}

	private void setGameNumber(int gameNumber)
	{
		this.gameNumber = gameNumber;
	}

	private void setbTeamsFinalised(boolean bTeamsFinalised)
	{
		this.bTeamsFinalised = bTeamsFinalised;
	}

	private void setFixtureList(List<Team[]> fixtureList)
	{
		this.fixtureList = fixtureList;
	}

	// =====================================================================
	// methods:
	// =====================================================================

	// ---------------------------------------------------------------------
	// game play
	// ---------------------------------------------------------------------
	public InvasionGame createGame(int gameNumber)
	{
		/**
		 * THIS SHOULD BE ONLY METHOD IN PROGRAM ALLOWED TO CREATE INSTANCE OF INVASION GAME AND ITS CHILD CLASSES
		 * 
		 * Creates the game instance so the different methods of finding the result of the game can be utilised
		 */
		if (gameNumber < 0 || gameNumber >= getFixtureList().size()) 
		{
			System.out.println("Invalid gameNumber");

			return null;
		}

		InvasionGame game;

		switch(sport)
		{
		case RUGBY: 
			game = new RugbyGame(gameNumber, this);
			break;
		case FOOTBALL:
			game = new FootballGame(gameNumber, this);
			break;
		case NETBALL:
			game = new NetballGame(gameNumber, this);
			break;
		default:
			game = null;
			break;

		}

		return game;
	}

	public InvasionGame playNextGame() 
	{
		InvasionGame game = null;

		if(!checkLeagueFinished())
		{
			System.out.println("Game between " + getFixtureList().get(gameNumber)[0] + " and " + getFixtureList().get(gameNumber)[1] + " has started.");
			game = createGame(getGameNumber());

			incrementGameNumber();
		} else
		{
			System.out.println("League is finished");
		}

		return game;
	}

	public void simulateNextGame()
	{
		if(!checkLeagueFinished())
		{
			createGame(getGameNumber()).simulate();

			incrementGameNumber();
		} else
		{
			System.out.println("League is finished\n");
		}
	}

	// ------ FUNCTION IS DESIGNED TO ONLY ALLOW ENTRY OF GAMES THAT HAVE NOT PREVIOUSLY BEEN ENTERED ---
	public void enterResult(int homeTeamScore, int awayTeamScore, int gameNumber)
	{
		if(!checkLeagueFinished())
		{
			// if game is next game on schedule
			if(gameNumber == getGameNumber())
			{
				enterNextResult(homeTeamScore, awayTeamScore);

				// else if gameNumber is a valid number
			} else if (gameNumber < getFixtureList().size() && gameNumber >= 0)
			{
				// nest if statement so error isn't thrown if number is invalid
				// if game isn't already entered
				if (getResultList()[getGameNumber()][0] == null)
				{
					createGame(gameNumber).submitResult(homeTeamScore, awayTeamScore);
				}
				else
				{
					System.out.println("That result is already entered.");
				}
			} else
			{
				System.out.println("This league doesn't have a game number " + gameNumber);
			}
		} else
		{
			System.out.println("League is finished\n");
		}
	}

	public void enterNextResult(int homeTeamScore, int awayTeamScore)
	{
		if(!checkLeagueFinished())
		{
			createGame(getGameNumber()).submitResult(homeTeamScore, awayTeamScore);
			incrementGameNumber();
		} else
		{
			System.out.println("League is finished\n");
		}
	}

	private void incrementGameNumber()
	{
		// once game is created, game number increments so next game to be created is next game on schedule
		// if next game has already been entered out of order, increment again
		do
		{
			setGameNumber(getGameNumber() + 1);

			if (getGameNumber() >= getResultList().length) 
			{
				break;  // Break the loop if it goes out of bounds
			}
		} while(getResultList()[getGameNumber()][0] != null);

	}

	private boolean checkLeagueFinished()
	{
		boolean bFinished;

		// check if league is finished
		if (getGameNumber() >= getFixtureList().size())
		{
			bFinished = true;
		} else
		{
			bFinished = false;
		}

		return bFinished;
	}
	// ---------------------------------------------------------------------
	// team administration
	// ---------------------------------------------------------------------
	public void finaliseLeague()
	{
		int iNumOfTeams = getTeamList().size();

		if (!getbTeamsFinalised() && iNumOfTeams > 1)
		{

			this.setbTeamsFinalised(true);

			// length 2 refers to "Team object, numOfPoints", can add values to table later e.g goal difference
			Object[][] tempTable = new Object[iNumOfTeams][2]; 

			// initialise array
			for (int i = 0; i < iNumOfTeams; i++)
			{
				// add team all teams to index 1 and set all points to 0
				tempTable[i][0] = getTeamList().get(i);
				tempTable[i][1] = 0;
			}

			setTable(tempTable);

			int iNumOfFixtures = createFixtureList(iNumOfTeams);

			// initialise result list 
			// don't assign values to the variables in array, hence all games not entered can be represented by null
			setResultList(new Integer[iNumOfFixtures][2]);
			
			System.out.println("League has been finalised.\n");


		} else if (getbTeamsFinalised())
		{
			System.out.println("Teams are already finalised.\n");

		} else
		{
			System.out.println("Not enough teams are in the league.\n");
		}

	}

	private int createFixtureList(int numOfTeams)
	{
		List<Team> teams = new ArrayList<>(getTeamList());
		// shuffles the list so the fixture list is randomised despite being the same structure each time
		Collections.shuffle(teams);
		List<Team[]> fixtureList = new ArrayList<>();

		// if the number of teams is odd, add a bye
		if (numOfTeams % 2 == 1) 
		{
			teams.add(new Team("Bye", 0, null));
			// include bye in value of numOfTeams
			numOfTeams++;
		}

		int numOfRounds = numOfTeams - 1;

		// loop through each game in each round
		for (int i = 0; i < numOfRounds; i++) 
		{
			for (int j = 0; j < numOfTeams / 2; j++) 
			{
				Team homeTeam = teams.get(j);
				Team awayTeam = teams.get(numOfTeams - 1 - j);

				// do not add if it is a bye game
				if (!homeTeam.toString().equals("Bye") && !awayTeam.toString().equals("Bye")) {
					fixtureList.add(new Team[]{homeTeam, awayTeam});
				}

			}

			// shift all teams up 1 not including the team at index 0 (fixed)
			Team lastTeam = teams.remove(numOfTeams - 1);
			teams.add(1, lastTeam);
		}

		setFixtureList(fixtureList);

		// fixtureNumber stores the number of games without byes
		return fixtureList.size();
	}

	public void addTeam(Team team)
	{

		if (!getTeamList().contains(team))
		{
			if (!getbTeamsFinalised())
			{	
				if (!getIsStadiumRequired() || team.getHasStadium())
				{
					getTeamList().add(team);
					
					System.out.println(team + " has been added to " + getName());

				} else
				{
					System.out.println(team + " does not have a stadium and " + getName() + " requires one so they could not be added");
				}
			}
			else
			{
				System.out.println("Team can't be added, this league has been finalised.");
			}
		} else
		{
			System.out.println("This team is already in the league.");
		}


	}

	public void removeTeam(Team team)
	{
		if (!getbTeamsFinalised())
		{
			if (getTeamList().remove(team))
			{
				System.out.println(team + " has been removed from the league.");
			}
		}
		else
		{
			System.out.println("This league has been finalised. " + team + " has now been dropped out of the tournament.");

			dropOutOfLeague(team);

		}
	}

	public void dropOutOfLeague(Team team)
	{
		/**
		 *  edit league table to ensure team is dropped from league
		 *  and add method to ensure future fixtures are considered a bye (auto win for opponent)
		 */
		removeFromTable(team);
		getTeamList().remove(team);
		forfeitFixtures(team);
		
		System.out.println(team + " has dropped out of league.");

	}

	private void forfeitFixtures(Team team)
	{
		for (int i = 0; i < getFixtureList().size(); i++)
		{
			if (getFixtureList().get(i)[0].equals(team))
			{
				enterResult(0, getForfeitGoalDifference(), i);
			} else if (getFixtureList().get(i)[1].equals(team))
			{
				enterResult(getForfeitGoalDifference(), 0, i);
			}
		}
	}

	private void removeFromTable(Team team)
	{
		if (getbTeamsFinalised() && getTeamList().contains(team))
		{
			Object[][] table = getTable();
			Object[][] newTable = new Object[table.length - 1][2];
			int index = findTeamStanding(table, team);

			for (int i = 0; i < index; i++)
			{
				newTable[i] = table[i];
			}
			for (int i = index + 1; i < table.length; i++)
			{
				newTable[i - 1] = table[i];
			}

			this.setTable(newTable);
		}
	}

	public int findTeamStanding(Object[][] arr, Team team)
	{
		int index = -1;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i][0].equals(team))
				index = i;
		}

		return index;
	}


	public void displayTable()
	{
		System.out.println(getName() + ":");
		System.out.println("-------------------------------------");
		System.out.printf("%-20s | %10s%n", "Team", "Points");
		System.out.println("---------------------|---------------");

		for (int i = 0; i < getTable().length; i++)
		{
			System.out.printf("%-20s | %10d", table[i][0], table[i][1]);
			System.out.println();
		}
	}

	public void displayFixtureList()
	{
		List<Team[]> fixtureList = getFixtureList();

		System.out.println(getName() + " Fixtures and Results:\n");
		
		// print all results
		System.out.println("Results:");
		for (int i = 0; i < fixtureList.size(); i++) 
		{
			if (getResultList()[i][0] != null)
			{
				Team[] fixture = fixtureList.get(i);
				String homeTeam = fixture[0].toString();
				String awayTeam = fixture[1].toString();


				if (!getTeamList().contains(fixture[0]))
				{
					homeTeam = homeTeam + "(folded)";
				}
				if (!getTeamList().contains(fixture[1]))
				{
					awayTeam = awayTeam + "(folded)";
				}

				System.out.println(homeTeam + " " + getResultList()[i][0] + " - " + getResultList()[i][1] + " "  + awayTeam);
			}
		}
		System.out.println();
		// print all upcoming fixtures
		System.out.println("Fixtures:");
		for (int i = 0; i < fixtureList.size(); i++) 
		{
			if (getResultList()[i][0] == null)
			{
				Team[] fixture = fixtureList.get(i);
				System.out.println(fixture[0].toString() + " vs " + fixture[1].toString());
			}
		}

		System.out.println();
	}

	public void displayTeamList()
	{
		System.out.println(getName() + " Team List:");
		// display name values of objects in teamList
		for (int i = 0; i < getTeamList().size(); i++)
		{
			System.out.println(getTeamList().get(i).toString());
		}
		System.out.println();
	}
}
