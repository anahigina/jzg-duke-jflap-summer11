package JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.scroller;

import java.awt.Adjustable;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.AdjustmentListener;

import javax.accessibility.AccessibleContext;
import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JViewport;
import javax.swing.plaf.ScrollBarUI;

import JFLAPnew.formaldef.gui.IUpdate;
import JFLAPnew.formaldef.gui.definitionpanel.DefinitionPanel;
import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.symbolbar.SymbolBarScrollPane;

public class ThinScrollBar extends JScrollBar{

	public ThinScrollBar(int orientation, int thinness) {
		super(orientation);
		this.setSize(this.getSize().width, thinness);
		this.setUI(new ThinScrollBarUI());
	}
	
}
