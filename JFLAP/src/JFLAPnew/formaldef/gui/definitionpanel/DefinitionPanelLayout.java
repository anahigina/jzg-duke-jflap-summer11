package JFLAPnew.formaldef.gui.definitionpanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;

public class DefinitionPanelLayout extends GridBagLayout {


	public void setConstraints(AlphabetPane bar){
        this.setConstraints(bar, null);
	}
	
	
	public void addLayoutComponent(AlphabetPane bar){
	        this.addLayoutComponent(bar, null);
	}
	
	@Override
	public void addLayoutComponent(Component bar, Object constraints) {
		if (bar instanceof AlphabetPane)
	        super.addLayoutComponent(bar, createConstraints((AlphabetPane) bar));
		else
			super.addLayoutComponent(bar, constraints);
	}
	
	private static GridBagConstraints createConstraints(AlphabetPane bar) {
	        GridBagConstraints c = new GridBagConstraints();
	        c.insets = new Insets(0,0,0,0);
	        c.gridx = 0;
	        c.gridy = bar.getAssociatedAlphabet().getPriority();
	        c.weightx = 1;
	        c.weighty = 1;
	        c.fill = GridBagConstraints.BOTH;
	        c.anchor = GridBagConstraints.NORTHWEST;
	        return c;
	}
	
}

