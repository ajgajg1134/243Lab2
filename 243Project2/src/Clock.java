/**File: Clock.java
 * Version:
 *  $Id: Clock.java,v 1.2 2013/03/26 15:19:10 ajg2440 Exp $
 * Revisions:
 *  $Log: Clock.java,v $
 *  Revision 1.2  2013/03/26 15:19:10  ajg2440
 *  Added cvs tags
 *
 */
import java.util.ArrayList;
/**
 * @author Andrew
 *
 */
public class Clock implements Puzzle {
	
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
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> configLst) {
		ArrayList<ArrayList<Integer>> neighborConfigs = new ArrayList<ArrayList<Integer>>();
		int config = configLst.get(0);
		if (config + 1 > hours)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(0);
			neighborConfigs.add(temp);
		}
		else
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(config + 1);
			neighborConfigs.add(temp);
		}
		if(config - 1 <= 0)
		{
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			temp2.add(hours);
			neighborConfigs.add(temp2);
		}
		else
		{
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			temp2.add(config - 1);
			neighborConfigs.add(temp2);
		}
		
		return neighborConfigs;
	}

	@Override
	public ArrayList<Integer> getStart() {
		ArrayList<Integer> startLst = new ArrayList<Integer>();
		startLst.add(start);
		return startLst;
	}

	@Override
	public boolean isSolution(ArrayList<Integer> config)
	{
		for(int i : config)
		{
			if(i == goal)
				return true;
		}
		return false;
	}
}
