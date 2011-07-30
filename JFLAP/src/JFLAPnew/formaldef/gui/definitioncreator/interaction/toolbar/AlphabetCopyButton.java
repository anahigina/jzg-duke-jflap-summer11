package JFLAPnew.formaldef.gui.definitioncreator.interaction.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.definitioncreator.MultiDefitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;

public class AlphabetCopyButton extends DefinitionCreationToolbarButton {


	public AlphabetCopyButton(MultiDefitionPanel multiDef) {
		super("Copy", multiDef);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AlphabetPane a = myMultiDefPanel.getCurrentAlphabetPanel();
		if (a == null) JOptionPane.showMessageDialog(null, 
											"You must select an alphabet in the current defintion to Copy.", 
											"Error", 
											JOptionPane.ERROR_MESSAGE);
		else {
			((DefinitionCreationToolbar) AlphabetCopyButton.this.getParent()).getPasteButton().setCopied(
					new AlphabetPane(a.getAssociatedAlphabet().clone()));
			System.out.println(a.getAssociatedAlphabet());
		}
		
	}
	
}
