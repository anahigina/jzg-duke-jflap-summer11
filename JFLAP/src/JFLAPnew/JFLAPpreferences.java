package JFLAPnew;

import javax.swing.JButton;
import javax.swing.JFrame;

import JFLAPnew.formaldef.test.Toggler;

public class JFLAPpreferences {

	public static boolean amUserDefined = true;
	
	
	public static void setUserDefinedAlphabet(boolean b){
		amUserDefined = b;
	}
	
	public static boolean isUserDefinedAlphabet() {
		return amUserDefined;
	}

	

}
