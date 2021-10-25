package SS1Problem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.moeaframework.Analyzer;
import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;

public class Test {
	public static void main(String[] args) throws FileNotFoundException,
	IOException {
		int maxMoves = 500;
		
		// define new prob, algorithm with moea
		NondominatedPopulation results = new Executor()
				.withProblemClass(AntProblem.class, getProbFile(), maxMoves)
				.withAlgorithm("random")
				.withProperty("populationSize", 500)
				.withMaxEvaluations(10000)
				.run();
		
		Analyzer analyzer = new Analyzer()
				.withProblem("UF1")
				.includeAllMetrics()
				.showStatisticalSignificance();
		
		Executor executor = new Executor()
				.withProblem("UF1")
				.withMaxEvaluations(10000);
				
		analyzer.addAll("GA", executor.withAlgorithm("GA").runSeeds(500));
		
		analyzer.printAnalysis();
				
		AntProblem problem = new AntProblem(getProbFile(), maxMoves);
		problem.evaluate(results.get(0));
		problem.displayLastEvaluation();
	}
	
	/**
	 * Returns an input problem file
	 */
	public static InputStream getProbFile() {
		InputStream probFile = Test.class.getResourceAsStream("test.txt");
		
		if (probFile == null) {
			System.err.println("Unable to find test file");
			System.exit(-1);
		}
		
		return probFile;
	}

}
