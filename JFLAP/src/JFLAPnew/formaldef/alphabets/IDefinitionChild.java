package JFLAPnew.formaldef.alphabets;

import JFLAPnew.formaldef.FormalDefinition;

public interface IDefinitionChild {

	public abstract FormalDefinition getParent();

	public abstract void setParent(FormalDefinition parent);
	
	public <T extends IAlphabet> T getParentAlphabetOfClass(Class<T> clazz);

}