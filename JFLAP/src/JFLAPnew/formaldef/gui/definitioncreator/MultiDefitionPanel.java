package JFLAPnew.formaldef.gui.definitioncreator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.MetaDefinition;
import JFLAPnew.formaldef.gui.definitioncreator.chooser.ModuleChooser;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.MouseClickAdapter;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.selection.ISelectable;
import JFLAPnew.formaldef.gui.selection.ISelector;

public class MultiDefitionPanel extends JPanel implements ISelector{

	
	private MetaDefinition myDefintion;
	private LinkedList<DefinitionPanel> myPanels;
	
	public MultiDefitionPanel(MetaDefinition def, boolean focusable) {
		myDefintion = def;
		myPanels = new LinkedList<DefinitionPanel>();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setFocusable(focusable);
		for (FormalDefinition fd: def){
			this.add(createPanel(fd));
		}
		this.select(myPanels.getFirst());
	
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MultiDefitionPanel.this.requestFocusInWindow();
			}
			
		});
		
	}

	private DefinitionPanel createPanel(FormalDefinition fd) {
		DefinitionPanel frame = new DefinitionPanel(fd, this, this.isFocusable());
		frame.addMouseListener(new MouseClickAdapter<DefinitionPanel>() {

			@Override
			public void leftClickResponse(MouseEvent e,
					DefinitionPanel component) {
				MultiDefitionPanel.this.select(component);
			}
		});
		return frame;
	}

	@Override
	public Component add(Component comp) {
		if (comp instanceof DefinitionPanel) myPanels.add((DefinitionPanel) comp);
		return super.add(comp);
	}

	public MetaDefinition getMetaDefinition(){
		return myDefintion;
	}
	
	@Override
	public void revalidate(){
		int height = 0;
		for (Component c: this.getComponents()){
			if (c instanceof JComponent)
				((JComponent)c).revalidate();
			height += c.getPreferredSize().height;
		}
		this.setPreferredSize(new Dimension(this.getSize().width, height));
		super.revalidate();
	}

	public AlphabetPane getCurrentAlphabetPanel() {
		return (AlphabetPane) ((ISelector) this.getSelected()).getSelected();
	}

	@Override
	public void selectPrevious() {
		int index = myPanels.indexOf(this.getSelected())-1;
		index = index < 0 ? index + myPanels.size() : index;
		this.select(myPanels.get(index));
	}

	@Override
	public void selectNext() {
		this.select(myPanels.get((myPanels.indexOf(this.getSelected())+1) % myPanels.size()));
		
	}

	@Override
	public void select(ISelectable component) {
		DefinitionPanel panel = (DefinitionPanel) component;
		if (component != null && component.isSelectable()){
			this.clearSelection();
			panel.setSelected(true);
			this.revalidate();
		}
	}

	@Override
	public void clearSelection() {
		for(ISelectable panel: myPanels)
			panel.setSelected(false);
	}

	@Override
	public ISelectable getSelected() {
		for (DefinitionPanel c: myPanels){
			if (c.isSelected())
				return c;
		}
		return null;
	}

	@Override
	public void selectAll() {
		for (DefinitionPanel c: myPanels){
			c.setSelected(true);
		}
		this.revalidate();
	}
}
