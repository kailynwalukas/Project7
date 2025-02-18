package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Chocolate extends Thing {
	
	public Chocolate(String title) {
		super(title);
	}

	public void beEaten() {
		Person owner = getOwner();
		if (owner == null) {
			Utility.displayMessage("No one has " + getName());
		} else {
			owner.say("I have eaten " + getName());
		}
	}
	
	public static List<Chocolate> chocolatesIn(Place place) {
		ArrayList<Chocolate> chocolatesIn = new ArrayList<Chocolate>();
		for (Thing thing : place.getContents()) {
			if (thing instanceof Chocolate) {
				chocolatesIn.add((Chocolate) thing);
			}
		}
		return chocolatesIn;
	}
}