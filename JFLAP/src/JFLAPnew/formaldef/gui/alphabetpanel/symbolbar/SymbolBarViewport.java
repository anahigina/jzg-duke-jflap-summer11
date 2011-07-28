package JFLAPnew.formaldef.gui.alphabetpanel.symbolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.border.EtchedBorder;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.alphabetpanel.IUpdate;
import JFLAPnew.formaldef.gui.alphabetpanel.menu.SymbolMenu;
import JFLAPnew.formaldef.symbols.Symbol;


public class SymbolBarViewport extends JViewport  implements IUpdate{

	private SymbolBar myBar;
	
	public SymbolBarViewport(IAlphabet alph){
		super();
		myBar = new SymbolBar(alph);
		this.setView(myBar);
	}

	public SymbolBar getSymbolBar() {
		return myBar;
	}

	@Override
	public void update() {
		myBar.update();
		this.setMaximumSize(myBar.getSize());
	}

}
