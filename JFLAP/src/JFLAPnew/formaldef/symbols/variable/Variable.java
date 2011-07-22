package JFLAPnew.formaldef.symbols.variable;

import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.terminal.Terminal;



public class Variable extends Symbol{

	@Override
	public Variable clone() {
		return (Variable) super.clone();
	}
	
	public Variable(String s) {
		super(s);
	}

}
