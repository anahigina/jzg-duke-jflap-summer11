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

import grammar.cfg.ContextFreeGrammar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;
import automata.State;
import automata.Transition;
import automata.UnreachableStatesDetector;
import automata.vdg.VDGTransition;
import automata.vdg.VariableDependencyGraph;

/**
 * As it stands now, the code in here is almost completely useless. Through
 * conjunction with <CODE>gui.grammar.transform.UselessController</CODE> it
 * manages to do the correct thing (I hope), but in the interest of correctness
 * this code should be reformed; I have too much to do right now to figure out
 * where the hell he was going with some of this garbage... TWF
 */

public class UselessProductionRemover {

	/**
	 * Returns set of all useful variables in <CODE>grammar</CODE>. A grammar
	 * is considered useful if it can derive a string.
	 * 
	 * @param grammar
	 *            the grammar
	 * @return set of all useful variables in <CODE>grammar</CODE>. A grammar
	 *         is considered useful if it can derive a string.
	 */
	public static Set<Variable> getCompleteUsefulVariableSet(Grammar grammar) {
		Set<Variable> set = new HashSet<Variable>();
		while (areMoreVariablesThatBelongInUsefulVariableSet(grammar, set)) {
			Variable variable = getVariableThatBelongsInUsefulVariableSet(
					grammar, set);
			set.add(variable);
		}
		return set;
	}



	/**
	 * Returns the set of variables that are the predicate of rules that are
	 * only terminal strings.
	 */
	public static Set<SymbolString> getTerminalProductions(Grammar grammar) {
		Set<SymbolString> terminalDerivers = new TreeSet<SymbolString>();
		Production[] p = grammar.getProductions();
		for (int i = 0; i < p.length; i++) {
			SymbolString lhs = p[i].getLHS();
			if (terminalDerivers.contains(lhs))
				continue;
			SymbolString rhs = p[i].getRHS();
			for (Symbol s : rhs) {
				if (s instanceof Variable) {
					lhs = null;
					break;
				}
			}
			if (lhs == null)
				continue;
			terminalDerivers.add(lhs);
		}
		return terminalDerivers;
	}

	/**
	 * Get a grammar with only those variables that derive terminals, directly
	 * or indirectly. This is not the same as a useless production-less grammar.
	 * 
	 * @param grammar
	 *            the grammar to get the reformed grammar for
	 */
	public static Grammar getTerminalGrammar(Grammar grammar) {
		Grammar g = new ContextFreeGrammar();
		Set<Variable> terminalVars = getCompleteUsefulVariableSet(grammar);
		Production[] prods = grammar.getProductions();
		for (int i = 0; i < prods.length; i++) {
			Set<Variable> v = new HashSet<Variable>(prods[i].getVariables());
			v.removeAll(terminalVars);
			if (v.size() > 0)
				continue;
			// Production has no variables that aren't terminal derivers!
			g.addProduction(prods[i]);
		}
		g.setStartVariable(grammar.getStartVariable());
		return g;
	}

	/**
	 * Returns a variable that belongs in the set of useful variables for <CODE>grammar</CODE>
	 * that is not already in <CODE>set</CODE>.
	 * 
	 * @param grammar
	 *            the grammar
	 * @param set
	 *            the set of useful variables in <CODE>grammar</CODE>.
	 * @return a variable that belongs in the set of useful variables for <CODE>grammar</CODE>
	 *         that is not already in <CODE>set</CODE>.
	 */
	public static Variable getVariableThatBelongsInUsefulVariableSet(
			Grammar grammar, Set set) {
		Set<Variable> variables = grammar.getVariableAlphabet().getSymbols();
		for (Variable v: variables) {
			if (belongsInUsefulVariableSet(v, grammar, set)
					&& !set.contains(v))
				return v;
		}
		return null;
	}


