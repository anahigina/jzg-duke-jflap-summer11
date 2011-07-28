package JFLAPnew.formaldef.gui.definitionpanel.chooser;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JCheckBox;

import JFLAPnew.formaldef.IFormallyDefined;
import JFLAPnew.formaldef.alphabets.IAlphabet;

public class ChooserOption extends JCheckBox {

	
	
	private Collection<Class<? extends IAlphabet>> myClass;

	public ChooserOption(String label, int hotkey, boolean selected, 
			Class<? extends IAlphabet> ... clazz){
		super(label);
		setMnemonic(hotkey); 
	    setSelected(selected);
	    myClass = Arrays.asList(clazz);
	}

	public Collection<Class<? extends IAlphabet>> getAlphabetClasses() {
		return myClass;
	}

}
