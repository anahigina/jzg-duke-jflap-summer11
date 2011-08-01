package JFLAPnew.formaldef.gui.definitionpanel.actions;

import gui.errors.BooleanWrapper;

import java.awt.event.ActionEvent;

import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;
import JFLAPnew.formaldef.symbols.Symbol;

public class SetSpecialSymbolAction extends AbstractEditSymbolAction {

	public SetSpecialSymbolAction(SymbolBar bar) {
		super("Set " + ((ISpecialSymbol) bar.getAssociatedAlphabet()).getSpecialSymbolName(), bar);
	}

	@Override
	protected BooleanWrapper executeAdjustment(IAlphabet alph, ActionEvent e) {
		return Alphabet.setSpecialSymbol(alph, this.getSymbol(e));
	}

}
