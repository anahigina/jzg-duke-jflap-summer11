package JFLAPnew.formaldef;

import JFLAPnew.formaldef.alphabets.IAlphabet;

public abstract class FormallyDefinedObject implements IFormallyDefined {

	protected FormalDefinition myDefintion;
	
	@Override
	public FormalDefinition getFormalDefinition() {
		return myDefintion;
	}

	@Override
	public void setFormalDefinition(FormalDefinition fd) {
		myDefintion = fd;
	}
	
//	public void resetDefintion(){
//		myDefintion = new FormalDefinition(
//				FormalDefintionFactory.getDefinitionRequirements(this.getClass()));
//	}
	
}
