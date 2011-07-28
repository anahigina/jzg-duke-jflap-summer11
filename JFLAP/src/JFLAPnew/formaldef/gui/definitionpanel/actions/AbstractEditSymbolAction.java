package JFLAPnew.formaldef.gui.definitionpanel.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import JFLAPnew.formaldef.IFormallyDefined;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.IUpdate;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;

import gui.action.RestrictedAction;
import gui.errors.BooleanWrapper;
import gui.errors.JFLAPError;

public abstract class AbstractEditSymbolAction extends RestrictedAction {

	private SymbolBar myBar;

	public AbstractEditSymbolAction(String string, SymbolBar bar) {
		super(string, null);
		myBar = bar;
		this.putValue(MNEMONIC_KEY, 
				(Integer)(int)((String) this.getValue(NAME)).charAt(0));
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		BooleanWrapper wasSuccess = this.executeAdjustment(this.getAlphabet(),e);
		if (wasSuccess.isFalse()){
			JFLAPError.show(wasSuccess);
			return;
		}
		DefinitionPanel.updateParentDefPanel( (JComponent) myBar.getParent());
	}
	

	private IAlphabet getAlphabet(){
		return myBar.getAssociatedAlphabet();
	}
	
	protected Symbol getSymbol(ActionEvent e){
		return ((ISymbol) e.getSource()).getSymbol();
	}
	
	protected abstract BooleanWrapper executeAdjustment(IAlphabet alph, ActionEvent e);
	
}
