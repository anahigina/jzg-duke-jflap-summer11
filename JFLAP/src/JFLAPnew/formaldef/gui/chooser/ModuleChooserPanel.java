package JFLAPnew.formaldef.gui.chooser;

import grammar.Grammar;
import gui.errors.JFLAPError;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import JFLAPnew.formaldef.FormalDefintionFactory;
import JFLAPnew.formaldef.FormallyDefinedObject;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TerminalAlphabet;
import JFLAPnew.formaldef.alphabets.specific.VariableAlphabet;
import JFLAPnew.formaldef.gui.mouseadapter.MouseClickAdapter;

public abstract class ModuleChooserPanel extends JPanel implements ActionListener{

	
	private ArrayList<ChooserOption> myChoices;
	
	public ModuleChooserPanel(){
		myChoices = new ArrayList<ChooserOption>();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Select Modules"));
		for (Class<? extends FormallyDefinedObject> clazz: FormalDefintionFactory.getAllGenericClasses()){
			ChooserOption op = this.createChooserOption(clazz);
			myChoices.add(op);
			this.add(op);
		}
		JButton button = new JButton("Continue");
		button.addActionListener(this);
		this.add(button);
		
	}

	private ChooserOption createChooserOption(Class<? extends FormallyDefinedObject> def) {
		return new ChooserOption(FormalDefintionFactory.getGenericName(def),
								 FormalDefintionFactory.getHotkey(def),
								 false,
								 FormalDefintionFactory.getDefinitionRequirements(def));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		HashSet<Class<? extends IAlphabet>> selection = new HashSet<Class<? extends IAlphabet>>();
		for (ChooserOption op: myChoices){
			if (op.isSelected())
				selection.addAll(op.getAlphabetClasses());
		}
		if (selection.isEmpty()){
			JFLAPError.show("You must select at least one Module", "Error");
			return;
		}
		else{
			this.onContinueAction(selection);
		}
	}

	public abstract void onContinueAction(HashSet<Class<? extends IAlphabet>> selection);
	
}
