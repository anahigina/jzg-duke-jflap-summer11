package JFLAPnew.formaldef.gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;

public class GUIHelper {

	
	
	public static <T extends Component> T getAncestorOfClass(Component comp, Class<T> parentClass){
		while (!parentClass.isAssignableFrom(comp.getClass())){
			comp = comp.getParent();
		}
		return (T) comp;
	}
	
	public static void closeAncestorFrame(Component comp){
		WindowEvent winClosingEvent = new WindowEvent( getAncestorOfClass(comp, Window.class), WindowEvent.WINDOW_CLOSING );
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent );
	}
	
	
}


