package JFLAPnew.formaldef.gui.alphabetpanel.symbolbar;

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.grouping.SpecialSymbolFactory;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.actions.AddSymbolAction;
import JFLAPnew.formaldef.gui.actions.ModifySymbolAction;
import JFLAPnew.formaldef.gui.actions.RemoveSymbolAction;
import JFLAPnew.formaldef.gui.actions.SetSpecialSymbolAction;
import JFLAPnew.formaldef.gui.alphabetpanel.IUpdate;
import JFLAPnew.formaldef.gui.alphabetpanel.menu.IMenu;
import JFLAPnew.formaldef.gui.alphabetpanel.menu.SymbolMenu;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;


public class SymbolBox extends JButton implements IMenu, ISymbol, IUpdate {

	private Symbol mySymbol;
	private SymbolMenu myMenu;
	
	
	public SymbolBox(Symbol s) {
		this.setSymbol(s);
		this.setMargin(new Insets(0,0,0,0));
		this.update();
	}

	@Override
	public Symbol getSymbol() {
		return mySymbol;
	}

	@Override
	public void setSymbol(Symbol symbol) {
		mySymbol = symbol;
	}

	@Override
	public void update(){
		this.setText(mySymbol.getString());
		this.setBackground(mySymbol.isSpecial() ? GUIConstants.SPECIAL_SYMBOL : GUIConstants.DEFAULT);
		System.out.println("Box " + this.getSymbol() + " updated");
	}
	
	
	@Override
	public SymbolMenu getMenu(){
		return ((SymbolBar) this.getParent()).getBoxMenu();
	}

	@Override
	public void setUpMenu() {
	}

}
