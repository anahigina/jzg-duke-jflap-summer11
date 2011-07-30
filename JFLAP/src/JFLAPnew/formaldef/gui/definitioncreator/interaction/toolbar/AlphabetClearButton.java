package JFLAPnew.formaldef.gui.definitioncreator.interaction.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.gui.definitioncreator.MultiDefitionPanel;

public class AlphabetClearButton extends DefinitionCreationToolbarButton {

	public AlphabetClearButton(MultiDefitionPanel multiDef) {
		super("Clear", multiDef);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int n = JOptionPane.showConfirmDialog(null, 
				 "Are you sure you want to clear all Symbols\n" +
				 "in the currently selected Alphabet?", 
				 "Warning", 
				 JOptionPane.OK_CANCEL_OPTION,
				 JOptionPane.WARNING_MESSAGE);
		if (n==0){
			myMultiDefPanel.getCurrentAlphabetPanel().getAssociatedAlphabet().clear();
		}
		super.actionPerformed(e);
	}
	

}
