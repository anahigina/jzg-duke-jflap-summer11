package gui.help;

import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.KeyStroke;

import automata.Automaton;
import gui.editor.Tool;
import gui.viewer.AutomatonDrawer;
import gui.viewer.AutomatonPane;

public class HelpTool extends Tool {

	public HelpTool(AutomatonPane view, AutomatonDrawer drawer) {
		super(view, drawer);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		super.mouseClicked(event);
	}

	@Override
	public String getShortcutToolTip() {
		// TODO Auto-generated method stub
		return super.getShortcutToolTip();
	}

	@Override
	public String getToolTip() {
		// TODO Auto-generated method stub
		return super.getToolTip();
	}

	@Override
	protected AutomatonPane getView() {
		// TODO Auto-generated method stub
		return super.getView();
	}

	@Override
	protected AutomatonDrawer getDrawer() {
		// TODO Auto-generated method stub
		return super.getDrawer();
	}

	@Override
	protected Icon getIcon() {
		// TODO Auto-generated method stub
		return super.getIcon();
	}

	@Override
	protected Automaton getAutomaton() {
		// TODO Auto-generated method stub
		return super.getAutomaton();
	}

	@Override
	public KeyStroke getKey() {
		// TODO Auto-generated method stub
		return super.getKey();
	}

}
