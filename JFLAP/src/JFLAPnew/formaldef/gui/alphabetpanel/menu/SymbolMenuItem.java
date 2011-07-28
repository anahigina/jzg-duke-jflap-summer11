package JFLAPnew.formaldef.gui.alphabetpanel.menu;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;

public class SymbolMenuItem extends JMenuItem implements ISymbol {

	public SymbolMenuItem(Action a) {
		super(a);
		this.setAccelerator((KeyStroke) a.getValue(Action.ACCELERATOR_KEY));
	}

	@Override
	public Symbol getSymbol() {
		return ((ISymbol)((JPopupMenu) this.getParent()).getInvoker()).getSymbol();
	}

	@Override
	public void setSymbol(Symbol s) {
		//do nothing
	}

}
