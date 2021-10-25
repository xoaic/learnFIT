package SS1Problem;

import org.moeaframework.util.tree.Environment;
import org.moeaframework.util.tree.Node;

/**
* The node for turning the ant left.  Performing this operation consumes one
* move.
*/
public class TurnLeft extends Node {
	
	/**
	 * Constructs a new node for turning the ant left.
	 */
	public TurnLeft() {
		super();
	}

	@Override
	public TurnLeft copyNode() {
		return new TurnLeft();
	}

	@Override
	public Void evaluate(Environment environment) {
		World world = environment.get(World.class, "world");
		world.turnLeft();
		return null;
	}

}
