package JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.Scrollable;
import javax.swing.text.JTextComponent;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.IUpdate;
import JFLAPnew.formaldef.gui.definitionpanel.MouseClickAdapter;
import JFLAPnew.formaldef.gui.definitionpanel.actions.AddSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.actions.ModifySymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.actions.RemoveSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.actions.SetSpecialSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu.SymbolMenu;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu.SymbolMenuItem;
import JFLAPnew.formaldef.symbols.Symbol;

public class SymbolBar extends JPanel implements IUpdate, Scrollable{

	private IAlphabet<Symbol> myAlphabet;
//	private ArrayList<SymbolBox> myBoxes;
	private SymbolMenu myBoxMenu;
	private JTextComponent myFocus;
	
	public SymbolBar(IAlphabet alph) {
		myAlphabet = alph;
//		myBoxes = new ArrayList<SymbolBox>();
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setAlignmentY(TOP_ALIGNMENT);
		setUpBoxMenu();
		setUpFocusManager();
//		this.setBackground(Color.ORANGE);
	}

	private void setUpFocusManager() {
		KeyboardFocusManager focusManager =
		    KeyboardFocusManager.getCurrentKeyboardFocusManager();
		focusManager.addPropertyChangeListener(
		    new PropertyChangeListener() {
		    	
		        public void propertyChange(PropertyChangeEvent e) {
		            String prop = e.getPropertyName();
		            if (("focusOwner".equals(prop))){
		                  if (e.getNewValue() instanceof JTextComponent)
		                	  myFocus = ((JTextComponent)e.getNewValue());
		                  else
		                	  myFocus = null;
		            }
	            }
		    }
		);		
	}

	private void setUpBoxMenu() {
		myBoxMenu = new SymbolMenu(new AddSymbolAction(this),
				  new RemoveSymbolAction(this),
				  new ModifySymbolAction(this));

		if (this.getAssociatedAlphabet() instanceof ISpecialSymbol)
			myBoxMenu.add(new SetSpecialSymbolAction(this));
	}

	public IAlphabet<? extends Symbol> getAssociatedAlphabet(){
		return myAlphabet;
	}
	
	
	@Override
	public Component add(Component comp) {
		
		comp.addMouseListener(new MouseClickAdapter<SymbolBox>() {

			@Override
			public void rightClickResponse(MouseEvent e, SymbolBox component) {
				component.getMenu().show(component, e.getX(), e.getY());
			}

			@Override
			public void leftClickResponse(MouseEvent e, SymbolBox component) {
				if (myFocus != null) myFocus.replaceSelection(component.getSymbol().getString());
			}
			
		});
//		myBoxes.add((SymbolBox) comp);
		return super.add(comp);
	}

	@Override
	public void update() {
		this.removeAll();
		int width = 0;
		int i = 0;
		
		for (Symbol s: myAlphabet.sortedList()){
			SymbolBox sb = new SymbolBox(s);
			sb.setPreferredSize(new Dimension(sb.getPreferredSize().width, AlphabetPane.HEIGHT));
			this.add(sb);
			width += sb.getPreferredSize().width;
		}
		this.setPreferredSize(new Dimension(width, AlphabetPane.HEIGHT));
		this.setMinimumSize(new Dimension(width, AlphabetPane.HEIGHT));

	}

	public SymbolMenu getBoxMenu() {
		return myBoxMenu;
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return this.getPreferredSize();
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle r, int orient, int dir) {
		return getScrollableUnitIncrement(r, orient, dir);
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle r, int orient, int dir) {
		return (int) (dir*(this.getWidth() - r.width)/10.0);
	}

	
}
