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

import java.util.Set;

import JFLAPnew.formaldef.symbols.SymbolString;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;

/**
 * The Production checker object can be used to check certain properties of
 * production objects.
 * 
 * @author Ryan Cavalcante
 */

public class ProductionChecker {

	/**
	 * Returns true if <CODE>production</CODE> is linear (i.e. either right or
	 * left linear).
	 * 
	 * @param production
	 *            the production
	 * @return true if <CODE>production</CODE> is linear.
	 */
	public static boolean isLinear(Production production) {
		if (isRightLinear(production) || isLeftLinear(production)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if <CODE>production</CODE> is right linear.
	 * 
	 * @param production
	 *            the production.
	 * @return true if <CODE>production</CODE> is right linear.
	 */
	public static boolean isRightLinear(Production production) {
		if (isRightLinearProductionWithVariable(production)
				|| isLinearProductionWithNoVariable(production))
			return true;
		return false;
	}

	/**
	 * Returns true if <CODE>production</CODE> is left linear.
	 * 
	 * @param production
	 *            the production.
	 * @return true if <CODE>production</CODE> is left linear.
	 */
	public static boolean isLeftLinear(Production production) {
		if (isLeftLinearProductionWithVariable(production)
				|| isLinearProductionWithNoVariable(production))
			return true;
		return false;
	}

	/**
	 * Returns true if <CODE>production</CODE> is a production of the form
	 * A->Bx where x is a series of 0 or more terminals
	 * 
	 * @param production
	 *            the production
	 * @return true if <CODE>production</CODE> is a production of the form
	 *         A->Bx where x is a series of 0 or more terminals
	 */
	public static boolean isLeftLinearProductionWithVariable(
			Production production) {
		return isRestrictedOnLHS(production) &&
				production.getVariablesOnRHS().size() == 1 && 
				production.getRHS().getFirst() instanceof Variable;
	}

	/**
	 * Returns true if <CODE>production</CODE> is a production of the form
	 * A->xB where x is a series of 0 or more terminals
	 * 
	 * @param production
	 *            the production
	 * @return true if <CODE>production</CODE> is a production of the form
	 *         A->xB where x is a series of 0 or more terminals
	 */
	public static boolean isRightLinearProductionWithVariable(
			Production production) {
		return isRestrictedOnLHS(production) &&
				production.getVariablesOnRHS().size() == 1 && 
				production.getRHS().getLast() instanceof Variable;
	}

	/**
	 * Returns true if <CODE>production</CODE> is a production of the form
	 * A->x where x is a series of 0 or more terminals.
	 * 
	 * @param production
	 *            the production
	 * @return true if <CODE>production</CODE> is a production of the form
	 *         A->x where x is a series of 0 or more terminals.
	 */
	public static boolean isLinearProductionWithNoVariable(Production production) {
		
		return isRestrictedOnLHS(production) && 
				production.getVariablesOnRHS().isEmpty(); //no variables ~ all terminals
	}

	/**
	 * Returns true if <CODE>production</CODE> is a unit production.
	 * 
	 * @param production
	 *            the production.
	 * @return true if <CODE>production</CODE> is a unit production.
	 */
	public static boolean isUnitProduction(Production production) {
		return isRestrictedOnLHS(production) &&
				production.getLHS().size() == 1 &&
				production.getVariablesOnLHS().size() == 1;
	}

	/**
	 * Returns true if <CODE>production</CODE> is a lambda production.
	 * 
	 * @param production
	 *            the production.
	 * @return true if <CODE>production</CODE> is a lambda production.
	 */
	public static boolean isLambdaProduction(Production production) {
		return isRestrictedOnLHS(production) && production.getRHS().isEmpty();
	}

	/**
	 * Returns true if the left hand side of <CODE>production</CODE> is a
	 * single variable.
	 * 
	 * @param production
	 *            the production.
	 * @return true if the left hand side of <CODE>production</CODE> is a
	 *         single variable.
	 */
	public static boolean isRestrictedOnLHS(Production production) {
		return production.getLHS().size() == 1 && 
			   production.getVariablesOnLHS().size() == 1;
	}

	/**
	 * Returns true if <CODE>variable</CODE> is in the production, either on
	 * the right or left hand side of the production.
	 * 
	 * @param variable
	 *            the variable.
	 * @param production
	 *            the production.
	 * @return true if <CODE>variable</CODE> is in the production.
	 * 
	 * @deprecated Use <CODE> production.containsSymbol(variable) </CODE> instead
	 */
	@Deprecated
	public static boolean isVariableInProduction(Variable variable,
			Production production) {
		return production.containsSymbol(variable);
	}

	/**
	 * Returns true if <CODE>terminal</CODE> is in the production, either on
	 * the right or left hand side of the production.
	 * 
	 * @param terminal
	 *            the terminal.
	 * @param production
	 *            the production.
	 * @return true if <CODE>terminal</CODE> is in the production.
	 * 
	 * @deprecated Use <CODE> production.containsSymbol(variable) </CODE> instead
	 */
	@Deprecated
	public static boolean isTerminalInProduction(Terminal terminal,
			Production production) {
		return production.containsSymbol(terminal);
	}

	/**
	 * Returns true if there are 1 or more terminals on the rhs of <CODE>productions</CODE>.
	 * 
	 * @param production
	 *            the production
	 * @return true if there are 1 or more terminals on the rhs of <CODE>productions</CODE>.
	 */
	public static boolean areTerminalsOnRHS(Production production) {
		return !production.getTerminalsOnRHS().isEmpty();
	}

	
	/**
	 * Returns true if <CODE>variable</CODE> is on the right hand side of
	 * <CODE>production</CODE>.
	 * 
	 * @param production
	 *            the production
	 * @param variable
	 *            the variable
	 * @return true if <CODE>variable</CODE> is on the right hand side of
	 *         <CODE>production</CODE>.
	 */
	public static boolean isVariableOnRHS(Production production, Variable variable) {
		return production.getVariablesOnRHS().contains(variable);
	}
	
	
}
