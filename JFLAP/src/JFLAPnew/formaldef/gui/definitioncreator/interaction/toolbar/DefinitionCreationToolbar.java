package JFLAPnew.formaldef.gui.definitioncreator.interaction.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.definitioncreator.MultiDefitionPanel;
import JFLAPnew.formaldef.symbols.Symbol;


public class DefinitionCreationToolbar extends JToolBar{

	private MultiDefitionPanel myMultiDef;
	private IAlphabet COPIED;
	private AlphabetPasteButton myPasteButton;
	
	public DefinitionCreationToolbar(MultiDefitionPanel multiDefPanel){
		myMultiDef = multiDefPanel;
		this.setOrientation(HORIZONTAL);
		this.add(new AlphabetCopyButton(myMultiDef));
		this.add(myPasteButton = new AlphabetPasteButton(myMultiDef));
		this.add(new AlphabetClearButton(myMultiDef));
	}

	public AlphabetPasteButton getPasteButton(){
		return myPasteButton;
	}

	
	
}
