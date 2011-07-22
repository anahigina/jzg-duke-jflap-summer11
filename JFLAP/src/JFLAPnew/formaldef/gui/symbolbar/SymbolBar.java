package JFLAPnew.formaldef.gui.symbolbar;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.mouseadapter.MouseAdapterFactory;
import JFLAPnew.formaldef.gui.symbolbar.menu.SymbolMenu;
import JFLAPnew.formaldef.gui.symbolbar.menu.SymbolMenuFactory;
import JFLAPnew.formaldef.symbols.Symbol;


public class SymbolBar extends JToolBar implements IMenu, IUpdate{

	private IAlphabet<Symbol> myAlphabet;
	private ArrayList<SymbolBox> myBoxes;
	private SymbolMenu myMenu;
	private boolean amSelected;
	private JLabel myLabel;
	
	public SymbolBar(IAlphabet alph) {
		myAlphabet = alph;
		myBoxes = new ArrayList<SymbolBox>();
		amSelected = false;
		initialize();
		update();
	}

	public IAlphabet getAssociatedAlphabet(){
		return myAlphabet;
	}

	private void initialize() {
		this.setToolTipText(myAlphabet.getName());
		this.setBackground(GUIConstants.DEFAULT);
		this.add(myLabel = new JLabel(myAlphabet.getName()));
		myLabel.setOpaque(true);
		this.add(new JLabel(" = { "));
		this.add(new JLabel(" }"));
		this.setUpMenu(null);
		this.addMouseListener(MouseAdapterFactory.manufactureListener(this.getClass()));
		this.initializeBoxes();
	}

	@Override
	public void update() {
		this.setFloatable(!JFLAPpreferences.isUserDefinedAlphabet());
		myLabel.setBackground( amSelected ? GUIConstants.BAR_SELECTED : GUIConstants.DEFAULT);
		this.setBackground( amSelected ? GUIConstants.BAR_SELECTED : GUIConstants.DEFAULT);
		updateBoxes();
	}

	
	public void updateBoxes() {
		this.clearBoxes();
		this.initializeBoxes();
		this.updateUI();
	}
	
	private void initializeBoxes() {
		for (Symbol s: myAlphabet.getSymbols()){
			this.add(this.createBox(s));
		}
	}

	private SymbolBox createBox(Symbol s) {
		SymbolBox box = new SymbolBox(s, this.getHeight());
		box.setUpMenu(this);
		return box;
	}
	
	public void add(SymbolBox box){
		myBoxes.add(box);
		super.add(box, this.getComponentCount()-1);
	}

	private void clearBoxes() {
		for (SymbolBox sb: myBoxes){
			this.remove(sb);
		}
		myBoxes.clear();
	}

	private Set<Symbol> getSymbolSet() {
		Set<Symbol> symbols = new HashSet<Symbol>();
		for (SymbolBox sb: myBoxes){
			
			symbols.add(sb.getSymbol());
		}
		return symbols;
	}

	@Override
	public void setUpMenu(Container parent) {
		myMenu = SymbolMenuFactory.manufactureMenu(this, null, SymbolMenuFactory.ADD_OPTION);
	}

	@Override
	public JPopupMenu getMenu() {
		return myMenu;
	}
	
	public void setSelected(boolean b) {
		amSelected = b;
	}
	
	public boolean isSelected(){
		return amSelected;
	}
}
