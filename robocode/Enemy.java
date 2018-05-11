package ec.app.robocode;
import ec.util.*;
import ec.*;
import ec.gp.*;

public class Enemy extends GPNode{
	public String toString(){return "enemy";}
	
	//public String extraToString(){return this.toString();}
	
	public void checkConstraints(final EvolutionState state,
				 final int tree,
				 final GPIndividual typicalIndividual,
				 final Parameter individualBase)
	{
	super.checkConstraints(state,tree,typicalIndividual,individualBase);
	if (children.length!=0)
	    state.output.error("Incorrect number of children for node " + 
			       toStringForError() + " at " +
			       individualBase);
	}
	
	public void eval(final EvolutionState state,
        final int thread,
        final GPData input,
        final ADFStack stack,
        final GPIndividual individual,
        final Problem problem)
		{
			EnemyPos rd = (EnemyPos)input;
			Battle b = (Battle)problem;
			rd.bearing = b.currBearing;
			rd.distance = b.currDistance;
			rd.velocity = b.currVelocity;
		}
}