package JFLAPnew;

import grammar.Grammar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.FormalDefintionFactory;
import JFLAPnew.formaldef.MetaDefinition;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;
import JFLAPnew.formaldef.test.Toggler;

public class JFLAPpreferences {

	public static boolean amUserDefined = false;
	
	
	public static void setUserDefinedAlphabet(boolean b){
		amUserDefined = b;
	}
	
	public static boolean isUserDefinedAlphabet() {
		return amUserDefined;
	}

	
	public static MetaDefinition getDefaultDefintions(){
		ArrayList<Variable> vars = new ArrayList<Variable>();
		ArrayList<Terminal> terms = new ArrayList<Terminal>();
		for (int i = 0; i < 26; i++){
			char upper = (char)('A' + i),
				 lower = (char)('a' + i);
			vars.add(new Variable(Character.toString(upper)));
			terms.add(new Terminal(Character.toString(lower)));
		}
		vars.get('S'-'A').setSpecial(true);
		MetaDefinition def = new MetaDefinition();
		for (Class<? extends FormalDefinition> c: FormalDefintionFactory.getAllGenericClasses()){
			try {
				def.add(createDefintion(c, vars, terms));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("UHOH");
			} 
			
		}
		return def;
	}

	private static FormalDefinition createDefintion(Class<? extends FormalDefinition> c,
			ArrayList<Variable> vars, ArrayList<Terminal> terms) throws InstantiationException, IllegalAccessException {
		
		FormalDefinition fd = c.newInstance();

		if (fd instanceof Grammar){
			((Grammar) fd).getVariableAlphabet().addAll(cloneSymbols(vars));
			((Grammar) fd).getTerminalAlphabet().addAll(cloneSymbols(terms));
		}
		else{
			int i = 0;
			for (IAlphabet alph: fd){
				alph.addAll(cloneSymbols(convertToTerminals(vars)));
				alph.addAll(cloneSymbols(terms));
			}
		}
			
		
		return fd;
	}

	private static ArrayList<Terminal> convertToTerminals(ArrayList<Variable> vars) {
		ArrayList<Terminal> terms = new ArrayList<Terminal>();
		
		for(Variable v: vars)
			terms.add(new Terminal(v.getString()));
		
		return terms;
	}

	public static <T extends Symbol> T[] cloneSymbols(ArrayList<T> input) {
		T[] symbols = (T[]) Array.newInstance(input.get(0).getClass(), input.size());
		
		for (int i = 0; i < symbols.length; i++){
			symbols[i] = (T) input.get(i).clone();
		}
		
		return symbols;
		
	}
	
	public static void main (String[] args){
		System.out.println(getDefaultDefintions());
	}
	
}
