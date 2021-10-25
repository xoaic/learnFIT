package SS1Problem;

public enum State {

	/**
	 * The cell contains food.
	 */
	FOOD,
	
	/**
	 * The cell is empty.
	 */
	EMPTY,
	
	/**
	 * The cell is empty but located on the "ideal" ant trail.  This must NOT
	 * be used to influence the ant's behavior, and should be treated like an
	 * EMPTY cell.
	 */
	TRAIL,
	
	/**
	 * The cell previously contained food, but the ant reached this location
	 * and ate the food.
	 */
	EATEN

}