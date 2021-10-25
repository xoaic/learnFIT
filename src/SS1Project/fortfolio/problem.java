package SS1Problem.fortfolio;

import java.util.Random;
import java.io.*;
import java.util.Arrays;
import java.lang.*;
import java.util.*;

public class problem implements Constants {
	int MaxGen; // Maximum number of Generation
	int ParamNo; // number of parameters in each individual = cromosome dimension
	int PopSize; // population size
	int ParentsNo;
	        
    double bst;        
	Random Rnd;
	double Results[][]; 
	// 0.0. Converge? 0.1. Achieved Fitness, 0.2. Requried Evaluation, 0.3 Best Score after 1000 Eval
	// 0.4 Best Score after 10000 Eval 0.5 Best Score after 100000 Eval 0.6 Best Score after 300000 Eval
	// 1. Best score in different generations
	int CountEvaluation;
	double BestScore;
	boolean Converge;
	
	double GlobalOptimum;
	double GlobalOptimumX[];

	ArrayList<String> companyName;
	ArrayList<Float> companyReturn, companyRisk;
      float covariance[][];

	public problem(int genMax, int paramNo, int popSize) {
		MaxGen = genMax;
		ParamNo = paramNo;
		PopSize = popSize;
		ParentsNo = 3;
		// FunctNo = functNo;
		//Rnd =  ((long) System.currentTimeMillis());
		Rnd = new Random((new Random()).nextLong());
		Results = new double[2][];
		Results[0] = new double[8]; //  0.0. Converge? 0.1. Achieved Fitness, 0.2. Requried Evaluation, 0.3 Best Score after 1000 Eval
		// 0.4 Best Score after 10000 Eval 0.5 Best Score after 100000 Eval 0.6 Best Score after 300000 Eval

		Results[1] = new double[MaxGen]; // For storing best scores in different generations
		CountEvaluation = 0;
		BestScore = Double.MAX_VALUE;
		Converge = false;

	}

