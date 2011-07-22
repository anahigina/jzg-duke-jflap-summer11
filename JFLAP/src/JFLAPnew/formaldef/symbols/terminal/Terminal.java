package JFLAPnew.formaldef.symbols.terminal;

import JFLAPnew.formaldef.symbols.Symbol;



public class Terminal extends Symbol{

	

	@Override
	public Terminal clone() {
		return (Terminal) super.clone();
	}

	public Terminal(String s) {
		super(s);
	}

}
