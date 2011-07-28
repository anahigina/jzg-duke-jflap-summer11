package JFLAPnew.formaldef.gui.definitionpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.omg.CORBA.portable.BoxedValueHelper;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.IUpdate;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;


public class DefinitionPanel extends JPanel implements IUpdate{

	private FormalDefinition myDef;
	private LinkedList<AlphabetPane> myPanes;
	private Container myParent;
	
	public DefinitionPanel(FormalDefinition def, Container parent){
		myDef = def;
		myParent = parent;
		myPanes = new LinkedList<AlphabetPane>();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		for (IAlphabet alph: def){
			this.add(new AlphabetPane(alph));
		}
		this.add(Box.createVerticalGlue());
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
				DefinitionPanel.this.setSelectedBar(component);
			}

		});
		pane.setAlignmentX(LEFT_ALIGNMENT);
		pane.setAlignmentY(CENTER_ALIGNMENT);
		return super.add(comp);
	}

	@Override
	public void update() {
		int width = (myParent != null) ? myParent.getWidth() : AlphabetPane.HEIGHT*20;
		this.setPreferredSize(new Dimension(width, myDef.size()*AlphabetPane.HEIGHT));
		for (AlphabetPane c: myPanes){
			c.update();
		}
		super.repaint();
		super.updateUI();
	}

	public void setSelectedBar(AlphabetPane component) {
		this.clearSelection();
		component.setSelected(true);
		this.update();
	}

	private void clearSelection() {
		if (this.getSelected() != null)
			this.getSelected().setSelected(false);
	}

	public AlphabetPane getSelected() {
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

}
