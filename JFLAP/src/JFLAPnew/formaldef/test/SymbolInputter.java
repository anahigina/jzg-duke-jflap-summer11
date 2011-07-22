package JFLAPnew.formaldef.test;

import gui.errors.BooleanWrapper;
import gui.errors.JFLAPError;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import JFLAPnew.formaldef.gui.DefinitionPanel;
import JFLAPnew.formaldef.gui.actions.AddSymbolAction;


public class SymbolInputter extends JTextField implements ActionListener{

	private DefinitionPanel myPanel;

	public SymbolInputter(DefinitionPanel defPanel) {
		myPanel = defPanel;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (myPanel.getSelected() != null){
			new AddSymbolAction(myPanel.getSelected(), this.getText()).actionPerformed(e);
			this.selectAll();
		}
		else
			JFLAPError.show("You must first select an alphabet " +
							"to which the Symbol will be added.", "Error");
	}
	
	

}
