package JFLAPnew.formaldef;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import regular.RegularExpression;
import automata.fsa.FiniteStateAutomaton;
import automata.mealy.MealyMachine;
import automata.mealy.MooreMachine;
import automata.pda.PushdownAutomaton;
import automata.turing.TuringMachine;
import grammar.Grammar;
import grammar.UnboundGrammar;
import grammar.cfg.ContextFreeGrammar;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.specific.InputAlphabet;
import JFLAPnew.formaldef.alphabets.specific.OutputAlphabet;
import JFLAPnew.formaldef.alphabets.specific.StackAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TapeAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TerminalAlphabet;
import JFLAPnew.formaldef.alphabets.specific.VariableAlphabet;

public class FormalDefintionFactory {

	public static Class<? extends IAlphabet>[] getDefinitionRequirements(
			Class<? extends FormalDefinition> def) {
		
		if (Grammar.class.isAssignableFrom(def))
			return new Class[]{VariableAlphabet.class, TerminalAlphabet.class};
		
		if (FiniteStateAutomaton.class.isAssignableFrom(def))
			return new Class[]{InputAlphabet.class};
		
		if (PushdownAutomaton.class.isAssignableFrom(def))
			return new Class[]{InputAlphabet.class, StackAlphabet.class};
		
		if (TuringMachine.class.isAssignableFrom(def))
			return new Class[]{TapeAlphabet.class, InputAlphabet.class};
		
		if (RegularExpression.class.isAssignableFrom(def))
			return new Class[]{InputAlphabet.class};
		
		if (MealyMachine.class.isAssignableFrom(def))
			return new Class[]{InputAlphabet.class, OutputAlphabet.class};
		
		return null;
	}

	public static String getGenericName(
			Class<? extends FormalDefinition> def) {
		if (Grammar.class.isAssignableFrom(def)) return "Grammar";
		
		if (FiniteStateAutomaton.class.isAssignableFrom(def)) return "Finite State Automaton";
		
		if (PushdownAutomaton.class.isAssignableFrom(def)) return "Pushdown Automaton";
		
		if (TuringMachine.class.isAssignableFrom(def)) return "Turing Machine";
		
		if (RegularExpression.class.isAssignableFrom(def)) return "Regular Expression";
		
		if (MooreMachine.class.isAssignableFrom(def)) return "Moore Machine";
		
		if (MealyMachine.class.isAssignableFrom(def)) return "Mealy Machine";
		
		
		return null;
	}

	public static int getHotkey(Class<? extends FormalDefinition> def) {
		
		if (MooreMachine.class.isAssignableFrom(def)) return KeyEvent.VK_O;
		
		if (MealyMachine.class.isAssignableFrom(def)) return KeyEvent.VK_E;
		
		return KeyStroke.getKeyStroke(def.getSimpleName().charAt(0)).getKeyCode();
	}

	public static Class<? extends FormalDefinition>[] getAllGenericClasses() {
		return new Class[]{UnboundGrammar.class,
						   FiniteStateAutomaton.class,
						   PushdownAutomaton.class,
						   TuringMachine.class,
						   RegularExpression.class,
						   MooreMachine.class,
						   MealyMachine.class};
	}

}
