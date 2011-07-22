package JFLAPnew.formaldef.test;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.specific.InputAlphabet;
import JFLAPnew.formaldef.alphabets.specific.OutputAlphabet;
import JFLAPnew.formaldef.alphabets.specific.StackAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TapeAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TerminalAlphabet;
import JFLAPnew.formaldef.alphabets.specific.VariableAlphabet;
import JFLAPnew.formaldef.grouping.SpecialSymbolFactory;
import JFLAPnew.formaldef.gui.DefinitionPanel;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;


public class testALL {

	private static final int WIDTH = 500;
	
	private static DefinitionPanel buildGrammar(Variable[] vars, Terminal[] terms, boolean grouping, 
			JDesktopPane pane) {
		FormalDefinition def = new FormalDefinition();
		
		VariableAlphabet varAlph = new VariableAlphabet(def);
		TerminalAlphabet termAlph = new TerminalAlphabet(def);
		TapeAlphabet tapeAlph = new TapeAlphabet(def);
		StackAlphabet stackAlph = new StackAlphabet(def);
		OutputAlphabet outAlph = new OutputAlphabet(def);
		InputAlphabet inputAlph = new InputAlphabet(def);
		
		def.addAlphabets(varAlph,
				termAlph, 
				tapeAlph, 
				stackAlph, 
				outAlph, 
				inputAlph);
		
		if (grouping)
			varAlph.setGrouping(SpecialSymbolFactory.getBestGrouping(varAlph.getUniqueCharacters()));
		
		for (Variable v: vars)
			System.out.println(varAlph.add(v));
		for (Terminal t: terms){
			System.out.println(termAlph.add(t.clone()));
//			System.out.println(tapeAlph.add(t.clone()));
//			System.out.println(inputAlph.add(t.clone()));
//			System.out.println(outAlph.add(t.clone()));
//			System.out.println(stackAlph.add(t.clone()));
		}
		
		System.out.println(varAlph.setStartVariable(vars[0]));
		System.out.println(stackAlph.setBottomOfStackSymbol(terms[0]));
		System.out.println(def);
		JInternalFrame frame = new JInternalFrame("Formal Defintion" + (grouping ? " + Grouping" : ""));
		DefinitionPanel defnPanel = new DefinitionPanel(def, new Dimension(800,11));
		frame.add(defnPanel);
		frame.pack();
		frame.setVisible(true);
		pane.add(frame);
		return defnPanel;
	}
	
	/**
	 * @param args
	 */
	public static void createAndShowGUI(Variable[] vars, Terminal[] terms, boolean grouping) {
		JFrame frame = new JFrame();
		JDesktopPane pane = new JDesktopPane();
		DefinitionPanel defPanel = buildGrammar(vars, terms, grouping, pane);
		Toggler tog = new Toggler();
		
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		tog.addUpdatable(defPanel);
		
		pane.add(new SymbolInputter(defPanel));
		pane.add(tog);
		frame.add(pane);
		frame.setPreferredSize(new Dimension(WIDTH, frame.getPreferredSize().height));
		frame.pack();
		frame.setVisible(true);
		
	}

	
	public static void main(String[] args){
		createAndShowGUI(vars1, terms1, false);
		createAndShowGUI(vars2, terms2, true);
	}
	static Variable[] vars1 = new Variable[]{new Variable("A"),
			new Variable("B"),
			new Variable("C"),
			new Variable("D")};
	static Terminal[] terms1 = new Terminal[]{new Terminal("a"),
			new Terminal("b"),
			new Terminal("c"),
			new Terminal("d")};

	static Variable[] vars2 = new Variable[]{new Variable("(ADJECTIVE)"),
			new Variable("(VERB)"),
		  	new Variable("(NOUN)"),
		  	new Variable("(ADVERB)")};
	static Terminal[] terms2 = new Terminal[]{new Terminal("cat"),
			new Terminal("bird"),
			new Terminal("run"),
			new Terminal("fly"),
			new Terminal("blue"),
			new Terminal("furry"),
			new Terminal("fast")};
	
}
