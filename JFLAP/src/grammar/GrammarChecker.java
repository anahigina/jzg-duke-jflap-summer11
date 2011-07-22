/*
 *  JFLAP - Formal Languages and Automata Package
 * 
 * 
 *  Susan H. Rodger
 *  Computer Science Department
 *  Duke University
 *  August 27, 2009

 *  Copyright (c) 2002-2009
 *  All rights reserved.

 *  JFLAP is open source software. Please see the LICENSE for terms.
 *
 */





package grammar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import JFLAPnew.formaldef.symbols.SymbolHelper;
import JFLAPnew.formaldef.symbols.SymbolString;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;

/**
 * The Grammar checker object can be used to check certain properties of grammar
 * objects.
 * 
 * @author Ryan Cavalcante
 */

public class GrammarChecker {
	/**
	 * Creates an instance of <CODE>GrammarChecker</CODE>.
	 */
	public GrammarChecker() {

	}

	/**
	 * Returns true if <CODE>grammar</CODE> is a regular grammar (i.e. if it
	 * is either a right or left linear grammar).
	 * 
	 * @param grammar
	 *            the grammar.
	 * @return true if <CODE>grammar</CODE> is a regular grammar.
	 */
	public static boolean isRegularGrammar(Grammar grammar) {
		if (isRightLinearGrammar(grammar) || isLeftLinearGrammar(grammar))
			return true;
		return false;
	}

	/**
	 * Returns true if <CODE>grammar</CODE> is a right-linear grammar (i.e.
	 * all productions are of the form A->xB or A->x).
	 * 
	 * @param grammar
	 *            the grammar.
	 * @return true if <CODE>grammar</CODE> is a right-linear grammar.
	 */
	public static boolean isRightLinearGrammar(Grammar grammar) {
		ProductionChecker pc = new ProductionChecker();
		Production[] productions = grammar.getProductions();
		for (int k = 0; k < productions.length; k++) {
			if (!ProductionChecker.isRightLinear(productions[k]))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if <CODE>grammar</CODE> is a left-linear grammar (i.e. all
	 * productions are of the form A->Bx or A->x).
	 * 
	 * @param grammar
	 *            the grammar.
	 * @return true if <CODE>grammar</CODE> is a left-linear grammar.
	 */
	public static boolean isLeftLinearGrammar(Grammar grammar) {
		ProductionChecker pc = new ProductionChecker();
		Production[] productions = grammar.getProductions();
		for (int k = 0; k < productions.length; k++) {
			if (!ProductionChecker.isLeftLinear(productions[k]))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if <CODE>grammar</CODE> is a context-free grammar (i.e.
	 * all productions are of the form A->x).
	 * 
	 * @param grammar
	 *            the grammar.
	 * @return true if <CODE>grammar</CODE> is a context-free grammar.
	 */
	public static boolean isContextFreeGrammar(Grammar grammar) {
		ProductionChecker pc = new ProductionChecker();
		Production[] productions = grammar.getProductions();
		for (int k = 0; k < productions.length; k++) {
			if (!ProductionChecker.isRestrictedOnLHS(productions[k]))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if <CODE>variable</CODE> is in any production, either on
	 * the right or left hand side of the production, of <CODE>grammar</CODE>.
	 * 
	 * @param variable
	 *            the variable.
	 * @return true if <CODE>variable</CODE> is in any production of <CODE>grammar</CODE>.
	 */
	public static boolean isVariableInProductions(Grammar grammar,
			Variable variable) {
		for (Production p: grammar.getProductions()) {
			if (p.containsSymbol(variable)) 
				return true;
		}
		return false;
	}

	/**
	 * Returns true if <CODE>terminal</CODE> is in any production, either on
	 * the right or left hand side of the production, of <CODE>grammar</CODE>.
	 * 
	 * @param terminal
	 *            the terminal.
	 * @return true if <CODE>terminal</CODE> is in any production in <CODE>grammar</CODE>.
	 */
	public static boolean isTerminalInProductions(Grammar grammar,
			Terminal terminal) {
		for (Production p: grammar.getProductions()) {
			if (p.containsSymbol(terminal)) 
				return true;
		}
		return false;
	}

	/**
	 * Returns all productions in <CODE>grammar</CODE> whose lhs is <CODE>variable</CODE>.
	 * 
	 * @param variable
	 *            the variable
	 * @param grammar
	 *            the grammar
	 * @return all productions in <CODE>grammar</CODE> whose lhs is <CODE>variable</CODE>.
	 */
	public static Production[] getProductionsOnVariable(Variable variable,
			Grammar grammar) {
		ArrayList<Production> list = new ArrayList<Production>();
		for (Production p: grammar.getProductions()){
			if(p.getLHS().size() == 1 && p.getLHS().getFirst().equals(variable))
				list.add(p);
		}
		return (Production[]) list.toArray(new Production[0]);
	}

	/**
	 * Returns all productions in <CODE>grammar</CODE> that have <CODE>variable</CODE>
	 * as the only character on the left hand side and that are not unit
	 * productions.
	 * 
	 * @param variable
	 *            the variable
	 * @param grammar
	 *            the grammar
	 * @return all productions in <CODE>grammar</CODE> that have <CODE>variable</CODE>
	 *         as the only character on the left hand side and are not unit
	 *         productions.
	 */
	public static Production[] getNonUnitProductionsOnVariable(String variable,
			Grammar grammar) {
		ArrayList list = new ArrayList();
		ProductionChecker pc = new ProductionChecker();
		Production[] productions = grammar.getProductions();
		for (int k = 0; k < productions.length; k++) {
			if (variable.equals(productions[k].getLHS())
					&& !ProductionChecker.isUnitProduction(productions[k])) {
				list.add(productions[k]);
			}
		}
		return (Production[]) list.toArray(new Production[0]);
	}


	/**
	 * Returns a list of those variables which are unresolved, i.e., which
	 * appears in the right hand side but do not appear in the left hand side.
	 * 
	 * @param grammar
	 *            the grammar to check
	 * @return an array of the unresolved variables
	 */
	public static Set<Variable> getUnresolvedVariables(Grammar grammar) {
		Set<Variable> left = SymbolHelper.getAllSymbolSetOfClass(Variable.class, 
																		grammar.getLHSes()),
					 right = SymbolHelper.getAllSymbolSetOfClass(Variable.class, 
							 											grammar.getLHSes());
		right.removeAll(left);
		return right;
	}

}
