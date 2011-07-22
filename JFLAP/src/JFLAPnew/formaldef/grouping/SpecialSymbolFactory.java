package JFLAPnew.formaldef.grouping;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeMap;

import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class SpecialSymbolFactory {
	
	private static final String DELIMITER = "\\.";
	private static TreeMap<Integer, GroupingPair> GROUPINGS;
	private static TreeMap<Integer, Terminal> BOTTOM_OF_STACK;
	static {
		GROUPINGS = new TreeMap<Integer, GroupingPair>();
		BOTTOM_OF_STACK = new TreeMap<Integer, Terminal>();
		ResourceBundle rb = ResourceBundle.getBundle("JFLAPnew.formaldef.grouping.special");
		for (String key: rb.keySet()){
			String groups = rb.getString(key);
			if (key.startsWith("grouping"))
				GROUPINGS.put(Integer.parseInt(key.split(DELIMITER)[1]), new GroupingPair(groups.charAt(0), groups.charAt(1)));
			else if(key.startsWith("bottomstack"))
				BOTTOM_OF_STACK.put(Integer.parseInt(key.split(DELIMITER)[1]), new Terminal(groups));
		}
	}
	
	public static GroupingPair getBestGrouping(Collection<Character> invalid){
		for (Integer key: GROUPINGS.keySet()){
			GroupingPair gp = GROUPINGS.get(key);
			if (!invalid.contains(gp.getOpenGroup()) && !invalid.contains(gp.getCloseGroup())){
				return gp;
			}
		}
		
		return new GroupingPair(' ',' ');
	}
	
	public static Terminal getReccomendedBotOfStackSymbol(Collection<Character> invalid){
		for (Integer key: BOTTOM_OF_STACK.keySet()){
			Terminal  t = BOTTOM_OF_STACK.get(key);
			if (!invalid.contains(t.toString())){
				return t;
			}
		}
		
		return null;
	}
	
}
