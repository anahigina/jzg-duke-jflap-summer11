package JFLAPnew.formaldef.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;

public class DefinitionPanelLayout extends GridBagLayout {


	public void setConstraints(SymbolBar bar){
		this.setConstraints(bar, null);
	}
	
	@Override
	public void setConstraints(Component bar, GridBagConstraints c) {
		super.setConstraints(bar, createConstraints((SymbolBar) bar));
	}


	public void addLayoutComponent(SymbolBar bar){
		this.addLayoutComponent(bar, null);
	}
	
	@Override
	public void addLayoutComponent(Component bar, Object constraints) {
		super.addLayoutComponent(bar, createConstraints((SymbolBar) bar));
	}

	private static GridBagConstraints createConstraints(SymbolBar bar) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 2;
		c.gridx = 0;
		c.gridy = bar.getAssociatedAlphabet().getPriority()*2;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		return c;
	}
	
}

