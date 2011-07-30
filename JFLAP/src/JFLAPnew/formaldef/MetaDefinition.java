package JFLAPnew.formaldef;

import gui.errors.JFLAPError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.definitioncreator.DefinitionCreationPanel;
import JFLAPnew.formaldef.gui.definitioncreator.chooser.ModuleChooser;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;

public class MetaDefinition extends HashSet<FormalDefinition> implements Serializable{

	public MetaDefinition(){
		
	}
	
	public MetaDefinition(Class<? extends FormalDefinition> ... selection) {
		this.addAllByClass(Arrays.asList(selection));
			
	}
	
	
	public MetaDefinition(FormalDefinition ... def){
		for (FormalDefinition fd: def)
			this.add(fd);
	}

	public boolean add(Class<? extends FormalDefinition> clazz){
		try {
			return this.add(clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlphabetException("Trouble adding" + clazz + " to meta definition");		} 
	}
	

	public Class<? extends FormalDefinition>[] getDefinitionClasses() {
		ArrayList<Class<? extends FormalDefinition>> list = new ArrayList<Class<? extends FormalDefinition>>();
		for (FormalDefinition fd: this)
			list.add(fd.getClass());
		return list.toArray( new Class[0]);
	}


	public void addAllByClass(Collection<Class<? extends FormalDefinition>> classes) {
		for (Class<? extends FormalDefinition> clazz : classes)
			this.add(clazz);
	}

	public static void setDefintionFromMeta(FormalDefinition old,
			MetaDefinition meta) {
		FormalDefinition replacement = null;
		while ((replacement = meta.getDefinitionByClass(old.getClass())) == null){
			
			int n = JOptionPane.showConfirmDialog(null, 
					"The current Meta Definition does not contain this type of Formal Defintion.\n" +
							"Would you like to expand the meta definition?", 
					"Error",
					JOptionPane.YES_NO_OPTION) ;
			if (n == 1) return;
			DefinitionCreationPanel.createWithChooser(meta);
		}
		
		old.eraseAlphabets();
		old.addAll(replacement);
		
	}

	public FormalDefinition getDefinitionByClass(
			Class<? extends FormalDefinition> clazz) {
		for (FormalDefinition fd: this){
			if (fd.getClass().isAssignableFrom(clazz))
				return fd;
		}
		return null;
	}
	
}