	/**
	 * Returns true if <CODE>production</CODE> can derive a string. (i.e. if
	 * all letters on the right hand side of the production are either terminals
	 * or useful variables (variables in <CODE>set</CODE>).
	 * 
	 * @param production
	 *            the production
	 * @param set
	 *            the set of useful variables
	 * @return true if <CODE>production</CODE> can derive a string. (i.e. if
	 *         all letters on the right hand side of the production are either
	 *         terminals or useful variables (variables in <CODE>set</CODE>).
	 */
	private static boolean isUsefulProduction(Production production, Set<Variable> set) {
		for (Symbol s: production.getRHS()) {
			if (s instanceof Variable
					&& !set.contains(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns true if <CODE>production</CODE> contains only terminals and
	 * variables in <CODE>set</CODE>, the set of useful variables. This
	 * includes both the left and right hand side of the production.
	 * 
	 * @param production
	 *            the production
	 * @param set
	 *            the set of useful variables
	 * @return true if <CODE>production</CODE> contains only terminals and
	 *         variables in <CODE>set</CODE>, the set of useful variables.
	 *         This includes both the left and right hand side of the
	 *         production.
	 */
	public static boolean isValidProduction(Production production, Set<Variable> set) {
		for (Symbol s: production.getLHS()) {
			if (!set.contains(s))
				return false;
		}
		return isUsefulProduction(production, set);
	}

	/**
	 * Returns true if <CODE>variable</CODE> belongs in the set of useful
	 * variables, even if it is already in <CODE>set</CODE>. This function
	 * examines all productions in <CODE>grammar</CODE> with variable on the
	 * left hand side, and determines if any of those productions are useful.
	 * 
	 * @param v
	 *            the variable
	 * @param grammar
	 *            the grammar
	 * @param set
	 *            the set of useful variables
	 * @return true if <CODE>variable</CODE> belongs in the set of useful
	 *         variables, even if it is already in <CODE>set</CODE>.
	 */
	public static boolean belongsInUsefulVariableSet(Variable v,
			Grammar grammar, Set<Variable> set) {
		Production[] productions = GrammarChecker.getProductionsOnVariable(
				v, grammar);
		for (int k = 0; k < productions.length; k++) {
			if (isUsefulProduction(productions[k], set))
				return true;
		}
		return false;
	}

	/**
	 * Returns true if there are more variables (i.e. other than the ones
	 * already in the set) that belong in the set of useful variables <CODE>set</CODE>.
	 * 
	 * @param grammar
	 *            the grammar
	 * @param set
	 *            the set of useful variables.
	 * @return true if there are more variables (i.e. other than the ones
	 *         already in the set) that belong in the set of useful variables
	 *         <CODE>set</CODE>.
	 */
	public static boolean areMoreVariablesThatBelongInUsefulVariableSet(
			Grammar grammar, Set set) {
		if (getVariableThatBelongsInUsefulVariableSet(grammar, set) == null)
			return false;
		return true;
	}

	/**
	 * Returns the set of all useful productions (i.e. productions that can
	 * derive strings) in <CODE>grammar</CODE> based on the useful variables,
	 * contained in <CODE>usefulVariableSet</CODE>.
	 * 
	 * @param grammar
	 *            the grammar
	 * @param usefulVariableSet
	 *            the set of useful variables
	 * @return the set of all useful productions (i.e. productions that can
	 *         derive strings) in <CODE>grammar</CODE> based on the useful
	 *         variables, contained in <CODE>usefulVariableSet</CODE>.
	 */
	public static Set getCompleteProductionWithUsefulVariableSet(
			Grammar grammar, Set usefulVariableSet) {
		Set set = getNewProductionWithUsefulVariableSet();
		Production[] productions = grammar.getProductions();
		for (int k = 0; k < productions.length; k++) {
			if (belongsInProductionWithUsefulVariableSet(productions[k],
					usefulVariableSet)) {
				set.add(productions[k]);
			}
		}
		return set;
	}

	/**
	 * Returns an empty set.
	 * 
	 * @return an empty set.
	 */
	public static Set getNewProductionWithUsefulVariableSet() {
		return new HashSet();
	}

	/**
	 * Returns true if <CODE>production</CODE> belongs in set of useful
	 * productions (i.e. if <CODE>production</CODE> contains only terminals
	 * and variables in <CODE>usefulVariableSet</CODE>.
	 * 
	 * @param production
	 *            the production
	 * @param usefulVariableSet
	 *            the set of useful variables
	 * @return true if <CODE>production</CODE> belongs in set of useful
	 *         productions (i.e. if <CODE>production</CODE> contains only
	 *         terminals and variables in <CODE>usefulVariableSet</CODE>.
	 */
	public static boolean belongsInProductionWithUsefulVariableSet(
			Production production, Set usefulVariableSet) {
		if (isValidProduction(production, usefulVariableSet)) {
			return true;
		}
		return false;
	}

	/**
	 * Adds <CODE>production</CODE> to <CODE>set</CODE>.
	 */
	public static void addToProductionWithUsefulVariableSet(
			Production production, Set set) {
		set.add(production);
	}

	/**
	 * Adds a state for every variable in <CODE>grammar</CODE> to <CODE>graph</CODE>,
	 * and sets the state that represents the start variable ("S") to the
	 * initial state.
	 * 
	 * @param graph
	 *            the variable dependency graph
	 * @param grammar
	 *            the grammar.
	 */
	public static void initializeVariableDependencyGraph(
			VariableDependencyGraph graph, Grammar grammar) {
		String[] variables = (String[]) getCompleteUsefulVariableSet(grammar)
				.toArray(new String[0]);
		for (int k = 0; k < variables.length; k++) {
			double theta = 2.0 * Math.PI * (double) k
					/ (double) variables.length;
			Point point = new Point(200 + (int) (180.0 * Math.cos(theta)),
					200 + (int) (180.0 * Math.sin(theta)));
			State state = graph.createState(point);
			state.setName(variables[k]);
			if (variables[k].equals(grammar.getStartVariable()))
				graph.setInitialState(state);
		}
	}

	/**
	 * Returns true if <CODE>v1</CODE> is dependent on <CODE>v2</CODE>.
	 * (i.e. if <CODE>v2</CODE> is on the right hand side of any production in
	 * <CODE>grammar</CODE> that has <CODE>v1</CODE> on the left hand side).
	 * 
	 * @param v1
	 *            the variable on the left hand side
	 * @param v2
	 *            the variable on the right hand side
	 * @param grammar
	 *            the grammar
	 * @return true if <CODE>v1</CODE> is dependent on <CODE>v2</CODE>.
	 *         (i.e. if <CODE>v2</CODE> is on the right hand side of any
	 *         production in <CODE>grammar</CODE> that has <CODE>v1</CODE>
	 *         on the left hand side).
	 */
	public static boolean isDependentOn(Variable v1, Variable v2, Grammar grammar) {
		for (Production p : 
					GrammarChecker.getProductionsOnVariable(v1, grammar)) {
			if (p.containsSymbol(v2)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a transition between the states that represent <CODE>v1</CODE>
	 * and <CODE>v2</CODE> in <CODE>graph</CODE>.
	 * 
	 * @param v1
	 *            a variable
	 * @param v2
	 *            a variable
	 * @param graph
	 *            the variable dependency graph
	 * @return a transition between the states that represent <CODE>v1</CODE>
	 *         and <CODE>v2</CODE> in <CODE>graph</CODE>.
	 */
	public static Transition getTransition(Variable v1, Variable v2,
			VariableDependencyGraph graph) {
		State from = getStateForVariable(v1, graph);
		State to = getStateForVariable(v2, graph);
		return new VDGTransition(from, to);
	}

	/**
	 * Returns the state in <CODE>graph</CODE> that represents <CODE>variable</CODE>
	 * (i.e the state whose name is <CODE>variable</CODE>).
	 * 
	 * @param v1
	 *            the variable
	 * @param graph
	 *            the variable dependency graph.
	 * @return the state in <CODE>graph</CODE> that represents <CODE>variable</CODE>
	 *         (i.e the state whose name is <CODE>variable</CODE>).
	 */
	public static State getStateForVariable(Variable v1,
			VariableDependencyGraph graph) {
		State[] states = graph.getStates();
		for (int k = 0; k < states.length; k++) {
			State state = states[k];
			if (state.getName().equals(v1.getString()))
				return state;
		}
		return null;
	}

	/**
	 * Returns the variable dependency graph for <CODE>grammar</CODE>.
	 * 
	 * @param grammar
	 *            the grammar
	 * @return the variable dependency graph for <CODE>grammar</CODE>.
	 */
	public static VariableDependencyGraph getVariableDependencyGraph(
			Grammar grammar) {
		VariableDependencyGraph graph = new VariableDependencyGraph();
		initializeVariableDependencyGraph(graph, grammar);
		Variable[] variables = getCompleteUsefulVariableSet(grammar)
				.toArray(new Variable[0]);
		for (int k = 0; k < variables.length; k++) {
			Variable v1 = variables[k];
			for (int i = 0; i < variables.length; i++) {
				Variable v2 = variables[i];
				if (i != k && isDependentOn(v1, v2, grammar)) {
					Transition trans = getTransition(v1, v2, graph);
					graph.addTransition(trans);
				}
			}
		}
		return graph;
	}

	/**
	 * Returns a set of transitions that represent all the dependencies
	 * determined by <CODE>production</CODE>.
	 * 
	 * @param production
	 *            the production
	 * @param graph
	 *            the variable dependency graph
	 * @return a set of transitions that represent all the dependencies
	 *         determined by <CODE>production</CODE>.
	 */
	public static Transition[] getTransitionsForProduction(
			Production production, VariableDependencyGraph graph) {
		
		ArrayList<Transition> list = new ArrayList<Transition>();
		Variable v1 = (Variable) production.getLHS().getFirst();
		
		for (Symbol s: production.getRHS()) {
			if (s instanceof Variable) {
				list.add(getTransition(v1, (Variable) s, graph));
			}
		}
		return (Transition[]) list.toArray(new Transition[0]);
	}

	/**
	 * Returns the set of variables in <CODE>grammar</CODE> whose productions
	 * can never be reached from the start symbol. This is determined by the
	 * variable dependency graph <CODE>graph</CODE>.
	 * 
	 * @param grammar
	 *            the grammar
	 * @param graph
	 *            the variable dependency graph
	 * @return the set of variables in <CODE>grammar</CODE> whose productions
	 *         can never be reached from the start symbol. This is determined by
	 *         the variable dependency graph <CODE>graph</CODE>.
	 */
	public static String[] getUselessVariables(Grammar grammar,
			VariableDependencyGraph graph) {
		ArrayList list = new ArrayList();
		UnreachableStatesDetector usd = new UnreachableStatesDetector(graph);
		State[] states = usd.getUnreachableStates();
		for (int k = 0; k < states.length; k++) {
			list.add(states[k].getName());
		}
		return (String[]) list.toArray(new String[0]);
	}

	/**
	 * Removes all productions from <CODE>grammar</CODE> that contain <CODE>variable</CODE>,
	 * either on the left or right hand sides.
	 * 
	 * @param variable
	 *            the variable
	 * @param grammar
	 *            the grammar
	 */
	public static void removeProductionsForVariable(Variable variable,
			Grammar grammar) {
		List<Production> productions = grammar.getProductionsUsingSymbol(variable);
		grammar.removeProductions(productions);
	}

	/**
	 * Returns a grammar with no variables that can not derive strings, by
	 * simply creating a new grammar and adding all productions in <CODE>usefulProductionSet</CODE>
	 * to that grammar.
	 * 
	 * @param usefulProductionSet
	 *            the set of useful productions
	 * @return a grammar with no variables that can not derive strings, by
	 *         simply creating a new grammar and adding all productions in
	 *         <CODE>usefulProductionSet</CODE> to that grammar.
	 */
	private static Grammar getGrammarWithNoVariablesThatCantDeriveStrings(
			Set usefulProductionSet) {
		Grammar g = new ContextFreeGrammar();
		Iterator it = usefulProductionSet.iterator();
		while (it.hasNext()) {
			Production p = (Production) it.next();
			g.addProduction(p);
		}
		return g;
	}

	/**
	 * Returns a grammar, equivalent to <CODE>grammar</CODE> that contains no
	 * useless productions.
	 * 
	 * @param grammar
	 *            the grammar
	 * @return a grammar, equivalent to <CODE>grammar</CODE> that contains no
	 *         useless productions.
	 */
	public static Grammar getUselessProductionlessGrammar(Grammar grammar) {
		Grammar g = new ContextFreeGrammar();
		g.setStartVariable(grammar.getStartVariable());
		if (!getCompleteUsefulVariableSet(grammar).contains(
				grammar.getStartVariable()))
			return g;
		grammar = getTerminalGrammar(grammar);
		VariableDependencyGraph graph = getVariableDependencyGraph(grammar);
		Set useless = new HashSet(Arrays.asList(getUselessVariables(g, graph)));
		Production[] p = grammar.getProductions();
		for (int i = 0; i < p.length; i++) {
			Set variables = new HashSet(Arrays.asList(p[i].getVariables()));
			variables.retainAll(useless);
			if (variables.size() > 0)
				continue;
			g.addProduction(p[i]);
		}
		return g;

	}

	/** the start symbol. */
	protected static String START_SYMBOL = "S";
}
