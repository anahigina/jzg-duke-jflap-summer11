package JFLAPnew.formaldef.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Window;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.symbolbar.IUpdate;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;


public class DefinitionPanel extends JPanel implements IUpdate{

	private FormalDefinition myDef;
	
	public DefinitionPanel(FormalDefinition def, Dimension d){
		super();
		myDef = def;
		DefinitionPanelLayout layout = new DefinitionPanelLayout();
		this.setLayout(layout);
		for (IAlphabet alph: def){
			SymbolBar sb = new SymbolBar(alph);
			this.add(sb);
			layout.addLayoutComponent(sb);
		}
		this.setSize(d);
		
	}

	public FormalDefinition getDefinition() {
		return myDef;
	}

	@Override
	public void update() {
		for (Component c: this.getComponents()){
			((SymbolBar) c).update();
		}
		Container cont = this.getRootPane().getParent();
		if (cont instanceof Window)
			((Window) cont).pack();
	}

	public void setSelectedBar(SymbolBar component) {
		this.clearSelection();
		component.setSelected(true);
		this.update();
	}

	private void clearSelection() {
		if (this.getSelected() != null)
			this.getSelected().setSelected(false);
	}

	public SymbolBar getSelected() {
		for (Component c: this.getComponents()){
			if(((SymbolBar) c).isSelected())
				return (SymbolBar) c;
		}
		return null;
	}



}
