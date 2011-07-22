package JFLAPnew.formaldef.gui.actions;

import gui.errors.BooleanWrapper;

import java.awt.event.ActionEvent;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;
import JFLAPnew.formaldef.symbols.Symbol;

public class SetSpecialSymbolAction extends AbstractEditSymbolAction {

	public SetSpecialSymbolAction(SymbolBar bar) {
		super("Set " + ((ISpecialSymbol)bar.getAssociatedAlphabet()).getSpecialSymbolName(), bar);
	}

	@Override
	protected BooleanWrapper executeAdjustment(IAlphabet alph, ActionEvent e) {
		return ((ISpecialSymbol) alph).setSpecialSymbol(this.getSymbol(e));
	}

}
