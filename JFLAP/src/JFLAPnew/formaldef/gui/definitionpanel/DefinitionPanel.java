package JFLAPnew.formaldef.gui.definitionpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.portable.BoxedValueHelper;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.ISelectable;
import JFLAPnew.formaldef.gui.ISelector;
import JFLAPnew.formaldef.gui.IUpdate;
import JFLAPnew.formaldef.gui.UpDownSelectingAdapter;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;


public class DefinitionPanel extends JPanel implements IUpdate, ISelector, ISelectable{

	public static final Border THICK_BORDER = BorderFactory.createLineBorder(Color.BLACK, 4);
	public static final Border THIN_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1);
	private FormalDefinition myDef;
	private LinkedList<AlphabetPane> myPanes;
	private Container myParent;
	private boolean amActive;
	
	private Font TITLE_FONT = new Font("Dialog", 1, 15);
	private boolean amSelected;
	
	public DefinitionPanel(FormalDefinition def, Container parent, boolean allowSelection){
		myDef = def;
		myParent = parent;
		myPanes = new LinkedList<AlphabetPane>();
		amSelected = false;
		this.setBorder(BorderFactory.createTitledBorder(THIN_BORDER,
				myDef.getName() + " Definition",
				TitledBorder.LEADING,
				TitledBorder.TOP,
				TITLE_FONT));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		for (IAlphabet alph: def){
			this.add(new AlphabetPane(alph));
		}
		if (amActive = allowSelection) this.select(myPanes.getFirst());
		this.setFocusable(true);
		this.addKeyListener(new UpDownSelectingAdapter(this, false, true, myParent));
		this.update();
		
	}

	public FormalDefinition getDefinition() {
		return myDef;
	}
	
	
	@Override
	public Component add(Component comp) {
		if (!(comp instanceof AlphabetPane)) return super.add(comp);
		AlphabetPane pane = (AlphabetPane) comp;
		myPanes.add(pane);
		pane.addMouseListener(new MouseClickAdapter<AlphabetPane>() {

			@Override
			public void rightClickResponse(MouseEvent e, AlphabetPane component) {
				component.getMenu().show(component, e.getX(), e.getY());
			}

			@Override
			public void leftClickResponse(MouseEvent e, AlphabetPane component) {
				DefinitionPanel.this.select(component);
			}

		});
		pane.setAlignmentX(LEFT_ALIGNMENT);
		pane.setAlignmentY(CENTER_ALIGNMENT);
		return super.add(comp);
	}

	@Override
	public void update() {
		
		((TitledBorder) this.getBorder()).setBorder( amSelected ? THICK_BORDER: THIN_BORDER);
		for (AlphabetPane c: myPanes){
			c.update();
		}
		super.revalidate();
		super.repaint();
	}

	@Override
	public void revalidate(){
		if (myParent != null) this.update();
	}
	
	@Override
	public void repaint(){
		this.revalidate();
	}
	
	/* (non-Javadoc)
	 * @see JFLAPnew.formaldef.gui.definitionpanel.ISelector#select(JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane)
	 */
	@Override
	public void select(ISelectable select) {
		if (!isActive() || !this.isEnabled()) return;
		this.clearSelection();
		select.setSelected(true);
		this.update();
	}

	/* (non-Javadoc)
	 * @see JFLAPnew.formaldef.gui.definitionpanel.ISelector#clearSelection()
	 */
	@Override
	public void clearSelection() {
		if (this.getSelected() != null)
			this.getSelected().setSelected(false);
	}

	/* (non-Javadoc)
	 * @see JFLAPnew.formaldef.gui.definitionpanel.ISelector#getSelected()
	 */
	@Override
	public ISelectable getSelected() {
		for (AlphabetPane ap: myPanes){
			if(ap.isSelected())
				return  ap;
		}
		return null;
	}

	public static void updateParentDefPanel(JComponent comp){
		while (!(comp instanceof DefinitionPanel)){
			comp = (JComponent) comp.getParent();
		}
		((DefinitionPanel)comp).update();
	}

	/* (non-Javadoc)
	 * @see JFLAPnew.formaldef.gui.definitionpanel.ISelector#canSelect(boolean)
	 */
	@Override
	public boolean isActive() {
		return amActive;
	}

	@Override
	public void setSelected(boolean b) {
		amSelected = b;
		System.out.println("Hi");
		
	}

	@Override
	public boolean isSelected() {
		return amSelected;
	}

	@Override
	public void selectPrevious() {
		int index = myPanes.indexOf(this.getSelected())-1;
		index = index < 0 ? index + myPanes.size() : index;
		this.select(myPanes.get(index));
	}

	@Override
	public void selectNext() {
		this.select(myPanes.get((myPanes.indexOf(this.getSelected())+1) % myPanes.size()));
		
	}

	@Override
	public void selectAll() {
		for (ISelectable c: myPanes){
			c.setSelected(true);
		}
		this.update();
	}

	
	
}
