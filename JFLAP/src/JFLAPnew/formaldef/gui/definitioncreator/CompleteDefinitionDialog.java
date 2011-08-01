package JFLAPnew.formaldef.gui.definitioncreator;

import java.awt.Window;

import javax.swing.JDialog;

import JFLAPnew.formaldef.MetaDefinition;

public class CompleteDefinitionDialog extends JDialog {

	
	private DefinitionCreationPanel myPanel;

	public CompleteDefinitionDialog(MetaDefinition def){
		super((Window)null,"Complete your Definition");
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		this.add(myPanel = new DefinitionCreationPanel(def, true));
		this.setLocation(500, 300);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	public MetaDefinition getResultingDefinition() {
		return myPanel.getMetaDefinitionPanel().getMetaDefinition();
	}
}
