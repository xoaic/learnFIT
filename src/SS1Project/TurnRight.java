package SS1Problem;

import org.moeaframework.util.tree.Environment;
import org.moeaframework.util.tree.Node;

/**
* The node for turning the ant left.  Performing this operation consumes one
* move.
*/
public class TurnRight extends Node {
	
	/**
	 * Constructs a new node for turning the ant left.
	 */
	public TurnRight() {
		super();
	}

	@Override
	public TurnRight copyNode() {
		return new TurnRight();
	}

	@Override
	public Void evaluate(Environment environment) {
		World world = environment.get(World.class, "world");
		world.turnRight();
		return null;
	}

}