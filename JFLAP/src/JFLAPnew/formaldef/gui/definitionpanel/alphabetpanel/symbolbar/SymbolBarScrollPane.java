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
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.IUpdate;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu.SymbolMenu;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.scroller.ThinScrollBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.scroller.ThinScrollBarUI;
import JFLAPnew.formaldef.symbols.Symbol;


public class SymbolBarScrollPane extends JScrollPane  implements IUpdate{

	private SymbolBar myBar;
	public static final int SB_HEIGHT = 3;
	
	public SymbolBarScrollPane(IAlphabet alph){
		super();
		myBar = new SymbolBar(alph);
		this.setViewportView(myBar);
		this.setHorizontalScrollBar(new ThinScrollBar(ScrollBar.HORIZONTAL, SB_HEIGHT));
//		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		
	}

	public SymbolBar getSymbolBar() {
		return myBar;
	}

	@Override
	public void update() {
		myBar.update();
//		this.setVisible(myBar.getComponentCount() > 0);
		Boolean spans = this.getViewport().getVisibleRect().width >= this.getViewport().getViewSize().width;
		this.setMaximumSize(new Dimension(myBar.getPreferredSize().width, 
											myBar.getPreferredSize().height + SB_HEIGHT));
		this.setPreferredSize(new Dimension(myBar.getPreferredSize().width+this.getBorder().getBorderInsets(this).left*3, 
						myBar.getPreferredSize().height + (spans ? 0: SB_HEIGHT)));
		
//		this.setMinimumSize(new Dimension(myBar.getSize().width, myBar.getPreferredSize().height));
//		this.setPreferredSize(new Dimension(
//					myBar.getSize().width, 
//					Math.min(
//						myBar.getSize().height + (this.getHorizontalScrollBar().isVisible() ? SB_HEIGHT : 0),
//						AlphabetPane.HEIGHT)));
//		this.setMaximumSize(new Dimension(myBar.getSize().width, AlphabetPane.HEIGHT));
//		this.revalidate();
		
//		this.setMaximumSize(myBar.getSize());
//		this.setPreferredSize(myBar.getPreferredSize());
//		System.out.println("Rectangle @update: " + this.getViewRect());
	}
	
//	@Override
//	public void setSize(Dimension d){
//		super.setSize(this.getPreferredSize());
//	}
//	
//	
//	@Override
//	public void setPreferredSize(Dimension d){
//		super.setPreferredSize(trimDimension(d));
//	}
//	
//	public Dimension trimDimension(Dimension d){
//		int height = d.height;
//		int width = d.width;
//		
//		if (width < this.getMinimumSize().width)
//			width = this.getMinimumSize().width;
//		else if(width > this.getMaximumSize().width)
//			width = this.getMaximumSize().width;
//	
//		if (height < this.getMinimumSize().height)
//			height = this.getMinimumSize().height;
//		else if(height > this.getMaximumSize().height)
//			height = this.getMaximumSize().height;
//		
//		return new Dimension(height, width);
//	}
}
