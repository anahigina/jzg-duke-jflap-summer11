package JFLAPnew.formaldef.grouping;

public class GroupingPair {

	
	private String OPEN, CLOSE;

	public GroupingPair(String open, String close){
		OPEN = open;
		CLOSE = close;
	}

	public GroupingPair(char open, char close) {
		this(Character.toString(open), Character.toString(close));
	}

	public String getOpenGroup() {
		return OPEN;
	}

	public String getCloseGroup() {
		return CLOSE;
	}

	public String toString(){
		return "Grouping: " + this.OPEN + " " + this.CLOSE;
	}
	
}
