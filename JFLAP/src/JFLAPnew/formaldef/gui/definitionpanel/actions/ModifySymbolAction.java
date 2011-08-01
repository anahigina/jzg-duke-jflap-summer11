package JFLAPnew.formaldef.gui.definitionpanel.actions;

import gui.errors.BooleanWrapper;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;
import JFLAPnew.formaldef.symbols.Symbol;

public class ModifySymbolAction extends AbstractEditSymbolAction {

	public ModifySymbolAction(SymbolBar bar) {
		super(GUIConstants.MODIFY_LABEL, bar);
	}

	@Override
	protected BooleanWrapper executeAdjustment(IAlphabet alph, ActionEvent e) {
		String s = JOptionPane.showInputDialog(null, 
											   "Modify the symbol, click ok to complete",
											   this.getSymbol(e).getString());
		if (s == null)
			return new BooleanWrapper(false, "The symbol was not modified");
		return (alph.modify(this.getSymbol(e),s));
	}

}
