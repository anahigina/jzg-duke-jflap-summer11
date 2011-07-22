package JFLAPnew.formaldef.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import JFLAPnew.formaldef.IFormallyDefined;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.DefinitionPanel;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.symbolbar.menu.SymbolMenu;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;

import gui.action.RestrictedAction;
import gui.errors.BooleanWrapper;
import gui.errors.JFLAPError;

public abstract class AbstractEditSymbolAction extends RestrictedAction {

	private SymbolBar myBar;
	private Symbol mySymbol;

	public AbstractEditSymbolAction(String string, SymbolBar bar) {
		super(string, null);
		myBar = bar;
		this.putValue(MNEMONIC_KEY, 
				(Integer)(int)((String) this.getValue(NAME)).charAt(0));
	}

	public boolean canBeApplied(Object object) {
		return object instanceof ISymbol;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.canBeApplied(e.getSource()))
			return;
		BooleanWrapper wasSuccess = this.executeAdjustment(this.getAlphabet(),e);
		if (wasSuccess.isTrue())
			((DefinitionPanel) myBar.getParent()).update();
		else
			JFLAPError.show(wasSuccess);
	}
	

	private IAlphabet getAlphabet(){
		return myBar.getAssociatedAlphabet();
	}
	
	protected Symbol getSymbol(ActionEvent e){
		return ((ISymbol) e.getSource()).getSymbol();
	}
	
	protected abstract BooleanWrapper executeAdjustment(IAlphabet alph, ActionEvent e);
	
}
