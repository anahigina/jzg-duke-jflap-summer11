package JFLAPnew.formaldef.gui.actions;

import gui.errors.BooleanWrapper;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;
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
		if (myNew == null)
			myNew = JOptionPane.showInputDialog(null, "Input new symbol, click OK to complete");
		if (myNew == null) return new BooleanWrapper(true);
		return alph.add(myNew);
	}

}
