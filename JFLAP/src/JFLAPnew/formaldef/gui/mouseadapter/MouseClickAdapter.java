package JFLAPnew.formaldef.gui.mouseadapter;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import JFLAPnew.JFLAPpreferences;
import JFLAPnew.formaldef.gui.symbolbar.IMenu;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBox;
import JFLAPnew.formaldef.symbols.Symbol;

public abstract class MouseClickAdapter<T extends Component> extends MouseAdapter {

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1){
			this.leftClickResponse(e, (T) e.getComponent());
		}
		else if(e.getButton() == MouseEvent.BUTTON3){
			this.rightClickResponse(e, (T) e.getComponent());
		}
		else if(e.getButton() == MouseEvent.BUTTON2){
			this.middleClickResponse(e, (T) e.getComponent());
		}
		
	}

	public void middleClickResponse(MouseEvent e, T component) {
		//Fill this in yourself!		
	}

	public void rightClickResponse(MouseEvent e, T component) {
		//Fill this in yourself!		
	}

	public void leftClickResponse(MouseEvent e, T component) {
		//Fill this in yourself!
	}

}
