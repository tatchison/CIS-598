package ec.app.robocode;
import ec.*;
import ec.gp.*;
import ec.util.*;

public class slowDown extends GPNode{
	public String toString(){return "slowDown";}
	
	//public String extraToString(){return "slowDown";}
	
	public void checkConstraints(final EvolutionState state,
				 final int tree,
				 final GPIndividual typicalIndividual,
				 final Parameter individualBase)
	{
	super.checkConstraints(state,tree,typicalIndividual,individualBase);
	if (children.length!=1)
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
			Battle r = (Battle)problem;
			r.slowDown();
			rd.bearing = r.currBearing;
			rd.distance = r.currDistance;
			rd.velocity = r.currVelocity;
		}
}