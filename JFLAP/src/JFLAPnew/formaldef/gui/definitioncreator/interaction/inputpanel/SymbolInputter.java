package JFLAPnew.formaldef.gui.definitioncreator.interaction.inputpanel;

import gui.errors.BooleanWrapper;
import gui.errors.JFLAPError;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import JFLAPnew.formaldef.gui.definitioncreator.MultiDefitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.actions.AddSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;


public class SymbolInputter extends JTextField implements ActionListener{

	private MultiDefitionPanel myPanel;

	public SymbolInputter(MultiDefitionPanel multiDefPanel, int i) {
		super(i);
		myPanel = multiDefPanel;
		this.addActionListener(this);
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					((JComponent) myPanel.getSelected()).requestFocusInWindow();
				}
			}
			
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AlphabetPane pane = myPanel.getCurrentAlphabetPanel();
		if (pane != null){
			new AddSymbolAction(pane.getSymbolBar(), this.getText()).actionPerformed(e);
			this.selectAll();
			myPanel.revalidate();
		}
		else
			JFLAPError.show("You must first select an alphabet " +
							"to which the Symbol will be added.", "Error");
	}
	
	

}
