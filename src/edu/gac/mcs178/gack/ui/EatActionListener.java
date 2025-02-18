package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Chocolate;

public class EatActionListener implements ActionListener {
	
	private static final Chocolate INTSRUCTIONS = new Chocolate("Eat ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox eatJComboBox;
	private boolean enabled;
	private List<Chocolate> chocolates;

	public EatActionListener(GraphicalUserInterface gui, Person player, JComboBox eatJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.eatJComboBox = eatJComboBox;
		this.enabled = true;
		chocolates = Chocolate.chocolatesIn(player.getPlace());
		eatJComboBox.addItem(INTSRUCTIONS);
		for (Chocolate chocolate : chocolates) {
			eatJComboBox.addItem(chocolate);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		eatJComboBox.removeAllItems();
		chocolates = Chocolate.chocolatesIn(player.getPlace());
		eatJComboBox.addItem(INTSRUCTIONS);
		for (Chocolate chocolate : chocolates) {
			eatJComboBox.addItem(chocolate);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Chocolate item = (Chocolate) eatJComboBox.getSelectedItem();
			if (!item.getName().equals(INTSRUCTIONS.getName())) {
				gui.displayMessage("\n>>> Eat " + item);
				player.eat(item);
				gui.playTurn();
			}
		}
	}
}

