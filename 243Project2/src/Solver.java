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
import java.util.*;
public class Solver <E> {

	public Solver()
	{

	}
	
	public void Solve(Puzzle<E> p)
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
		
		Queue<E> queue = new LinkedList<E>();
		E start = p.getStart();
		HashMap<E, E> predecessors = new HashMap<E,E>();
		HashSet<E> seenDis = new HashSet<E>();

		start = p.getStart();
		queue.add(start);

		predecessors.put(start, start);
		
		boolean found = p.isSolution(p.getStart());
		
		E current = start;
		
		if(found)
		{
			current = p.getStart();
		}
		
		while(queue.size() > 0 && !found)
		{
			current = queue.poll();
			for(E i : p.getNeighbors(current))
			{
				/*
				if(predecessors.get(current) == null)
					break;
				*/
				//seenDis.add(i);

				if(!p.hasBeenVisited(i, predecessors))
					predecessors.put(i, current);

				if(p.isSolution(i))
				{
					current = i;
					found = true;
					break;
				}
				else
				{
					queue.add(i);
				}
			}
			//System.out.println("goin thru queue");
		}
		System.out.println(predecessors);
		if(found)
		{
			System.out.println("FOUND SHIT");
			ArrayList<E> finalPath = new ArrayList<E>();
			E next = current;
			System.out.println("next: " + next);
			while(!next.equals(p.getStart()))
			{
				//System.out.println("Steppin: " + next);
				finalPath.add(next);
				next = predecessors.get(next);
			}
			finalPath.add(next);

			Collections.reverse(finalPath);

			for(int i = 0; i < finalPath.size(); i++)
			{
				System.out.println("Step " + i + ": " + finalPath.get(i));
			}
		}
		else
		{
			//Nothing found?
			System.out.println("No solution.");
		}
		

	}

}
