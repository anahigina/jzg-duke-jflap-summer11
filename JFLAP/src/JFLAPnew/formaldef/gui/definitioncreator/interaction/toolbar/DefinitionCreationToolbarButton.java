package JFLAPnew.formaldef.gui.definitioncreator.interaction.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import JFLAPnew.formaldef.gui.definitioncreator.MultiDefitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;

public abstract class DefinitionCreationToolbarButton extends JButton implements ActionListener {

	protected MultiDefitionPanel myMultiDefPanel;

	
	public DefinitionCreationToolbarButton(String text,
			MultiDefitionPanel multiDef) {
		super(text);
		myMultiDefPanel = multiDef;
		this.addActionListener(this);
	}
	
	@Override
	public  void actionPerformed(ActionEvent e){
		myMultiDefPanel.revalidate();
	}
	
}
