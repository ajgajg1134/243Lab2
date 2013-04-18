/**File: Clock.java
 * Version:
 *  $Id: Clock.java,v 1.2 2013/03/26 15:19:10 ajg2440 Exp $
 * Revisions:
 *  $Log: Clock.java,v $
 *  Revision 1.2  2013/03/26 15:19:10  ajg2440
 *  Added cvs tags
 *
 */
import java.util.*;
/**
 * @author Andrew
 *
 */
public class Clock implements Puzzle<Integer>{
	
	private int goal, start, hours;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 3)
		{
			//Error, not 3 args passed
			System.out.println("Usage: java Clock hours start goal");
		}
		else
		{
			int h, s, g;
			h = Integer.parseInt(args[0]);
			s = Integer.parseInt(args[1]);
			g = Integer.parseInt(args[2]);
			Clock c = new Clock(h, s, g);
			
			Solver solver = new Solver();
			
			solver.Solve(c);
		}
	}
	
	public Clock(int hours, int start, int goal)
	{
		this.hours = hours;
		this.start = start;
		this.goal = goal;
	}
	@Override
	public ArrayList<Integer> getNeighbors(Integer config) {
		ArrayList<Integer> neighborConfigs = new ArrayList<Integer>();

		if (config + 1 > hours)
		{
			neighborConfigs.add(0);
		}
		else
		{
			neighborConfigs.add(config + 1);
		}
		if(config - 1 <= 0)
		{
			neighborConfigs.add(hours);
		}
		else
		{
			neighborConfigs.add(config - 1);
		}

		return neighborConfigs;
	}

	@Override
	public Integer getStart() {
		return start;
	}

	@Override
	public boolean isSolution(Integer config)
	{
		return config == goal;
	}

	public String toString(Integer config)
	{
		return config + "";
	}
}
