package JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.grouping.SpecialSymbolFactory;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.IUpdate;
import JFLAPnew.formaldef.gui.definitionpanel.actions.AddSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.actions.ModifySymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.actions.RemoveSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.actions.SetSpecialSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu.IMenu;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu.SymbolMenu;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;


public class SymbolBox extends JButton implements IMenu, ISymbol, IUpdate{

	private Symbol mySymbol;
	private SymbolMenu myMenu;
	
	
	public SymbolBox(Symbol s) {
		this.setMargin(new Insets(0,0,0,0));
		this.setFont(AlphabetPane.FONT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.setFocusable(false);
		this.setSymbol(s);
	}

	@Override
	public Symbol getSymbol() {
		return mySymbol;
	}

	@Override
	public void setSymbol(Symbol symbol) {
		mySymbol = symbol;
		this.update();
	}

	
	@Override
	public SymbolMenu getMenu(){
		return ((SymbolBar) this.getParent()).getBoxMenu();
	}

	@Override
	public void setUpMenu() {
	}

	@Override
	public void update() {
		this.setBackground(mySymbol.isSpecial() ? GUIConstants.SPECIAL_SYMBOL : GUIConstants.DEFAULT);
		setText(mySymbol.isSpecial()? 
				"<html><u><i><b>" + mySymbol.getString() + "</b></i></u></html>": 
				mySymbol.getString());
		this.setPreferredSize(new Dimension(this.getPreferredSize().width, AlphabetPane.HEIGHT));
		this.setMinimumSize(new Dimension(this.getPreferredSize().width, AlphabetPane.HEIGHT));
	}

}
