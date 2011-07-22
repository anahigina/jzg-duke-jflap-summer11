package JFLAPnew.formaldef.gui.symbolbar.menu;

import java.awt.Component;

import javax.swing.JPopupMenu;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBox;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;

public class SymbolMenu extends JPopupMenu {

	
	private ISymbol mySymbolSource;
	
	public SymbolMenu(ISymbol source){
		mySymbolSource = source;
	}
	
	private void setSymbolSource(ISymbol symbol) {
		mySymbolSource = symbol;
	}
	
	public ISymbol getSymbolSource(){
		return mySymbolSource;
	}

	public Symbol getAssociatedSymbol(){
		return mySymbolSource.getSymbol();
	}

	@Override
	public boolean isEnabled() {
		return JFLAPpreferences.isUserDefinedAlphabet();
	}
	
	
	
}
