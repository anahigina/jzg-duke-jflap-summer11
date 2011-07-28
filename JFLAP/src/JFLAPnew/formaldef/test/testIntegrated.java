package JFLAPnew.formaldef.test;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.editor.EditorPane;
import gui.environment.AutomatonEnvironment;
import gui.environment.Environment;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.MetaDefinition;
import JFLAPnew.formaldef.gui.definitioncreator.chooser.ModuleChooser;
import JFLAPnew.formaldef.gui.definitioncreator.chooser.ModuleChooserPanel;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import automata.Automaton;

public class testIntegrated {

	private static final int Y = 300,
							 X = 500;
	private static final Dimension DIMENSION = new Dimension(300, 100);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FormalDefinition formalDef = new MetaDefinition(new ModuleChooser(X,Y).getSelection());
		
		DefinitionPanel defPanel = new DefinitionPanel(formalDef, DIMENSION);
		
		Toggler tog = new Toggler();
		tog.addUpdatable(defPanel);
		
		SymbolInputter inputter = new SymbolInputter(defPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(defPanel);
		panel.add(tog);
		panel.add(inputter);
		JFrame f = new JFrame();
		f.setLocation(X, Y);
		f.add(panel);
		f.pack();
		f.setVisible(true);
		
	}

}
