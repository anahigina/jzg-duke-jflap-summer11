package JFLAPnew.formaldef.gui.definitioncreator.interaction.inputpanel;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import JFLAPnew.formaldef.gui.definitioncreator.MultiDefitionPanel;


public class SymbolInputPanel extends JPanel {

	private SymbolInputter myInputter;

	public SymbolInputPanel(MultiDefitionPanel multiDefPanel){
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(myInputter = new SymbolInputter(multiDefPanel, 30));
		this.add(new AddSymbolBox(myInputter));
		this.add(new CompleteButton(multiDefPanel));
	}
	
}
