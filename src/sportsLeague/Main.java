package sportsLeague;

/**
 * 
 * @author Owen
 *
 * To-Do: 
 * print output where comments require it
 * showcase everything in main
 * final check
 *
 */

public class Main 
{

	public static void main(String[] args) 
	{

		League sixNations = new League("Six Nations", Sports.RUGBY, 3, 1, 0, true, 20);
		
		Stadium twickenham = new Stadium("Twickenham", 50000, 40.0f);
		Team england = new Team("England", 90, twickenham);
		
		Stadium avivaStadium = new Stadium("Aviva Stadium", 40000, 45.0f);
		Team ireland = new Team("Ireland", 50, avivaStadium);
		
		Stadium stadeDeFrance = new Stadium("Stade De France", 40000, 45.0f);
		Team france = new Team("France", 40, stadeDeFrance);
		
		Stadium principalityStadium = new Stadium("Principality Stadium", 40000, 45.0f);
		Team wales = new Team("Wales", 10, principalityStadium);
		
		Stadium hampdenPark = new Stadium("Hampden Park", 30000, 30.0f);
		Team scotland = new Team("Scotland", 10, hampdenPark);
		

		england.displayTeam();
		
		sixNations.addTeam(england);
		sixNations.addTeam(france);
		sixNations.addTeam(ireland);
		sixNations.addTeam(wales);
		sixNations.addTeam(scotland);
		
		sixNations.finaliseLeague();
		
		sixNations.displayTeamList();
		
		sixNations.dropOutOfLeague(wales);
		
		sixNations.displayTeamList();
		
		RugbyGame game = (RugbyGame) sixNations.playNextGame();
		game.score(true, 5);
		game.score(true, 7);
		game.score(false, 7);
		game.endGame();
		
		sixNations.simulateNextGame(); // will result in 0-0 for now
		
		sixNations.enterNextResult(15, 7);
		
		sixNations.displayFixtureList();
		
		sixNations.displayTable();
	}

}

