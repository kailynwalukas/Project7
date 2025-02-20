package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Dumbbell;

public class LiftActionListener implements ActionListener {
	
	private static final Dumbbell INTSRUCTIONS = new Dumbbell("Lift ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox liftJComboBox;
	private boolean enabled;
	private List<Dumbbell> dumbbells;

	public LiftActionListener(GraphicalUserInterface gui, Person player, JComboBox liftJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.liftJComboBox = liftJComboBox;
		this.enabled = true;
		dumbbells = Dumbbell.dumbbellsIn(player.getPlace());
		liftJComboBox.addItem(INTSRUCTIONS);
		for (Dumbbell dumbbell : dumbbells) {
			liftJComboBox.addItem(dumbbell);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		liftJComboBox.removeAllItems();
		dumbbells = Dumbbell.dumbbellsIn(player.getPlace());
		liftJComboBox.addItem(INTSRUCTIONS);
		for (Dumbbell dumbbell : dumbbells) {
			liftJComboBox.addItem(dumbbell);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Dumbbell item = (Dumbbell) liftJComboBox.getSelectedItem();
			if (!item.getName().equals(INTSRUCTIONS.getName())) {
				gui.displayMessage("\n>>> Lift " + item);
				player.lift(item);
				gui.playTurn();
			}
		}
	}
}

