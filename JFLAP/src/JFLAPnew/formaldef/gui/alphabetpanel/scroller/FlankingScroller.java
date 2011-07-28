package JFLAPnew.formaldef.gui.alphabetpanel.scroller;

import java.awt.Adjustable;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JViewport;

import JFLAPnew.formaldef.gui.DefinitionPanel;
import JFLAPnew.formaldef.gui.alphabetpanel.IUpdate;
import JFLAPnew.formaldef.gui.alphabetpanel.symbolbar.SymbolBarViewport;

public class FlankingScroller extends JComponent implements Adjustable, IUpdate{

	private ScrollButton NegButton,
							 PosButton;
	private Container myParent;
	private JViewport myView;
	private int myOrientation;
	private int myValue;
	public static final int EAST_WEST = 0,
							NORTH_SOUTH = 1;
	
	public FlankingScroller(Container parent, JViewport view, int orientation) {
		myParent = parent;
		myView = view;
		myOrientation = orientation;
		createButtons();
	}

	public void createButtons() {
		switch(myOrientation){
		case EAST_WEST:
			NegButton = new ScrollButton(ScrollButton.WEST, ScrollButton.DECREMENT, this); 
			PosButton = new ScrollButton(ScrollButton.EAST, ScrollButton.INCREMENT, this);
			break;
		case NORTH_SOUTH:
			NegButton = new ScrollButton(ScrollButton.NORTH, ScrollButton.DECREMENT, this); 
			PosButton = new ScrollButton(ScrollButton.SOUTH, ScrollButton.INCREMENT, this);
			break;
		}
		Dimension d= new Dimension(5,myParent.getHeight());
		NegButton.setMaximumSize(d);
		PosButton.setMaximumSize(d);
	}

	@Override
	public void update() {
		if (!this.isNeeded() || myView == null) {
			this.setVisible(false);
			return;
		}
		System.out.println("NEEDED!");
		
		PosButton.setVisible(myValue < this.getMaximum());
		NegButton.setVisible(myValue > this.getMinimum());
		
		int y = myView.getViewPosition().y,
			x = myValue;
		
		myView.setViewPosition(new Point(x,y));
	}
	private boolean isNeeded() {
		return myView.getView() != null && !this.checkViewportSpan();
	}

	@Override
	public void repaint(){
		this.update();
	}
	/**
	 * 
	 * @return false if the viewport does not span its view
	 */
	private boolean checkViewportSpan() {
		return getViewMeasure() <= getViewPortMeasure();
	}

	public void setVisible(boolean b) {
		NegButton.setVisible(b);
		PosButton.setVisible(b);
	}
	
	public JButton getFirstButton() {
		return NegButton;
	}

	public JButton getSecondButton() {
		return PosButton;
	}

	@Override
	public void addAdjustmentListener(AdjustmentListener l) {
		
	}

	@Override
	public int getBlockIncrement() {
		return this.getUnitIncrement();
	}

	@Override
	public int getMaximum() {
		return this.getViewMeasure()-this.getViewPortMeasure();
	}

	@Override
	public int getMinimum() {
		return 0;
	}

	@Override
	public int getOrientation() {
		return myOrientation;
	}

	@Override
	public int getUnitIncrement() {
		return this.getMaximum()/10;
//		return (int) Math.max(5,(getViewMeasure()-getViewPortMeasure())/(double)getViewMeasure()*this.getMaximum());
	}

	@Override
	public int getValue() {
		return myValue;
	}

	@Override
	public int getVisibleAmount() {
		return (getViewPortMeasure()/getViewMeasure())*this.getMaximum();
	}

	public int getViewMeasure() {
		switch(myOrientation){
		case EAST_WEST: 
			return myView.getViewSize().width;
		case NORTH_SOUTH:
			return myView.getViewSize().height;
		}
		return -1;
	}

	public int getViewPortMeasure() {
		switch(myOrientation){
		case EAST_WEST: 
			return myView.getVisibleRect().width;
		case NORTH_SOUTH:
			return myView.getVisibleRect().height;
		}
		return -1;
	}

	@Override
	public void removeAdjustmentListener(AdjustmentListener l) {
		
	}

	@Override
	public void setBlockIncrement(int b) {
		
	}

	@Override
	public void setMaximum(int max) {
		
	}

	@Override
	public void setMinimum(int min) {
		
	}

	@Override
	public void setUnitIncrement(int u) {
		
	}

	@Override
	public void setValue(int v) {
		myValue = Math.min(this.getMaximum(), v);
		myValue = Math.max(this.getMinimum(), myValue);
		this.repaint();
	}

	@Override
	public void setVisibleAmount(int v) {
		
	}
	

}
