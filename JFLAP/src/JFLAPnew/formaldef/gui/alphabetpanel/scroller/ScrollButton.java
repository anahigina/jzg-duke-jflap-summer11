package JFLAPnew.formaldef.gui.alphabetpanel.scroller;

import java.awt.Adjustable;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.plaf.basic.BasicArrowButton;

public class ScrollButton extends BasicArrowButton implements MouseListener{

	private Adjustable myAdjustable;
	private int myModifier;
	public static final int DECREMENT = -1,
							INCREMENT = 1;
	
	public ScrollButton(int direction, int modifier, Adjustable adj) {
		super(direction);
		myAdjustable = adj;
		myModifier = modifier;
		this.setMargin(new Insets(0,0,0,0));
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		myAdjustable.setValue(myAdjustable.getValue()+myAdjustable.getUnitIncrement()*myModifier);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		myAdjustable.setValue(myAdjustable.getValue()+myAdjustable.getUnitIncrement()*myModifier);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
