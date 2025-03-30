package sportsLeague;

/**
 * 
 * Teams can currently only have one ground - potential future adaptation
 *
 */

public class Team {


	// ---------------------------------------------------------------------
	// class attributes
	// ---------------------------------------------------------------------
	private int teamStrength; // out of 100
	private String szName;
	private boolean hasStadium;
	private Object venue; // venue can be a Stadium or Ground so is currently data type Object


	// ---------------------------------------------------------------------
	// constructor(s)
	// ---------------------------------------------------------------------
	public Team(String name, int strength, Ground ground)
	{
		this.szName = name;
		this.hasStadium = false;
		this.venue = ground;
		this.teamStrength = strength;
	}

	public Team(String name, int strength, Stadium stadium)
	{
		this.szName = name;
		this.hasStadium = true;
		this.venue = stadium;
		this.teamStrength = strength;
	}


	// ---------------------------------------------------------------------
	// getters
	// ---------------------------------------------------------------------
	public String toString()
	{
		return this.szName;
	}

	public boolean getHasStadium()
	{
		return this.hasStadium;
	}


	public Object getVenue()
	{		
		return this.venue;
	}

	public int getTeamStrength()
	{
		return this.teamStrength;
	}

	// ---------------------------------------------------------------------
	// setters
	// ---------------------------------------------------------------------
	public void setGround(Ground ground)
	{
		this.venue = ground;
		this.hasStadium = false;
	}

	public void setStadium(Stadium stadium)
	{
		this.venue = stadium;
		this.hasStadium = true;
	}

	public void setTeamStrength(int strength)
	{
		if(strength > 0 && strength <= 100)
		{
			this.teamStrength = strength;
		}
	}

	// ---------------------------------------------------------------------
	// display
	// ---------------------------------------------------------------------

	public void displayTeam()
	{
		String venue = (getHasStadium()) ? "Stadium" : "Ground";

		System.out.println(toString() + ":");
		System.out.println(venue + "- " + getVenue().toString());
		System.out.println("Strength- " + getTeamStrength() + "/100\n");
	}
}
