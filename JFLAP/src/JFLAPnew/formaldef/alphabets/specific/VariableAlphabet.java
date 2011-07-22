package JFLAPnew.formaldef.alphabets.specific;

import gui.errors.BooleanWrapper;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.grouping.GroupingPair;
import JFLAPnew.formaldef.grouping.IGrouping;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolHelper;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;

public class VariableAlphabet extends GrammarAlphabet<Variable> implements IGrouping, ISpecialSymbol<Variable>{

	private GroupingPair myGrouping;


	public VariableAlphabet(FormalDefinition parent) {
		super(parent);
	}

	
	@Override
	public BooleanWrapper canAdd(Variable sym) {
		BooleanWrapper canAdd = new BooleanWrapper(true);
		TerminalAlphabet terminals = this.getParent().getAlphabetByClass(TerminalAlphabet.class);
		if (this.usingGrouping()){
			if ((canAdd = checkValidGroupingSyntax(sym.getString())).isTrue()){
				if (terminals != null)
					canAdd = terminals.checkIdentical(sym.getString().substring(1, sym.length()-1), this.getName());
			}
		}
		else if (terminals != null)
			canAdd = checkNoOveralappingChars(sym.getString(), terminals);
		
		return BooleanWrapper.combineWrappers(canAdd, super.canAdd(sym));
	}

	@Override
	protected boolean areTooSimilar(Variable s1, Variable s2) {
		if (this.usingGrouping())
			return super.areTooSimilar(SymbolHelper.trim(s1,1), SymbolHelper.trim(s2,1));
		return super.areTooSimilar(s1, s2);
	}


	private BooleanWrapper checkValidGroupingSyntax(String string) {
		if (string.length() < 2 || !(string.contains(myGrouping.getOpenGroup()) && 
				string.contains(myGrouping.getCloseGroup())))
			return new BooleanWrapper(false, "The symbol " + string + 
					" does not contain the necessary grouping characters");
		
		String test = string.substring(1, string.length()-1);
		return new BooleanWrapper(!(test.contains(myGrouping.getOpenGroup()) || 
										test.contains(myGrouping.getCloseGroup())), 
						"You cannot add a symbol with internal grouping characters.");
		
	}

	@Override
	public BooleanWrapper isComplete() {
		return BooleanWrapper.combineWrappers(super.isComplete(), 
										new BooleanWrapper(this.getStartVariable() != null, 
										"The " + this.getName() + " requires a start variable."));
	}

	public Variable getStartVariable() {
		return this.getSpecialSymbol();
	}
	
	public BooleanWrapper setStartVariable(Variable variable) {
		return this.setSpecialSymbol(variable.getString());
	}


	@Override
	public Integer getPriority() {
		return 1;
	}

	@Override
	public String getName() {
		return "Variable Alphabet";
	}


	@Override
	public void setGrouping(GroupingPair gp) {
		myGrouping = gp;
	}


	@Override
	public GroupingPair getGrouping() {
		return myGrouping;
	}


	@Override
	public boolean usingGrouping() {
		return myGrouping != null;
	}

	@Override
	public char[] getDisallowedCharacers() {
		return new char[0];
	}


	
	
	
	@Override
	public Variable getSpecialSymbol() {
		for (Variable s: this.getSymbols())
			if (s.isSpecial())
				return s;
		return null;
	}
	
	@Override
	public BooleanWrapper setSpecialSymbol(Variable t) {
		return this.setSpecialSymbol(t.getString());
	}

	@Override
	public BooleanWrapper setSpecialSymbol(String string) {
		Variable sym = this.getSymbol(string);
		if (sym != null){
			this.clearSpecialSymbol();
			sym.setSpecial(true);
		}
		return new BooleanWrapper(sym != null, "The " + this.getName() + " does not contain a symbol " + 
							"corresponding to the input string");
	}

	@Override
	public void clearSpecialSymbol() {
		for (Variable s: this.getSymbols())
			s.setSpecial(false);
	}
	
	@Override
	public String getSpecialSymbolName() {
		return "Start Variable";
	}


	public BooleanWrapper setStartVariable(String variable) {
		return this.setSpecialSymbol(variable);
	}


}
