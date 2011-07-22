package JFLAPnew.formaldef.gui.actions;

import gui.errors.BooleanWrapper;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TapeAlphabet;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;
import JFLAPnew.formaldef.symbols.Symbol;

public class RemoveSymbolAction extends AbstractEditSymbolAction {

	public RemoveSymbolAction(SymbolBar bar) {
		super(GUIConstants.REMOVE_LABEL, bar);
	}

	@Override
	protected BooleanWrapper executeAdjustment(IAlphabet alph, ActionEvent e) {
		int n = 0;
		System.out.println("WHAT LE FUCK");
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
		
		
		 n = JOptionPane.showConfirmDialog(
			    null,
			    "This symbol will be permanently removed from the " + alph.getName() + ".",
			    "Warning",
			    JOptionPane.OK_CANCEL_OPTION);
		if (n == 0)
			return(alph.remove(this.getSymbol(e)));
		return new BooleanWrapper(false, "Symbol was not removed.");
	}

}
