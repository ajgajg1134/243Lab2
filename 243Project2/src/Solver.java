import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**File: Solver.java
 * Version:
 *  $Id: Solver.java,v 1.3 2013/04/03 14:10:14 ajg2440 Exp $
 * Revisions:
 *  $Log: Solver.java,v $
 *  Revision 1.3  2013/04/03 14:10:14  ajg2440
 *  Fixed error with no output if start = goal
 *
 *  Revision 1.2  2013/03/26 15:19:10  ajg2440
 *  Added cvs tags
 *
 */

/**
 * @author Andrew Glaude
 * @author Mike Yachanin
 *
 */
public class Solver {
	
	public void Solve(Puzzle p)
	{
//		create an empty queue as an ArrayList<Integer>
//		create an ArrayList<Integer> of one element from the starting config and enqueue it
//		set found to whether the starting config is the goal config, or not
//
//		while the queue is not empty and not found:
//		    dequeue the front element from the queue and set it to current
//		    for each neighbor of the last element in current:
//		        create an ArrayList<Integer> for the next config and make it a copy of current
//		        append the neighbor to the next config
//		        if the next config is the goal:
//		            set current to the next config
//		            set found to true
//		            break out of the for loop
//		        else:
//		            enqueue the next config
//		            
//		if found:
//		    current is the solution
//		else:
//		    there is no solution
		
		Queue<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();
		ArrayList<Integer> start = new ArrayList<Integer>();
		start = p.getStart();
		queue.add(start);
		
		boolean found = p.isSolution(p.getStart());
		
		ArrayList<Integer> current = new ArrayList<Integer>();
		
		if(found)
		{
			current = p.getStart();
		}
		
		while(queue.size() > 0 && !found)
		{
			current = queue.poll();
			for(ArrayList<Integer> i : p.getNeighbors(current))
			{
				Queue<ArrayList<Integer>> nextConfig = new LinkedList<ArrayList<Integer>>();

				nextConfig.add(current);
				
				nextConfig.add(i);
				if(p.isSolution(i))
				{
					current = nextConfig.poll();
					found = true;
					break;
				}
				else
				{
					queue = nextConfig;
				}
			}
		}
		if(found)
		{
			//Go through and print steps to get somewhere.
			for(int i = 0; i < current.size(); i++)
			{
				System.out.println("Step " + i + ": " + current.get(i));
			}
		}
		else
		{
			//Nothing found?
			System.out.println("Woah slow down there. Ain't no way you're getting a solution.");
		}
		

	}

}
