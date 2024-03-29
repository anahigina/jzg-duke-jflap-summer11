/*
 *  JFLAP - Formal Languages and Automata Package
 * 
 * 
 *  Susan H. Rodger
 *  Computer Science Department
 *  Duke University
 *  August 27, 2009

 *  Copyright (c) 2002-2009
 *  All rights reserved.

 *  JFLAP is open source software. Please see the LICENSE for terms.
 *
 */





package gui.grammar;

import grammar.Grammar;
import grammar.Production;
import gui.GrowableTableModel;
import gui.environment.Universe;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import JFLAPnew.formaldef.symbols.SymbolHelper;
import JFLAPnew.formaldef.symbols.SymbolString;

/**
 * The <CODE>GrammarTableModel</CODE> is used as a model for the input of a
 * grammar. The first column is the left hand side of a production, the second
 * middle column is reserved for a little icon that has a little arrow pointing
 * to the right, and the third and last column is the right hand side of the
 * production. Each row corresponds to a production, or to nothing if what is in
 * the table is not properly a production.
 * 
 * @see grammar.Production
 * 
 * @author Thomas Finley
 */

public class GrammarTableModel extends GrowableTableModel {


	/**
	 * Instantiates a <CODE>GrammarTableModel</CODE>.
	 * 
	 * @param grammar
	 *            the grammar to have for the grammar table model initialized to
	 */
	public GrammarTableModel(Grammar grammar) {
		super(3, new GrammarDataHelper(grammar));
		this.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				GrammarDataHelper data = (GrammarDataHelper) GrammarTableModel.this.getData();
				if (data.hasErrors()){
					JOptionPane.showMessageDialog(null, 
							data.getAndClearErrors(),
							"Warning", 
							JOptionPane.WARNING_MESSAGE);
				}
				System.out.println(data.getAssociatedGrammar());
			}
		});
	}

	


	/**
	 * Returns an empty string for each name.
	 * 
	 * @param column
	 *            the index of the column
	 * @return the empty string
	 */
	public String getColumnName(int column) {
		switch(column){
		case LHS_COLUMN: return "LHS";
		case RHS_COLUMN: return "RHS";
		}
		return "";
	}

	/**
	 * Everything in the table model is editable except for the middle arrow.
	 * 
	 * @param row
	 *            the index for the row
	 * @param column
	 *            the index for the column
	 * @return <CODE>true</CODE> if and only if this is a column other than
	 *         the middle column, which is column index 1
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return column != 1;
	}

	/**
	 * Initializes a row. For this particular object, a row is two strings
	 * surrounding an icon.
	 * 
	 * @param row
	 *            the row we're initializing, which is ignored
	 * @return an array containing the column entries for this new row
	 */
	@Override
	protected Object[] createEmptyRow() {
		Object[] newRow = { "", ARROW, "" };
		return newRow;
	}

	/**
	 * Returns the class of each column, which is a string for both the right
	 * and left hand sides, and an icon for the middle.
	 * 
	 * @param column
	 *            the column to get the class for
	 */
	@Override
	public Class getColumnClass(int column) {
		return column == 1 ? Icon.class : String.class;
	}

	
	
	
	/**
	 * Returns the object at a particular location in the model. This is
	 * overridden to see that the arrow does not display itself in the last
	 * column.
	 * 
	 * @param row
	 *            the row of the object to retrieve
	 * @param column
	 *            the column of the object to retrieve
	 * @return the object at that location
	 */
	@Override
	public Object getValueAt(int row, int column) {
		if (column == 1){
			if (row == getRowCount() - 1)
				return null;
			return super.getValueAt(row, column);
		}
		String s = (String) super.getValueAt(row, column);
		return s == Universe.curProfile.getEmptyStringSymbol() && 
				(column == 0 || row == this.getRowCount()) ? "" : s ;
	}
	

	

	@Override
	public boolean checkEmpty(int row) {
		return this.getValueAt(row, 0).toString().length() == 0 &&
				(this.getValueAt(row, 2).toString().length() == 0 ||
						this.getValueAt(row, 2).toString() == Universe.curProfile.getEmptyStringSymbol());
	}




	public Grammar getAssociatedGrammar() {
		return ((GrammarDataHelper) this.getData()).getAssociatedGrammar();
	}


	/**
	 * The arrow icon. This is simply the item returned for the second column.
	 */
	public static Icon ARROW = new ArrowIcon(20, 8);
	
	/** Constants to be used for columns**/
	private static final int LHS_COLUMN = 0, RHS_COLUMN = 2;

	
}
