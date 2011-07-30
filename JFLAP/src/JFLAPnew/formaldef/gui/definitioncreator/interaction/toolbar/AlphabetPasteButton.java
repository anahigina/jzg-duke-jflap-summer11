package JFLAPnew.formaldef.gui.definitioncreator.interaction.toolbar;

import gui.errors.BooleanWrapper;
import gui.errors.JFLAPError;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.gui.definitioncreator.MultiDefitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.symbols.Symbol;

public class AlphabetPasteButton extends DefinitionCreationToolbarButton {

	public AlphabetPane myCopied;
	public JPopupMenu myMenu;
	
	public AlphabetPasteButton(MultiDefitionPanel multiDef) {
		super("Paste", multiDef);
		this.setEnabled(false);
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!((Component) e.getSource()).isEnabled()) return;
				myMenu.show((Component) e.getSource(), e.getX(), e.getY());
			}


			@Override
			public void mouseExited(MouseEvent e) {
				if (!((Component) e.getSource()).isEnabled()) return;
				myMenu.setVisible(false);
			}
			
		});
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		int n = JOptionPane.showConfirmDialog(null, 
				 "Are you sure you want to paste these Symbols\n" +
				 "in the currently selected Alphabet?", 
				 "Warning", 
				 JOptionPane.OK_CANCEL_OPTION,
				 JOptionPane.WARNING_MESSAGE);
		if (n==0){
			AlphabetPasteButton.this.paste();
		}
		super.actionPerformed(e);
	}
	
	protected void paste() {
		String[] clone = myCopied.getAssociatedAlphabet().getSymbolStringArray();
		ArrayList<BooleanWrapper> errors = new ArrayList<BooleanWrapper>();
		for (String s: clone){
			BooleanWrapper bw = myMultiDefPanel.getCurrentAlphabetPanel().getAssociatedAlphabet().add(s);
			if (bw.isFalse())
				errors.add(bw);
		}
		
		if (!errors.isEmpty())
			this.showErrorLog(errors);
	}

	private void showErrorLog(ArrayList<BooleanWrapper> errors) {
		JFLAPError.show("The following symbols could not be pasted:\n" + 
							BooleanWrapper.createErrorLog(errors.toArray(new BooleanWrapper[0])), 
				"Pasting Errors");
		
	}

	public void setCopied(AlphabetPane a) {
		myCopied = a;
		if (a != null) {
			this.setEnabled(true);
			myMenu = new JPopupMenu();
			myMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			myMenu.add(myCopied);
		}
	}
	

}
