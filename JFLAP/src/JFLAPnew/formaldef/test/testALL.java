package JFLAPnew.formaldef.test;

import grammar.Grammar;
import grammar.UnrestrictedGrammar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import automata.Automaton;
import automata.fsa.FiniteStateAutomaton;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.MetaDefinition;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.specific.InputAlphabet;
import JFLAPnew.formaldef.alphabets.specific.OutputAlphabet;
import JFLAPnew.formaldef.alphabets.specific.StackAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TapeAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TerminalAlphabet;
import JFLAPnew.formaldef.alphabets.specific.VariableAlphabet;
import JFLAPnew.formaldef.grouping.SpecialSymbolFactory;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBox;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;


public class testALL {

//	private static final int WIDTH = 500;
//	
//	private static DefinitionPanel buildGrammar(Variable[] vars, Terminal[] terms, boolean grouping, 
//			JDesktopPane pane) {
//		Grammar def = new UnrestrictedGrammar();
//		
////		VariableAlphabet varAlph = new VariableAlphabet(def);
////		TerminalAlphabet termAlph = new TerminalAlphabet(def);
////		TapeAlphabet tapeAlph = new TapeAlphabet(def);
////		StackAlphabet stackAlph = new StackAlphabet(def);
////		OutputAlphabet outAlph = new OutputAlphabet(def);
////		InputAlphabet inputAlph = new InputAlphabet(def);
////		
////		def.addAlphabets(varAlph,
////				termAlph, 
////				tapeAlph, 
////				stackAlph, 
////				outAlph, 
////				inputAlph);
//		
//		if (grouping)
//			def.getVariables().setGrouping(SpecialSymbolFactory.getBestGrouping(def.getVariables().getUniqueCharacters()));
//		
////		for (Variable v: vars)
////			System.out.println(varAlph.add(v));
////		for (Terminal t: terms){
////			System.out.println(termAlph.add(t.clone()));
//////			System.out.println(tapeAlph.add(t.clone()));
//////			System.out.println(inputAlph.add(t.clone()));
//////			System.out.println(outAlph.add(t.clone()));
//////			System.out.println(stackAlph.add(t.clone()));
////		}
//		
////		System.out.println(varAlph.setStartVariable(vars[0]));
////		System.out.println(stackAlph.setBottomOfStackSymbol(terms[0]));
//		System.out.println(def);
//		JInternalFrame frame = new JInternalFrame("Formal Defintion" + (grouping ? " + Grouping" : ""));
//		DefinitionPanel defnPanel = new DefinitionPanel(def, new Dimension(500,11));
//		frame.add(defnPanel);
//		frame.pack();
//		frame.setVisible(true);
//		pane.add(frame);
//		return defnPanel;
//	}
//	
//	/**
//	 * @param args
//	 */
//	public static void createAndShowGUI(Variable[] vars, Terminal[] terms, boolean grouping) {
//		JFrame frame = new JFrame();
//		JDesktopPane pane = new JDesktopPane();
//		DefinitionPanel defPanel = buildGrammar(vars, terms, grouping, pane);
//		Toggler tog = new Toggler();
//		
//		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
//		
//		tog.addUpdatable(defPanel);
//		
//		pane.add(new SymbolInputter(defPanel));
//		pane.add(tog);
//		frame.add(pane);
////		frame.setPreferredSize(new Dimension(WIDTH, frame.getPreferredSize().height));
//		
//		
//	}

	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		for (FormalDefinition fd: new MetaDefinition().getDefinitions()){
			JInternalFrame internal = new JInternalFrame(fd.getName());
			internal.setSize(new Dimension(600, 10));
			internal.add(new DefinitionPanel(fd, internal));
//			internal.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			internal.pack();
			internal.setVisible(true);
			panel.add(internal);
		}
	
		frame.add(panel);
		frame.setLocation(300, 300);
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
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
