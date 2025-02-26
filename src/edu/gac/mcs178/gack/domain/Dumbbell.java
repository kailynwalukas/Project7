package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Dumbbell extends Thing {
	
	public Dumbbell(String title) {
		super(title);
	}

	public void beLifted() {
		Person owner = getOwner();
		if (owner == null) {
			Utility.displayMessage("No one has " + getName());
		} else {
			owner.say("I lift " + getName());
			if (owner.getStrength() == "Weak"){
				owner.setStrength("Strong");
			} else {
				owner.setStrength("Super Strong");
			}
			owner.say("I am " + owner.getStrength());
		}
	}
	
	public static List<Dumbbell> dumbbellsIn(Place place) {
		ArrayList<Dumbbell> dumbbellsIn = new ArrayList<Dumbbell>();
		for (Thing thing : place.getContents()) {
			if (thing instanceof Dumbbell) {
				dumbbellsIn.add((Dumbbell) thing);
			}
		}
		return dumbbellsIn;
	}
}