package JFLAPnew.formaldef.gui.chooser;

import gui.errors.JFLAPError;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import JFLAPnew.formaldef.alphabets.IAlphabet;

public class ModuleChooser extends JDialog{

	ModuleChooserPanel myPanel;
	Set<Class<? extends IAlphabet>> mySelection;
	
	
	public ModuleChooser(int x, int y){
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setLocation(x, y);
		myPanel = new ModuleChooserPanel(){

			@Override
			public void onContinueAction(HashSet<Class<? extends IAlphabet>> selection) {
				ModuleChooser.this.setSelection(selection);
				ModuleChooser.this.dispose();
			};
		};
		this.setTitle("Select Modules");
		this.add(myPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}


	protected void setSelection(HashSet<Class<? extends IAlphabet>> selection) {
		mySelection = selection;
	}
	
	public Class[] getSelection(){
		return mySelection.toArray(new Class[0]);
	}
	
	
}
