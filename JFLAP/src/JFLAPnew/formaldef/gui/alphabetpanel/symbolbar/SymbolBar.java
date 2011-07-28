package JFLAPnew.formaldef.gui.alphabetpanel.symbolbar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.gui.MouseClickAdapter;
import JFLAPnew.formaldef.gui.actions.AddSymbolAction;
import JFLAPnew.formaldef.gui.actions.ModifySymbolAction;
import JFLAPnew.formaldef.gui.actions.RemoveSymbolAction;
import JFLAPnew.formaldef.gui.actions.SetSpecialSymbolAction;
import JFLAPnew.formaldef.gui.alphabetpanel.IUpdate;
import JFLAPnew.formaldef.gui.alphabetpanel.menu.SymbolMenu;
import JFLAPnew.formaldef.gui.alphabetpanel.menu.SymbolMenuItem;
import JFLAPnew.formaldef.symbols.Symbol;

public class SymbolBar extends JPanel implements IUpdate{

	private IAlphabet<Symbol> myAlphabet;
	private ArrayList<SymbolBox> myBoxes;
	private SymbolMenu myBoxMenu;
	
	public SymbolBar(IAlphabet alph) {
		myAlphabet = alph;
		myBoxes = new ArrayList<SymbolBox>();
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setAlignmentY(CENTER_ALIGNMENT);
		setUpBoxMenu();
		
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
				System.out.println(component.getSymbol().getString());
				System.out.println(component.getSize().width);
			}
			
		});
		myBoxes.add((SymbolBox) comp);
		return super.add(comp);
	}

	@Override
	public void update() {
		this.removeAll();
		for (Symbol s: myAlphabet.getSymbols()){
			SymbolBox sb = new SymbolBox(s);
			this.add(sb);
			
			
		}
	}

	public SymbolMenu getBoxMenu() {
		return myBoxMenu;
	}

	
}
