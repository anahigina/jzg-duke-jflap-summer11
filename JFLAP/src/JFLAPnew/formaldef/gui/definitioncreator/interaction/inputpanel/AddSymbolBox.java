package JFLAPnew.formaldef.gui.definitioncreator.interaction.inputpanel;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddSymbolBox extends JButton {

	public AddSymbolBox(ActionListener listener) {
		super("Add");
		this.addActionListener(listener);
	}

}
