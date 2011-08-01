package JFLAPnew.formaldef.gui.definitionpanel;

import JFLAPnew.formaldef.gui.definitionpanel.alphabetpanel.AlphabetPane;
import JFLAPnew.formaldef.gui.selection.ISelectable;


public interface ISelector {

	public abstract void select(AlphabetPane component);

	public abstract void clearSelection();

	public abstract ISelectable getSelected();

	public abstract void canSelect(boolean allowSelection);

}