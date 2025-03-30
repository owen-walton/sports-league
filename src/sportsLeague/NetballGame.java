package sportsLeague;

import java.util.Random;

public class NetballGame extends InvasionGame {
	
	public NetballGame(int gameNumber, League league)
	{
		super(gameNumber, league);
	}

	public void score(boolean isScorerHomeTeam)
	{
		super.score(isScorerHomeTeam);
		
		// add goal to team that scored
		if(isScorerHomeTeam)
		{
			setHomeTeamScore(getHomeTeamScore() + 1);
		}
		else if(!isScorerHomeTeam)
		{
			setAwayTeamScore(getAwayTeamScore() + 1);
		}
	}
	
	public void simulationScore(boolean isScorerHomeTeam)
	{
		/**
		 * Score without printing to the console
		 */
		// add goal to team that scored
		if(isScorerHomeTeam)
		{
			setHomeTeamScore(getHomeTeamScore() + 1);
		}
		else if(!isScorerHomeTeam)
		{
			setAwayTeamScore(getAwayTeamScore() + 1);
		}
	}

	public void simulate()
	{
		/**
		 * simulate a netball game
		 * (not accurate to real life predictions)
		 */
		Random rand = new Random();
		final float HOME_TEAM_SCORE_CHANCE = (float) getHomeTeam().getTeamStrength() * 0.8f; // value is out of 100 but out of 80 is wanted
		final float AWAY_TEAM_SCORE_CHANCE = (float) getAwayTeam().getTeamStrength() * 0.8f;
		
		// maximum goals 70, teams have between 0-80% chance of scoring 70 times based on their strength
		for (int i = 0; i < 70; i++)
		{
			// generate number 0-99, if number is less than score chance value, team scores. 
			// e.g if team has 20 score chance only 0-19 cause them to score
			if(rand.nextInt(100) < HOME_TEAM_SCORE_CHANCE)
			{
				simulationScore(true);
			}
		}
		
		// repeat for away team
		for (int i = 0; i < 70; i++)
		{
			if(rand.nextInt(100) < AWAY_TEAM_SCORE_CHANCE)
			{
				simulationScore(false);
			}
		}
		
		super.endGame();
	}
}
