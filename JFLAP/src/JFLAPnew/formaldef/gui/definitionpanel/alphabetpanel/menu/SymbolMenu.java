package JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.definitionpanel.actions.AbstractEditSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBox;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;

public class SymbolMenu extends JPopupMenu {


	public SymbolMenu(AbstractEditSymbolAction ... actions){
		for(AbstractEditSymbolAction a: actions){
			this.add(a);
		}
	}
	
	@Override
	public void show(Component invoker, int x, int y) {
		if (!JFLAPpreferences.isUserDefinedAlphabet())
			return;
		super.show(invoker, x, y);
	}

	@Override
	public JMenuItem add(Action a) {
		return super.add(new SymbolMenuItem(a));
	}

	
	
	
	
}
