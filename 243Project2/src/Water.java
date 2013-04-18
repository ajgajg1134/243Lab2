import java.util.ArrayList;

/**File: Water.java
 * 
 * Version:
 * $Id$
 * 
 * Revisions:
 * $Log$
 */
import java.util.*;

/**
 * @author Andrew Glaude (ajg2440@rit.edu)
 *
 */
public class Water implements Puzzle<ArrayList<Integer>>{
	//Capacity of jugs
	private ArrayList<Integer> jugCapacity;
	//Actual amount of water in each jug
	private ArrayList<Integer> jugs;
	
	private int goalWater;
	
	public Water(ArrayList<Integer> jugCap, ArrayList<Integer> jugs, int goal)
	{
		this.jugCapacity = jugCap;
		this.jugs = jugs;
		this.goalWater = goal;
	}

	/**
	 * @see Puzzle#getNeighbors(int)
	 */
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> config) {
		ArrayList<ArrayList<Integer>> newConfigs = new ArrayList<ArrayList<Integer>>();
		
		
		//Adds configs where you fill a jug
		for(int i = 0; i < jugs.size(); i++)
		{
			ArrayList<Integer> newTemp = new ArrayList<Integer>();
			newTemp = (ArrayList<Integer>) config.clone();
			newTemp.set(i, jugCapacity.get(i));
			newConfigs.add(newTemp);
		}
		//Adds configs where you empty a jug
		for(int i = 0; i < jugs.size(); i++)
		{
			ArrayList<Integer> newTemp = new ArrayList<Integer>();
			newTemp = (ArrayList<Integer>) config.clone();
			newTemp.set(i, 0);
			newConfigs.add(newTemp);
		}
		//Adds configs where you pour from one to another
		//Note pours from j to i
		for(int i = 0; i < jugs.size(); i++)
		{
			for(int j = 1; j < jugs.size(); j++)
			{
				ArrayList<Integer> newTemp = new ArrayList<Integer>();
				newTemp = (ArrayList<Integer>) config.clone();
				if(jugCapacity.get(i) >= (newTemp.get(j) + newTemp.get(i)))
				{
					int oldI = newTemp.get(i);
					int newI = newTemp.get(i) + newTemp.get(j);
					newTemp.set(i, newI);
					newTemp.set(j, (newTemp.get(j) - (newI - oldI)));
					newConfigs.add(newTemp);
				}
				else
				{
					if(jugCapacity.get(i) != newTemp.get(i))
	                {
	                    int oldI = newTemp.get(i);
	                    int newI = jugCapacity.get(i);
	                    newTemp.set(i, newI);
	                    newTemp.set(j, (newTemp.get(j) - (newI - oldI)));
	                    newConfigs.add(newTemp);
	                }
            	}
			}
		}
		return newConfigs;
	}

	/**
	 * @see Puzzle#getStart()
	 */
	@Override
	public ArrayList<Integer> getStart() {
		return jugs;
	}

	@Override
	public boolean isSolution(ArrayList<Integer> config)
	{
		for(int i : config)
		{
			if (i == goalWater)
				return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2)
        {
            //Error, not 3 args passed
            System.out.println("Usage: java Water GoalWater BucketSizes");
        }
        else
        {
            ArrayList<Integer> jugs = new ArrayList<Integer>();
            ArrayList<Integer> jugCap = new ArrayList<Integer>();
            int goal = Integer.parseInt(args[0]);
            for(int i = 1; i < args.length; i++){
                jugCap.add(Integer.parseInt(args[i]));
                jugs.add(0);
            }
            
            Water w = new Water(jugCap, jugs, goal);
            
            Solver solver = new Solver();
            
            solver.Solve(w);
        }

	}
	/**
	* Determines if a config matches one in predecessors
	* @param config is the config to check
	* @param predecessors the hashmap to search in 
	* @return true if it has been visited false otherwise
	*/
	public boolean hasBeenVisited(ArrayList<Integer> config, HashMap predecessors)
	{
		boolean hasBeenVisited = false;
		Set<ArrayList<Integer>> s = predecessors.keySet();
		for(ArrayList<Integer> key : s)
		{
			boolean isTrue = true;
			for(int i = 0; i < key.size(); i++)
			{
				if(config.get(i) != key.get(i))
				{
					isTrue = false;
					break;
				}
			}
			if(isTrue)
				return true;
		}
		return false;
	}

}
