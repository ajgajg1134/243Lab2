/**File:Puzzle.java
 * Version:
 *  $Id: Puzzle.java,v 1.2 2013/03/26 15:19:10 ajg2440 Exp $
 * Revisions:
 *  $Log: Puzzle.java,v $
 *  Revision 1.2  2013/03/26 15:19:10  ajg2440
 *  Added cvs tags
 *
 * 
 */
import java.util.ArrayList;
/**
 * @author Andrew
 *
 */
public interface Puzzle {

	/**
	 * For an incoming config, generate and return all direct neighbors to this config.
	 * @param config the incoming config
	 * @return the collection of neighbor configs
	 */
	//public ArrayList<Integer> getNeighbors(int config);
	/**
	 * For an incoming config, generate and return all direct neighbors to this config.
	 * @param config the incoming config
	 * @return the collection of neighbor configs
	 */
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> config);
	/**
	 * Get the starting config for the puzzle
	 * @return the starting config
	 */
	public ArrayList<Integer> getStart();

	/**
	*	Determines if a configuration is the goal or not
	* @return true if the configuration is the goal
	* @param an arraylist of ints that represents a config
	*/
	public boolean isSolution(ArrayList<Integer> config);
}
