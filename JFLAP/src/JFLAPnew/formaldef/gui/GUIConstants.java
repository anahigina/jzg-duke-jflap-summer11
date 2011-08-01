package JFLAPnew.formaldef.gui;

import java.awt.Color;

import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class GUIConstants {
	public static final Color DEFAULT = new Color(225, 225, 225),
							  SPECIAL_SYMBOL = new Color(235, 235, 150),
							  BAR_SELECTED = new Color(140, 175, 255);
	
	public static final String ADD_LABEL = "Add",
							   MODIFY_LABEL = "Modify",
							   REMOVE_LABEL = "Remove",
							   SETSPECIAL_LABEL = "Set Special";
	
	/** The end of String Symbol */
	public static Symbol END_OF_STRING = new Terminal("$");
	
	public static final int VARIABLE_PRIORITY = 1,
							TERMINAL_PRIORITY = 2,
							INPUT_PRIORITY = 3,
							TAPE_PRIORITY = 4,
							STACK_PRIORITY = 5,
							OUTPUT_PRIORITY= 6;

	
}
