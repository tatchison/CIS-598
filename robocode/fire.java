package ec.app.robocode;
import ec.*;
import ec.gp.*;
import ec.util.*;

public class fire extends GPNode{
	public String toString(){return "fire";}
	
	/*public String extraToString(){
		String f = "fire(";
		f += (fire)children[0].extraToString();
		f += ")";
		return f;
	}*/
	
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
			
			children[0].eval(state,thread,input,stack,individual,problem);
			
			r.fire();
			rd.bearing = r.currBearing;
			rd.distance = r.currDistance;
			rd.velocity = r.currVelocity;
		}
}