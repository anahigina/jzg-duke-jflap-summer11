package JFLAPnew.formaldef.gui.definitionpanel.actions;

import gui.errors.BooleanWrapper;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.definitionpanel.GUIConstants;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;

public class AddSymbolAction extends AbstractEditSymbolAction {

	private String myNew;

	public AddSymbolAction(SymbolBar bar) {
		this(bar, null);
	}
	
	public AddSymbolAction(SymbolBar bar, String newSym) {
		super(GUIConstants.ADD_LABEL, bar);
		myNew = newSym;
	}

	public boolean canBeApplied(Object object) {
		return true;
	}
	
	@Override
	protected BooleanWrapper executeAdjustment(IAlphabet alph, ActionEvent e) {
		
		if (myNew == null) //if using menu
			myNew = JOptionPane.showInputDialog(null, "Input new symbol, click OK to complete");
		if (myNew == null) //If user input nothing/hit cancel
			return new BooleanWrapper(true);
		BooleanWrapper result = alph.add(myNew);
		this.clear();
		return result;
	}

	private void clear() {
		myNew = null;
	}

}
