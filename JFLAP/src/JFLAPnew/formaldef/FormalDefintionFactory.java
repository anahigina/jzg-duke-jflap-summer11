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
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.specific.InputAlphabet;
import JFLAPnew.formaldef.alphabets.specific.OutputAlphabet;
import JFLAPnew.formaldef.alphabets.specific.StackAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TapeAlphabet;
import JFLAPnew.formaldef.alphabets.specific.TerminalAlphabet;
import JFLAPnew.formaldef.alphabets.specific.VariableAlphabet;

public class FormalDefintionFactory {

	public static Class<? extends IAlphabet>[] getDefinitionRequirements(
			Class<? extends FormalDefinition> class1) {
		
		if (class1.isAssignableFrom(Grammar.class))
			return new Class[]{VariableAlphabet.class, TerminalAlphabet.class};
		
		if (class1.isAssignableFrom(FiniteStateAutomaton.class))
			return new Class[]{InputAlphabet.class};
		
		if (class1.isAssignableFrom(PushdownAutomaton.class))
			return new Class[]{InputAlphabet.class, StackAlphabet.class};
		
		if (class1.isAssignableFrom(TuringMachine.class))
			return new Class[]{TapeAlphabet.class, InputAlphabet.class};
		
		if (class1.isAssignableFrom(RegularExpression.class))
			return new Class[]{InputAlphabet.class};
		
		if (class1.isAssignableFrom(MooreMachine.class))
			return new Class[]{InputAlphabet.class, OutputAlphabet.class};
		
		return null;
	}

	public static String getGenericName(
			Class<? extends FormallyDefinedObject> def) {
		if (def.isAssignableFrom(Grammar.class)) return "Grammar";
		
		if (def.isAssignableFrom(FiniteStateAutomaton.class)) return "Finite State Automaton";
		
		if (def.isAssignableFrom(PushdownAutomaton.class)) return "Pushdown Automaton";
		
		if (def.isAssignableFrom(TuringMachine.class)) return "Turing Machine";
		
		if (def.isAssignableFrom(RegularExpression.class)) return "Regular Expression";
		
		if (def.isAssignableFrom(MealyMachine.class)) return "Mealy Machine";
		
		if (def.isAssignableFrom(MooreMachine.class)) return "Moore Machine";
		
		return null;
	}

	public static int getHotkey(Class<? extends FormallyDefinedObject> def) {
		
		if (def.isAssignableFrom(MealyMachine.class)) return KeyEvent.VK_E;
		
		if (def.isAssignableFrom(MooreMachine.class)) return KeyEvent.VK_O;
		
		return KeyStroke.getKeyStroke(def.getSimpleName().charAt(0)).getKeyCode();
	}

	public static Class<? extends FormallyDefinedObject>[] getAllGenericClasses() {
		return new Class[]{Grammar.class,
						   FiniteStateAutomaton.class,
						   PushdownAutomaton.class,
						   TuringMachine.class,
						   RegularExpression.class,
						   MooreMachine.class,
						   MealyMachine.class};
	}

}
