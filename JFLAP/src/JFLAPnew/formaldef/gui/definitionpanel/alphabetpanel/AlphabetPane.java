package JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.GUIConstants;
import JFLAPnew.formaldef.gui.definitionpanel.MouseClickAdapter;
import JFLAPnew.formaldef.gui.definitionpanel.actions.AddSymbolAction;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu.IMenu;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.menu.SymbolMenu;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.scroller.ThinScrollBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;
import JFLAPnew.formaldef.symbols.Symbol;

public class AlphabetPane extends JToolBar implements ISelectable, IMenu, IUpdate{

	private SymbolBarScrollPane myViewport;
	private JLabel[] myLabels;
	
	private IAlphabet myAlphabet;
	private boolean amSelected;
	private SymbolMenu myMenu;
	
	
	public final static int HEIGHT = 50;
	public final static Font FONT = new Font("Dialog", 1, HEIGHT/2-1);

	public AlphabetPane(IAlphabet a){
		myAlphabet = a;
		setSelected(false);
		this.setToolTipText(getAssociatedAlphabet().getName());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		setUpLayout();
		initlializeComponents();
	}

	private void initlializeComponents() {
		this.setUpLabels();
		
		for (JComponent c: new JComponent[]{
				myLabels[0],
				myLabels[1],
				myViewport = new SymbolBarScrollPane(this.getAssociatedAlphabet()),
				myLabels[2],}){
			this.add(c);
			c.setAlignmentX(LEFT_ALIGNMENT);
			c.setAlignmentY(CENTER_ALIGNMENT);
		}
		this.add(Box.createHorizontalGlue());
		this.setUpMenu();
	}

	private void setUpLabels() {
		myLabels = new JLabel[]{new JLabel(
				this.getAssociatedAlphabet().getName()+ " = "),
				new JLabel("{"),
				new JLabel("}")} ;
		for (JLabel label: myLabels){
			label.setFont(FONT);
		}
	}

	private void setUpLayout() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layout);
	}

	public IAlphabet<? extends Symbol> getAssociatedAlphabet() {
		return myAlphabet;
	}

	@Override
	public void setSelected(boolean b) {
		amSelected = b;
	}
	
	@Override
	public boolean isSelected(){
		return amSelected;
	}

	public SymbolBar getSymbolBar() {
		return myViewport.getSymbolBar();
	}

	@Override
	public JPopupMenu getMenu() {
		return myMenu;
	}

	
	
	@Override
	public Component add(Component comp) {
		
		comp.addMouseListener(new MouseClickAdapter<Component>() {
			@Override
			public void rightClickResponse(MouseEvent e, Component component) {
				AlphabetPane.this.getMenu().show(component, e.getX(), e.getY());
			}

			@Override
			public void leftClickResponse(MouseEvent e, Component component) {
				((DefinitionPanel)AlphabetPane.this.getParent()).setSelectedBar(AlphabetPane.this);
			}
		});
		((JComponent)comp).setToolTipText(this.getToolTipText());
		return super.add(comp);
	}

	@Override
	public void update() {
		this.setBackground( amSelected ? GUIConstants.BAR_SELECTED : GUIConstants.DEFAULT);
		setSizes(new Dimension(this.getParent().getWidth(), HEIGHT));
		this.setFloatable(!JFLAPpreferences.isUserDefinedAlphabet());
		myViewport.update();
	}

	public void setSizes(Dimension d) {
		System.out.println("AlphabetPane Size:" + d);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
	}

	@Override
	public void setUpMenu() {
		myMenu = new SymbolMenu(new AddSymbolAction(this.getSymbolBar()));
	}

	
}
