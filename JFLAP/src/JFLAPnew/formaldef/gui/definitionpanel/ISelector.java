package JFLAPnew.formaldef.gui.definitionpanel;


public interface ISelector {

	public abstract void select(AlphabetPane component);

	public abstract void clearSelection();

	public abstract ISelectable getSelected();

	public abstract void canSelect(boolean allowSelection);

}