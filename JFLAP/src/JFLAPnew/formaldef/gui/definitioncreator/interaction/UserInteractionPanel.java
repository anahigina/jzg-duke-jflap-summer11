package JFLAPnew.formaldef.gui.definitioncreator.interaction;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import JFLAPnew.formaldef.gui.definitioncreator.MultiDefitionPanel;
import JFLAPnew.formaldef.gui.definitioncreator.interaction.inputpanel.SymbolInputPanel;
import JFLAPnew.formaldef.gui.definitioncreator.interaction.toolbar.DefinitionCreationToolbar;

public class UserInteractionPanel extends JPanel {
	public UserInteractionPanel(MultiDefitionPanel panel, boolean saveOnClose){
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(new DefinitionCreationToolbar(panel));
		this.add(new SymbolInputPanel(panel, saveOnClose));
	}
}
