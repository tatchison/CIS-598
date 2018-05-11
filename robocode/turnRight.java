package ec.app.robocode;
import ec.*;
import ec.gp.*;
import ec.util.*;

public class turnRight extends GPNode{
	public String toString(){return "turnRight";}
	
	//public String extraToString(){return "turnRight";}
	
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
			r.turnRight();
			rd.bearing = r.currBearing;
			rd.distance = r.currDistance;
			rd.velocity = r.currVelocity;
		}
}