	public void diffEvolAHCspx() throws IOException, ClassNotFoundException {
		Individual offSpring;
		Individual curGen[], nextGen[];
		//Matrix inGen; // Matrix for reading initial generation from file
		double tmp[][], indPar[], offSpScore;
		int i, genCount, a, b, c;
		boolean tmpConv;
                

		int numberOfAssets = 4;	//this is temporarily the value of assets
		String input = null;
		float covariance[][] = new float[numberOfAssets][numberOfAssets];
		
		BufferedReader cov = null;
		try {
			cov = new BufferedReader(new FileReader("E:/DRIVE 2/netbeans/ArrayFill/src/arrayfill/covariance.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			cov.readLine();	//discard the first line as its the heading
			int j = 0;		//row count
			while((input = cov.readLine()) != null)
			{
				if(input.equals(""))continue;
				StringTokenizer st = new StringTokenizer(input);
				st.nextToken();	//discard the first token as its the row name
				//for (int i = 0; i < numberOfAssets; i++) {	// i is column count
				i = 0;
				while(st.hasMoreElements()){
					covariance[j][i++] = Float.parseFloat(st.nextToken());  
				}
				j++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		/*for ( i = 0; i < numberOfAssets; i++) {
			for (int j = 0; j < numberOfAssets; j++) {
				System.out.print(covariance[i][j]+"\t\t");
			}
			System.out.println();
		}*/
                
                //ArrayList<String> companyName;
		//ArrayList<Float> companyReturn, companyRisk;

		companyName = new ArrayList<String>();
		companyReturn = new ArrayList<Float>();
		companyRisk = new ArrayList<Float>();

		//File named company.txt containing values should be in the same directory
		File file = new File(
				"E:/DRIVE 2/netbeans/JavaApplication1/src/Rumana/company1.txt");
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		String str = new String();
		str = in.readLine(); //for discarding the heading line of the input file

		//loop until there are values in the file
		while ((str = in.readLine()) != null) {
			//string tokenizer used to tokenize the name, risk and return values
			//default token is space and tab. default is used
			StringTokenizer st = new StringTokenizer(str);
			companyName.add(st.nextToken());
			companyRisk.add(new Float(st.nextToken()));
			companyReturn.add(new Float(st.nextToken()));
		}

		//System.out.println("Reading from file is complete. Now printing the values read...");
		//System.out.println("Company Names: " + companyName.toString());
		//System.out.println("Company Returns: " + companyReturn.toString());
		//System.out.println("Company Risks: " + companyRisk.toString());

		//starting to compute initial population
		//Q: do I have to take the value from user?
		double[][] temp = new double[4][companyName.size()];

		for (int q = 0; q < 4; q++) {
			for (int j = 0; j < companyName.size(); j++) {
				//temp[q][j]=Float.parseFloat(companyName.get(j));
				temp[q][j] = 0;
			}

			float totalWeight = 0;
			while (totalWeight < 1.0) {
				//select random company index to give random weight
				Random r = new Random();
				//select company index to give weight
				int index = (int) (r.nextFloat() * 10) % companyName.size();
				//select weight to give

				float weight = r.nextFloat();
				//System.out.println("Index: "+index+" weight: "+weight+" totalweight: "+totalWeight);
				if ((totalWeight + weight) > 1)
					continue;
				temp[q][index] = weight;
				totalWeight += weight;
				if (totalWeight > 0.995)
					break; //sum of exactly 1.0 requires much time so consider a bit 
			}
		}
		
                
                System.out.println("20 Init population created.");
		/*for (int q = 0; q < 20; q++) {
			//for(int i=0;i<2;i++)
			
				for (int j = 0; j < companyName.size(); j++){
					//System.out.print(paddingString(new String(temp[q][j]+""), 20-new String(temp[q][j]+"").length(), ' ',false));
					//System.out.println();
			}
			//System.out.println();
		}*/
		curGen = new Individual[this.PopSize];
		nextGen = new Individual[this.PopSize];

		indPar = new double[this.ParamNo];
		tmp = new double[PopSize][ParamNo];

		//tmp = temp;
                
                for(int p=0;p<PopSize;p++){
                    for(int q=0;q<this.ParamNo;q++){
                        
                        tmp[p][q]=temp[p][q];
                    }
                }
                System.out.println("-----------------------------------------------");
                System.out.println("then producing the offsprings ");
		Random initRnd;

		for (i = 0; i < PopSize; i++) {
			for (int j = 0; j < this.ParamNo; ++j) {
				indPar[j] = tmp[i][j];
			}
			curGen[i] = new Individual(indPar, this.Rnd);

			double x;

			curGen[i].fitness(getFloatArray(companyReturn),getFloatArray(companyRisk),covariance);
                        
			//converge(curGen[i].getScore());
			//curGen[i].fitness(this.FunctNo);
			++CountEvaluation;
		}
		double y=printGeneration(curGen, 0, false);
                //System.out.println("Upward Best is"+y);
                curGen = ahcLS(curGen);
                
                //double bst;
		genCount = 0;
		while (this.CountEvaluation < (gMaxNoEvaluation + 1)) { // loop until MaxGen evolves
		//while (genCount < (gMaxNoEvaluation + 1)) { // loop until MaxGen evolves	
			//System.out.println(" genCount: "+ genCount);
			for (i = 0; i < PopSize; i++) { // loop through the complete current generation
				do {
					a = (int) (this.Rnd.nextDouble() * PopSize);
				} while (a == i);
				do {
					b = (int) (this.Rnd.nextDouble() * PopSize);
				} while (b == i || b == a);
				do {
					c = (int) (this.Rnd.nextDouble() * PopSize);
				} while (c == i || c == a || c == b);
				offSpring = curGen[i].reproduction(curGen[a], curGen[b],
						curGen[c]);

				///AIKHANE AKTA FITNESS

				offSpScore = offSpring.fitness(getFloatArray(companyReturn),getFloatArray(companyRisk),covariance);
				//offSpScore = offSpring.fitness(this.FunctNo);
				//converge(offSpScore);
				if (offSpScore >= curGen[i].getScore()) {
					//if (select(curGen[i], offSpring)){
					nextGen[i] = offSpring;
				} else
					nextGen[i] = curGen[i];
				/*if(tmpConv){
				    storeResult(curGen,nextGen, i);
				}*/

				/*
				 for(i=0; i<PopSize; ++i){
				 curGen[i] = nextGen[i];
				 }*/
				//curGen = ahcLS(nextGen);
				//++genCount;
				//printGeneration(curGen, genCount, false);
				++CountEvaluation;
			}
			curGen = ahcLS(nextGen);
			++genCount;
                        
		 this.bst=printGeneration(curGen, genCount, false);
                //System.out.println("Best is"+x);
		}
		Arrays.sort(curGen);
                System.out.println("The best is"+this.bst);

		/*for(int p=1; p<Results.length;p++)
		{
			for(int q=0; q<Results[p].length;q++){
			System.out.println("NOW PRODUCING THE RESULTS OF EACH GENERATION");	
                            System.out.print(Results[p][q]+"  ");  
			System.out.println();
                        }
		}*/
		
		//return (this.Results);
                
               // System.out.println("Best is"+bst);

	}

	// Adaptive hill climbing
	public Individual[] ahcLS(Individual curGen[]) {
		Individual mParents[] = new Individual[this.ParentsNo];
		Individual tmpParent, tmpChild;
		int i;
		double curBest;
		int curBestIndex;
		boolean tmpConverge;

		// find best in curGen
		curBestIndex = 0;
		curBest = curGen[0].getScore();
		for (i = 1; i < PopSize; i++) {
			//System.out.println("rumana i="+i);
			if (curBest < curGen[i].getScore()) {
				curBest = curGen[i].getScore();
				curBestIndex = i;
			}
		}
		mParents[0] = curGen[curBestIndex]; // select the best Individual as one of the Parents for Simplex Crossover

		for (int j = 1; j < this.ParentsNo; ++j) { // randomly select m individuals (parents), and arrange in increasing order of their fitness
			tmpParent = curGen[(int) (this.Rnd.nextDouble() * this.PopSize)];
			int k, t;
			for (k = 1; k < j; ++k) {
				if (mParents[k].getScore() < tmpParent.getScore())
					break;
			}
			for (t = j; t > k; --t) {
				mParents[t] = mParents[t - 1];
			}
			mParents[t] = tmpParent;
		}

		//for(i=0; i<gLSPopSize; i++){
		while (true) {
			//generate offspring
			tmpChild = simplexCrossOver(mParents);

			//AIKHANE AKTA FITNESS FUNCTION
			tmpChild.fitness(getFloatArray(companyReturn),getFloatArray(companyRisk),covariance);

			//converge(tmpChild.getScore());
			// if child is better than the best parent then replace the best parent with child otherwise exit the local search
			if (tmpChild.getScore() > mParents[0].getScore()) {
				//if (select(mParents[0], tmpChild)){
				mParents[0] = tmpChild;
                               // System.out.println("best offspring is"+  tmpChild);
				/*if (tmpConverge){
				     storeResult(curGen,mParents, 1);
				}*/
			} else {
				/*if(tmpConverge){
				 storeResult(curGen,mParents,1);
				}*/
				break;
			}
                        
                        
		}
		curGen[curBestIndex] = mParents[0];
                
		return (curGen);
                
        
	}

	public Individual simplexCrossOver(Individual ind[]) {
		double X[][], child[], O[];
		int m, i, j;
		double tmp, eps;
		double r[], YK[][], CK[][];
		//Individual offSpring;
		//Random rnd = new Random((long) System.currentTimeMillis());
		//Random rnd = new Random ((new Random()).nextLong());
		m = ind.length; // number of parents
		X = new double[m][]; // variable for storing parents parameters
		O = new double[this.ParamNo]; // center of mass O
		r = new double[m];
		YK = new double[m][this.ParamNo];
		CK = new double[m][this.ParamNo];
		child = new double[this.ParamNo];
		eps = 1.0;
		// get parameters of m parents
		for (i = 0; i < m; ++i) {
			X[i] = ind[i].getParameters();
		}
		// compute center of mass O
		for (i = 0; i < this.ParamNo; ++i) {
			tmp = 0;
			for (j = 0; j < this.ParentsNo; ++j) {
				tmp += X[j][i];
			}
			O[i] = tmp / m;
		}
		// generate random numbers
		for (i = 0; i < m; ++i) {
			r[i] = Math.pow(this.Rnd.nextDouble(), 1.0 / (i + 1));
		}
		// calculate the values of Y_i s
		for (i = 0; i < m; ++i) {
			for (j = 0; j < this.ParamNo; ++j) {
				YK[i][j] = O[j] + eps * (X[i][j] - O[j]);
			}
		}

		for (i = 1; i < m; ++i) {
			for (j = 0; j < this.ParamNo; ++j) {
				CK[i][j] = r[i - 1] * (YK[i - 1][j] - YK[i][j] + CK[i - 1][j]);
			}
		}

		for (i = 0; i < this.ParamNo; ++i) {
			child[i] = YK[m - 1][i] + CK[m - 1][i];
		}

		return (new Individual(child, this.Rnd));
	}

	public double printGeneration(Individual gen[], int genNo, boolean printAll) {
		int i;
		double avg, best, tmp;

		avg = best = gen[0].getScore();
		for (i = 1; i < PopSize; ++i) {
			tmp = gen[i].getScore();
			if (tmp > best) {
				best = tmp;
			}
			avg += tmp;
		}
		avg = avg / PopSize;
		this.Results[1][genNo] = best;
                return best;
                
                
                
                //System.out.println("The current generation number is"+genNo);
                //System.out.println("This generation best"+best);

	}

	private float[] getFloatArray(ArrayList<Float> list) {
		float[] floatArray = new float[list.size()];

		for (int i = 0; i < floatArray.length; i++) {
			floatArray[i] = ((Float) list.get(i)).floatValue();
		}
		return floatArray;
	}

}
