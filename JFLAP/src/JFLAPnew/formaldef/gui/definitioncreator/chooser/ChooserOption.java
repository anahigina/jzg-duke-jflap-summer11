package JFLAPnew.formaldef.gui.definitioncreator.chooser;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JCheckBox;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.IFormallyDefined;
import JFLAPnew.formaldef.alphabets.IAlphabet;

public class ChooserOption extends JCheckBox {

	
	
	private Class<? extends FormalDefinition> myDefinitionClass;

	public ChooserOption(String label, int hotkey, Class<? extends FormalDefinition> def, 
			boolean b){
		super(label);
		setMnemonic(hotkey); 
	    setSelected(b);
	    myDefinitionClass = def;
	}

	public Class<? extends FormalDefinition> getDefinitionClass() {
		return myDefinitionClass;
	}

}
