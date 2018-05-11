package ec.app.robocode;
import ec.util.*;
import ec.*;
import ec.gp.*;
import ec.gp.koza.*;
import ec.simple.*;
import java.io.*;
import java.lang.*;

//possibly implements SimpleProblemForm if things get too messy
public class Battle extends GPProblem implements SimpleProblemForm{
	public static final String P_DATA = "data";
	
	public double currBearing;
	public double currDistance;
	public double currVelocity;
	public static int count = 0;
	
	public void setup(final EvolutionState state, final Parameter base){
		super.setup(state,base);
		
		if(!(input instanceof EnemyPos))
				state.output.fatal("GPData class must subclass from " + EnemyPos.class,
					base.push(P_DATA),null);
	}
	
	public void evaluate(final EvolutionState state, 
        final Individual ind, 
        final int subpopulation,
        final int threadnum)
		{
			//Double check to make sure it hasn't already been evaluated
			if(!ind.evaluated){
				EnemyPos ep = (EnemyPos)(this.input);
				
				String output;
				output = String.format("output/tree%d.txt",count);
				String input;
				input = String.format("input/results%d.txt",count);
				count++;
				
				//We output the individual's tree so we can handmake them for testing
				//Another implementation would use an interface to RoboCode to run evaluate
				//but I couldn't figure it out, so we do it the hard/inefficient way
				PrintWriter pw;
				try{
					pw = new PrintWriter(output,"UTF-8");
					ind.printIndividual(state,pw);
					pw.close();
				}
				catch(IOException e){
					System.out.println("Error with printwriter: " + e.toString());
					System.exit(0);
				}
				
				File results = new File(input);
				while(!results.exists()){
					//We want to wait to read in the evaluation data until the file actually exists
					try{
						Thread.sleep(10000);
					}
					catch(InterruptedException e){
						System.out.println("Thread sleep error: " + e.toString());
						System.exit(0);
					}
				}
				BufferedReader fr;
				int res = 0;
				try{
					fr = new BufferedReader(new FileReader(results));
					res = Integer.parseInt(fr.readLine());
					fr.close();
				}
				catch(IOException e){
					System.out.println("Results file not found: " + e.toString());
					System.exit(0);
				}
				
				KozaFitness kf = ((KozaFitness)ind.fitness);
				kf.setStandardizedFitness(state,res);
				ind.evaluated = true;
			}
		}
		
	/*
		The methods from this point on are basically placeholders.
		As of right now, the main point is to visualize what I want
		to happen in the nodes when they are reached in the tree. 
		Since evaluations will be occuring out of program(for now),
		these methods will never be called.
	*/
	public void forward(){}
	public void speedUp(){}
	public void slowDown(){}
	public void turnLeft(){}
	public void turnRight(){}
	public void reverse(){}
	public void fire(){}
}