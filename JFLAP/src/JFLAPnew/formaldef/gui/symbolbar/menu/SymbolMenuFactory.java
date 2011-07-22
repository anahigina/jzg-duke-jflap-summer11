package JFLAPnew.formaldef.gui.symbolbar.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.actions.AddSymbolAction;
import JFLAPnew.formaldef.gui.actions.ModifySymbolAction;
import JFLAPnew.formaldef.gui.actions.RemoveSymbolAction;
import JFLAPnew.formaldef.gui.actions.SetSpecialSymbolAction;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;
import JFLAPnew.formaldef.symbols.ISymbol;

public class SymbolMenuFactory {

	public static final int ADD_OPTION = 0,
							MODIFY_OPTION = 1,
							REMOVE_OPTION = 2,
							SET_SPECIAL_OPTION = 3;
	
	public static SymbolMenuItem createItem(int i, SymbolBar bar) {
		return new SymbolMenuItem(getActionByInt(i, bar));
	}
	
	
	public static SymbolMenu manufactureMenu(SymbolBar bar, ISymbol source, Integer ... ops){
		SymbolMenu menu = new SymbolMenu(source);
		List<Integer> options = Arrays.asList(ops); 
		Collections.sort(options);
		for (int i : options){
			menu.add(createItem(i, bar));
		}
		return menu;
	}

	private static Action getActionByInt(int i, SymbolBar bar) {
		switch (i){
		case ADD_OPTION: return new AddSymbolAction(bar);
		case MODIFY_OPTION: return new ModifySymbolAction(bar);
		case REMOVE_OPTION:  return new RemoveSymbolAction(bar);
		case SET_SPECIAL_OPTION: return new SetSpecialSymbolAction(bar);
		}
		return null;
	}

	public static Integer[] getBoxOptions(boolean needsSpecial) {
		ArrayList<Integer> options = new ArrayList<Integer>(Arrays.asList(new Integer[]{ADD_OPTION, MODIFY_OPTION, REMOVE_OPTION}));
		if (needsSpecial) options.add(SET_SPECIAL_OPTION);
		return options.toArray(new Integer[0]);
	}
}
