package JFLAPnew.formaldef.gui.symbolbar;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.mouseadapter.MouseAdapterFactory;
import JFLAPnew.formaldef.gui.symbolbar.menu.SymbolMenu;
import JFLAPnew.formaldef.gui.symbolbar.menu.SymbolMenuFactory;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;


public class SymbolBox extends JButton implements IMenu, IUpdate, ISymbol {

	private Symbol mySymbol;
	private JPopupMenu myMenu;
	
	
	public SymbolBox(Symbol s, int height) {
		this.setSymbol(s);
		this.addMouseListener(MouseAdapterFactory.manufactureListener(this.getClass()));
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
	public void update(){
		this.setText(mySymbol.getString());
		this.setBackground(mySymbol.isSpecial() ? GUIConstants.SPECIAL_SYMBOL : GUIConstants.DEFAULT);
	}
	
	@Override
	public void setUpMenu(Container parent) {
		boolean needsSpecial = ISpecialSymbol.class.isAssignableFrom(
				((SymbolBar) parent).getAssociatedAlphabet().getClass());
		myMenu = SymbolMenuFactory.manufactureMenu(((SymbolBar) parent),
													this,
													SymbolMenuFactory.getBoxOptions(needsSpecial));
	}
	
	@Override
	public JPopupMenu getMenu(){
		return myMenu;
	}

}
