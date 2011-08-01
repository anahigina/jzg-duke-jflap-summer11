package JFLAPnew.formaldef.gui.definitioncreator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.MetaDefinition;
import JFLAPnew.formaldef.gui.definitioncreator.chooser.ModuleChooser;
import JFLAPnew.formaldef.gui.definitioncreator.interaction.UserInteractionPanel;
import JFLAPnew.formaldef.gui.definitioncreator.interaction.inputpanel.SymbolInputPanel;
import JFLAPnew.formaldef.gui.definitioncreator.interaction.toolbar.DefinitionCreationToolbar;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import JFLAPnew.formaldef.gui.selection.UpDownSelectingAdapter;

public class DefinitionCreationPanel extends JPanel{

	private MultiDefitionPanel myMultiDefPanel;

	public DefinitionCreationPanel(MetaDefinition def, boolean saveOnClose) {
		myMultiDefPanel = new MultiDefitionPanel(def, saveOnClose);
		this.setLayout(new BorderLayout());
		this.add(myMultiDefPanel, BorderLayout.CENTER);
		this.add(new UserInteractionPanel(myMultiDefPanel, saveOnClose), BorderLayout.SOUTH);
		myMultiDefPanel.addKeyListener(new UpDownSelectingAdapter(myMultiDefPanel, true));
	}

	public MultiDefitionPanel getMetaDefinitionPanel() {
		return myMultiDefPanel;
	}

	public static DefinitionCreationPanel createWithChooser(MetaDefinition def){
		return new DefinitionCreationPanel(new ModuleChooser(300,300, def).getResultingDefinition(), true);
	}

}
