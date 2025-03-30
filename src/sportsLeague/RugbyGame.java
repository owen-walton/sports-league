package sportsLeague;

import java.util.Random;

public class RugbyGame extends InvasionGame {

	public RugbyGame(int gameNumber, League league)
	{
		super(gameNumber, league);
	}

	public void score(boolean isScorerHomeTeam, int points)
	{	
		super.score(isScorerHomeTeam, points);

		// check type of score e.g try, conversion, penalty
		if(points == 5 || points == 3 || points == 7 || points == 2)
		{
			// add correct amount of points to team that scored
			if(isScorerHomeTeam)
			{
				setHomeTeamScore(getHomeTeamScore() + points);
			}
			else if(!isScorerHomeTeam)
			{
				setAwayTeamScore(getAwayTeamScore() + points);
			}
		}
		else
		{
			System.out.println("You can't score " + points + " points in rugby"); 
		}
	}

	public void simulate()
	{
		/**
		 * a basic game simulation algorithm specific to rugby (not accurate to real life predictions)
		 * that creates numbers to put into .setHomeTeamScore() and .setAwayTeamScore() at end of method
		 */
		Random rand = new Random();
		final float HOME_TEAM_STRENGTH = (float) getHomeTeam().getTeamStrength(); // value is out of 100 but out of 50 is wanted
		final float AWAY_TEAM_STRENGTH = (float) getAwayTeam().getTeamStrength();
		final float TRY_CHANCE = 0.7f;
		final float KICK_CHANCE = 0.95f;
		int homeTeamScore = 0;
		int awayTeamScore = 0;


		// maximum 9 tries, teams have between 0-70% chance of scoring 6 times based on their strength
		for (int i = 0; i < 9; i++)
		{
			// generate number 0-99, if number is less than score chance value, team scores. 
			// e.g if team has 20 score chance only 0-19 cause them to score
			if(rand.nextInt(100) < HOME_TEAM_STRENGTH * TRY_CHANCE)
			{
				homeTeamScore += 5;
				
				// if try is scored, simulate whether it is converted (2 points)
				if(rand.nextInt(100) < HOME_TEAM_STRENGTH * KICK_CHANCE)
				{
					homeTeamScore += 2;
				}

			}
		}

		// maximum 6 penalties (3 points)
		for (int i = 0; i < 9; i++)
		{
			if(rand.nextInt(100) < HOME_TEAM_STRENGTH * KICK_CHANCE)
			{
				homeTeamScore += 3;
			}
		}

		// repeat for away team
		for (int i = 0; i < 9; i++)
		{
			if(rand.nextInt(100) < AWAY_TEAM_STRENGTH * TRY_CHANCE)
			{
				awayTeamScore += 5;
				
				if(rand.nextInt(100) < AWAY_TEAM_STRENGTH * KICK_CHANCE)
				{
					awayTeamScore += 2;
				}

			}
		}

		for (int i = 0; i < 9; i++)
		{
			if(rand.nextInt(100) < AWAY_TEAM_STRENGTH * KICK_CHANCE)
			{
				awayTeamScore += 3;
			}
		}

		setHomeTeamScore(homeTeamScore);
		setAwayTeamScore(awayTeamScore);
		
		super.endGame();
	}
}
