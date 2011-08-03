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





package regular;

import gui.environment.Universe;

import java.util.*;

import JFLAPnew.formaldef.symbols.SymbolString;

/**
 * The discretizer contains method to split a regular expression string into
 * parts. For example, if "a+be+(c+d)" is passed in to the method {@link #or},
 * then that will be "discretized" into the subexpressions "a", "be", and
 * "(c+d)".
 * 
 * @author Thomas Finley
 */

public class Discretizer {
	/**
	 * This is another one of those static method collections. Yup.
	 */
	private Discretizer() {
	}

	/**
	 * Given a regular expression, this will return the subexpressions that,
	 * when or-ed together, result in the expression.
	 * 
	 * @param pk
	 *            the regular expression
	 * @return an array of the subexpressions
	 */
	public static String[] or(SymbolString pk) {
		ArrayList se = new ArrayList(); // Subexpressions.
		int start = 0;
		int level = 0;
		for (int i = 0; i < pk.length(); i++) {
			if (pk.charAt(i) == '(')
				level++;
			if (pk.charAt(i) == ')')
				level--;
			if (pk.charAt(i) != '+')
				continue;
			if (level != 0)
				continue;
			// First level or!
			se.add(delambda(pk.substring(start, i)));
			start = i + 1;
		}
		se.add(delambda(pk.substring(start)));
		return (String[]) se.toArray(new String[0]);
	}

	/**
	 * Given a regular expression, this will return the subexpressions that,
	 * when concatenated together, will result in the expression.
	 * 
	 * @param kk
	 *            the regular expression
	 * @return an array of the subexpressions
	 */
	public static String[] cat(SymbolString kk) {
		ArrayList se = new ArrayList(); // Subexpressions.
		int start = 0;
		int level = 0;
		for (int i = 0; i < kk.length(); i++) {
			char c = kk.charAt(i);
			if (c == ')') {
				level--;
				continue;
			}
			if (c == '(')
				level++;
			if (!(c == '(' && level == 1) && level != 0)
				continue;
			if (c == '+') {
				// Hum. That shouldn't be...
				throw new IllegalArgumentException(
						"+ encountered in cat discretization!");
			}
			if (c == '*')
				continue;
			// Not an operator, and on the first level!
			if (i == 0)
				continue;
			se.add(delambda(kk.substring(start, i)));
			start = i;
		}
		se.add(delambda(kk.substring(start)));
		return (String[]) se.toArray(new String[0]);
	}

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			// //System.out.println(Arrays.asList(or(args[i])));
			// //System.out.println(Arrays.asList(cat(args[i])));
		}
	}

	/**
	 * Given a string, returns the string, or the empty string if the string is
	 * the lambda string.
	 * 
	 * @param string
	 *            the string to possibly replace
	 * @return the string, or the empty string if the string is the lambda
	 *         string
	 */
	public static String delambda(String string) {
		return string.equals(Universe.curProfile.getEmptyStringSymbol()) ? "" : string;
	}
}
