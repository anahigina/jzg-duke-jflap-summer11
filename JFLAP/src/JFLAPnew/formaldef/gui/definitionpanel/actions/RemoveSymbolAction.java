package JFLAPnew.formaldef.gui.definitionpanel.actions;

import gui.errors.BooleanWrapper;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TapeAlphabet;
import JFLAPnew.formaldef.gui.definitionpanel.GUIConstants;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;
import JFLAPnew.formaldef.symbols.Symbol;

public class RemoveSymbolAction extends AbstractEditSymbolAction {

	public RemoveSymbolAction(SymbolBar alph) {
		super(GUIConstants.REMOVE_LABEL, alph);
	}

	@Override
	protected BooleanWrapper executeAdjustment(IAlphabet alph, ActionEvent e) {
		int n = 0;
		if (alph.getClass().isAssignableFrom(TapeAlphabet.class)){
			n = JOptionPane.showConfirmDialog(
				    null,
				    "Removing this symbol from the " + alph.getName() + "\n" +
				    		" will also remove it from the Input Alphabet.", //CHEATING!!!
				    "Warning",
				    JOptionPane.OK_CANCEL_OPTION);
			if (n != 0)
				return new BooleanWrapper(false, "Symbol was not removed.");
		}
		else{
			n = JOptionPane.showConfirmDialog(
				    null,
				    "This symbol will be permanently removed from the " + alph.getName() + ".",
				    "Warning",
				    JOptionPane.OK_CANCEL_OPTION);
		}
		if (n == 0)
			return(alph.remove(this.getSymbol(e)));
		return new BooleanWrapper(false, "Symbol was not removed.");
	}

}
