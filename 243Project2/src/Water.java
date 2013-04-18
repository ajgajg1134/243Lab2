import java.util.ArrayList;
import java.util.HashMap;

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
			ArrayList<Integer> newTemp = (ArrayList<Integer>) config.clone();
			
			if(newTemp.get(i) != jugCapacity.get(i))
			{
			    newTemp.set(i, jugCapacity.get(i));
			    newConfigs.add(newTemp);
			}
		}
		//Adds configs where you empty a jug
		for(int i = 0; i < jugs.size(); i++)
		{
			ArrayList<Integer> newTemp = (ArrayList<Integer>) config.clone();
			if(newTemp.get(i) != 0){
			    newTemp.set(i, 0);
			    newConfigs.add(newTemp);
			}
		}
		//Adds configs where you pour from one to another
		//Note pours from j to i
		for(int i = 0; i < jugs.size(); i++)
		{
			for(int j = 0; j < jugs.size(); j++)
			{
				ArrayList<Integer> newTemp = (ArrayList<Integer>) config.clone();
				int oldI = newTemp.get(i);
				int newI = newTemp.get(i) + newTemp.get(j);
				int getJ = newTemp.get(j);
				boolean contains = false;
				if(jugCapacity.get(i) >= (getJ + oldI))
				{
					if(newI != 0 && getJ != 0){
    					newTemp.set(i, newI);
    					newTemp.set(j, (getJ - (newI - oldI)));
                        for(ArrayList<Integer> temp: newConfigs)
                        {
                            if(temp.get(i) == newI && temp.get(j) == (getJ - (newI - oldI))){
                                contains = true;
                            }
                        }
                        if(!contains){
                            newConfigs.add(newTemp);
                        }
					}
				}
				
				else if(jugCapacity.get(i) != oldI)
                {
                    if(newI != 0){
                        newTemp.set(i, newI);
                        newTemp.set(j, (getJ - (newI - oldI)));
                        for(ArrayList<Integer> temp: newConfigs)
                        {
                            if(temp.get(i) == newI && temp.get(j) == (getJ - (newI - oldI))){
                                contains = true;
                            }
                        }
                        if(!contains){
                            newConfigs.add(newTemp);
                        }
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

            /*
            System.out.println((new Water(jugCap, jugs, goal)).getNeighbors(new ArrayList<Integer>(){{
                add(5);
                add(0);
                }}
            ));
            */
            
            Water w = new Water(jugCap, jugs, goal);
            
            Solver solver = new Solver();
            
            solver.Solve(w);
        }

	}

    /* (non-Javadoc)
     * @see Puzzle#hasBeenVisited(java.lang.Object, java.util.HashMap)
     */
    @Override
    public boolean hasBeenVisited(ArrayList<Integer> config,
            HashMap predecessors) {
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
