package sportsLeague;

/**
 * 
 * This class and its child classes must not be instantiated anywhere except League.createGame()
 *
 */

public abstract class InvasionGame 
{

	// ---------------------------------------------------------------------
	// class attributes
	// ---------------------------------------------------------------------
	private final League league;
	private final Team homeTeam;
	private final Team awayTeam;
	private int homeTeamScore;
	private int awayTeamScore;
	private int gameNumber;
	// kickoff time potential future addition



	// ---------------------------------------------------------------------
	// constructor(s)
	// ---------------------------------------------------------------------
	public InvasionGame(int gameNumber, League league)
	{

		this.league = league;
		this.homeTeam = league.getFixtureList().get(gameNumber)[0];
		this.awayTeam = league.getFixtureList().get(gameNumber)[1];
		this.gameNumber = gameNumber;
		this.homeTeamScore = 0;
		this.awayTeamScore = 0;
	}



	// ---------------------------------------------------------------------
	// getters
	// ---------------------------------------------------------------------
	public League getLeague()
	{
		return this.league;
	}

	public Team getHomeTeam() 
	{
		return this.homeTeam;
	}

	public Team getAwayTeam() 
	{
		return this.awayTeam;
	}

	public int getHomeTeamScore() 
	{
		return this.homeTeamScore;
	}

	public int getAwayTeamScore() 
	{
		return this.awayTeamScore;
	}

	public int getGameNumber()
	{
		return this.gameNumber;
	}

	// ---------------------------------------------------------------------
	// setters
	// ---------------------------------------------------------------------

	public void setHomeTeamScore(int homeTeamScore )
	{
		this.homeTeamScore = homeTeamScore;
	}

	public void setAwayTeamScore(int awayTeamScore)
	{
		this.awayTeamScore = awayTeamScore;
	}

	// ---------------------------------------------------------------------
	// game play methods
	// ---------------------------------------------------------------------

	// child class will handle scoring and simulation due to different point systems in different sports
	public abstract void simulate();

	public void score(boolean isScorerHomeTeam)
	{
		if (isScorerHomeTeam)
		{
			System.out.println(getHomeTeam().toString() + " has scored.");
		} else
		{
			System.out.println(getAwayTeam().toString() + " has scored.");
		}
	}

	public void score(boolean isScorerHomeTeam, int points)
	{
		if (isScorerHomeTeam)
		{
			System.out.println(getHomeTeam().toString() + " has scored " + points + " points.");
		} else
		{
			System.out.println(getAwayTeam().toString() + " has scored " + points + " points.");
		}
	}

	// ---------------------------------------------------------------------
	// result submission
	// ---------------------------------------------------------------------
	public void endGame()
	{
		System.out.println("Game has finished.");
		System.out.println("The score was " + getHomeTeam() + " "+ getHomeTeamScore() + " - " + getAwayTeamScore() + " " + getAwayTeam() + ".\n");
		submitResult(getHomeTeamScore(), getAwayTeamScore());
	}


	public void submitResult(int homeTeamScore, int awayTeamScore)
	{
		updateResultList(homeTeamScore, awayTeamScore);
		updateTable(homeTeamScore, awayTeamScore);
	}



	public void updateResultList(int homeTeamScore, int awayTeamScore)
	{
		/**
		 * find fixture in fixtureList to work out index of resultsList array to enter scores
		 * enter scores into corresponding indexes of resultsList array
		 */
		League league = getLeague();
		Integer[][] resultList;
		int gameNumber = -1;

		gameNumber = getGameNumber();

		resultList = league.getResultList();
		resultList[gameNumber][0] = homeTeamScore;
		resultList[gameNumber][1] = awayTeamScore;

		league.setResultList(resultList);

	}

	public void updateTable(int homeTeamScore, int awayTeamScore)
	{
		/**
		 * all processes in this method must be executed 
		 * at time of calling so table isn't changed elsewhere as it is being edited
		 */
		int homeTeamIndex = -1;
		int awayTeamIndex = -1;

		final int WIN_POINTS = getLeague().getiWinPoints();
		final int DRAW_POINTS = getLeague().getiDrawPoints();
		final int LOSS_POINTS = getLeague().getiLossPoints();
		final int POINTS_INDEX = 1;

		// get league table
		Object[][] table = getLeague().getTable();

		// find both teams in table
		for (int i = 0; i < table.length; i++)
		{
			if(table[i][0].equals(getHomeTeam()))
			{
				homeTeamIndex = i;
			}
		}
		for (int i = 0; i < table.length; i++)
		{
			if(table[i][0].equals(getAwayTeam()))
			{
				awayTeamIndex = i;
			}
		}

		// compare the scores and add appropriate points
		if (homeTeamIndex == -1) // if home team has dropped out of league and forfeited game
		{
			table[awayTeamIndex][POINTS_INDEX] = (Integer)table[awayTeamIndex][POINTS_INDEX] + WIN_POINTS;
		} else if (awayTeamIndex == -1) // if away team has dropped out of league and forfeited game
		{
			table[homeTeamIndex][POINTS_INDEX] = (Integer)table[homeTeamIndex][POINTS_INDEX] + WIN_POINTS;
		} else if (homeTeamScore > awayTeamScore)
		{
			table[homeTeamIndex][POINTS_INDEX] = (Integer)table[homeTeamIndex][POINTS_INDEX] + WIN_POINTS;
			table[awayTeamIndex][POINTS_INDEX] = (Integer)table[awayTeamIndex][POINTS_INDEX] + LOSS_POINTS;

		} else if (homeTeamScore < awayTeamScore)
		{
			table[homeTeamIndex][POINTS_INDEX] = (Integer)table[homeTeamIndex][POINTS_INDEX] + LOSS_POINTS;
			table[awayTeamIndex][POINTS_INDEX] = (Integer)table[awayTeamIndex][POINTS_INDEX] + WIN_POINTS;
		} else
		{
			table[homeTeamIndex][POINTS_INDEX] = (Integer)table[homeTeamIndex][POINTS_INDEX] + DRAW_POINTS;
			table[awayTeamIndex][POINTS_INDEX] = (Integer)table[awayTeamIndex][POINTS_INDEX] + DRAW_POINTS;
		}

		table = sortTableDescending(table, POINTS_INDEX);

		league.setTable(table);
	}

	private Object[][] sortTableDescending(Object[][] table, int sortByIndex)
	{
		/**
		 * used bubble sort
		 * potentially optimise by changing to merge sort in future
		 */
		for (int i = table.length - 1; i > 0; i--)
		{
			for (int j = 0; j < i; j++)
			{
				if((Integer) table[j][sortByIndex] < (Integer) table[j + 1][sortByIndex])
				{
					Object[] temp = table[j];
					table[j] = table[j + 1];
					table[j + 1] = temp;
				}
			}
		}

		return table;
	}
}