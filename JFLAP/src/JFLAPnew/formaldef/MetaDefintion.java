package JFLAPnew.formaldef;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import JFLAPnew.formaldef.gui.definitioncreator.chooser.ModuleChooser;

public class MetaDefintion {

	protected Set<FormalDefinition> myDefintions;
	
	public MetaDefintion(){
		this(new ModuleChooser(300,300).getSelection());
	}
	
	public MetaDefintion(Class<? extends FormalDefinition> ... selection) {
		myDefintions = new HashSet<FormalDefinition>();
		for (Class<? extends FormalDefinition> clazz: selection)
			try {
				System.out.println("CLASS:" + clazz);
				myDefintions.add(clazz.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
				throw new AlphabetException("Bad instantiation in meta definition");
			} 
	}

	public Set<FormalDefinition> getDefinitions() {
		return myDefintions;
	}

	public void addFormalDefinition(FormalDefinition fd) {
		myDefintions .add(fd);
	}
	
}
