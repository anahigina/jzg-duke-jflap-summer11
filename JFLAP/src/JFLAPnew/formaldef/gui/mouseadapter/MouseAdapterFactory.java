package JFLAPnew.formaldef.gui.mouseadapter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import JFLAPnew.formaldef.gui.DefinitionPanel;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBar;
import JFLAPnew.formaldef.gui.symbolbar.SymbolBox;

public class MouseAdapterFactory {

	public static MouseListener manufactureListener(Class clazz){
		if (clazz.isAssignableFrom(SymbolBox.class))
			return createSymbolBoxListener();
		else if(clazz.isAssignableFrom(SymbolBar.class))
			return createSymbolBarListener();
		return new MouseClickAdapter(){};
	}

	private static MouseListener createSymbolBarListener() {
		return new MouseClickAdapter<SymbolBar>() {

			@Override
			public void rightClickResponse(MouseEvent e, SymbolBar component) {
				component.getMenu().show(component, e.getX(), e.getY());
			}

			@Override
			public void leftClickResponse(MouseEvent e, SymbolBar component) {
				((DefinitionPanel) component.getParent()).setSelectedBar(component);
			}
		};
	}

	private static MouseListener createSymbolBoxListener() {
		return new MouseClickAdapter<SymbolBox>(){

			@Override
			public void rightClickResponse(MouseEvent e, SymbolBox component) {
				component.getMenu().show(component, e.getX(), e.getY());
			}

			@Override
			public void leftClickResponse(MouseEvent e, SymbolBox component) {
				//TODO: add to currently open text pane
				System.out.println(component.getSymbol());
			}
			
		};
	}
	
}
