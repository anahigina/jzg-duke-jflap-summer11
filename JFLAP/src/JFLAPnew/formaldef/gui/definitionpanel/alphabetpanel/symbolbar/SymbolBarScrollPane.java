package JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar;

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
import JFLAPnew.formaldef.gui.definitionpanel.GUIConstants;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.IUpdate;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu.SymbolMenu;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.scroller.ThinScrollBar;
import JFLAPnew.formaldef.symbols.Symbol;


public class SymbolBarScrollPane extends JScrollPane  implements IUpdate{

	private SymbolBar myBar;
	private static final int SB_HEIGHT = 3;
	
	public SymbolBarScrollPane(IAlphabet alph){
		super();
		myBar = new SymbolBar(alph);
		this.setViewportView(myBar);
		this.setHorizontalScrollBar(new ThinScrollBar(ScrollBar.HORIZONTAL, SB_HEIGHT));
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		this.update();
	}

	public SymbolBar getSymbolBar() {
		return myBar;
	}

	@Override
	public void update() {
		myBar.update();
		
		this.setMaximumSize(new Dimension(myBar.getSize().width + 3, myBar.getSize().height));
		this.repaint();
//		this.setMaximumSize(myBar.getSize());
//		this.setPreferredSize(myBar.getPreferredSize());
//		System.out.println("Bar Size: " + myBar.getSize());
//		System.out.println("Bar Pref Size: " + myBar.getPreferredSize());
//		System.out.println("Rectangle @update: " + this.getViewRect());
	}

}
