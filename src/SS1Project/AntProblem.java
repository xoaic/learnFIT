package SS1Problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.moeaframework.util.tree.Rules;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.Program;
import org.moeaframework.problem.AbstractProblem;
import org.moeaframework.util.tree.Environment;
import org.moeaframework.util.tree.IfElse;
import org.moeaframework.util.tree.Sequence;

public class AntProblem extends AbstractProblem {

	/**
	 * The rules for building the ant trail program.
	 */
	private final Rules rules;
	
	/**
	 * The world that the ant occupies.
	 */
	private final World world;
	
	/**
	 * Constructs a new ant trail problem using the ant trail defined in the
	 * specified file.
	 * 
	 */
	public AntProblem(File file, int maxMoves) throws FileNotFoundException,
	IOException {
		this(new FileReader(file), maxMoves);
	}
	
	public AntProblem(InputStream inputStream, int maxMoves) throws
	IOException {
		this(new InputStreamReader(inputStream), maxMoves);
	}
	
	public AntProblem(Reader reader, int maxMoves) throws IOException {
		super(1, 1);
		
		rules = new Rules();
		rules.add(new TurnLeft());
		rules.add(new TurnRight());
		rules.add(new MoveForward());
		rules.add(new IsFoodAhead());
		rules.add(new IfElse(Void.class));
		rules.add(new Sequence(Void.class, Void.class));
		rules.setReturnType(Void.class);
		world = new World(reader, maxMoves);
	}

	@Override
	public synchronized void evaluate(Solution solution) {
		Program program = (Program)solution.getVariable(0);
		
		world.reset();
		
		while ((world.getRemainingMoves() > 0) &&
				(world.getRemainingFood() > 0)) {
			Environment environment = new Environment();
			environment.set("world", world);
			program.evaluate(environment);
		}
		
		solution.setObjective(0, world.getRemainingFood() + 
				(world.getNumberOfMoves() / (world.getMaxMoves() + 1.0)));
	}
	
	/**
	 * Prints a visual representation of the last evaluated solution to this
	 * problem.
	 */
	public void displayLastEvaluation() {
		System.out.println("Moves: " + world.getNumberOfMoves() + " / " +
				world.getMaxMoves());
		System.out.println("Food: " + world.getFoodEaten() + " / " + 
				world.getTotalFood());
		world.display();
	}

	@Override
	public Solution newSolution() {
		Solution solution = new Solution(1, 1);
		solution.setVariable(0, new Program(rules));
		return solution;
	}

}

