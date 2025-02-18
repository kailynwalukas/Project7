package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Witch extends AutoPerson {
	
	private Place pond;
	
	public Witch(String name, Place place, int threshold, Place pond) {
		super(name, place, threshold);
		this.pond = pond;
	}

	@Override
	public void act() {
		List<Person> others = otherPeopleAtSamePlace();
		if (!others.isEmpty()) {
			Person victim = others.get(Utility.randInt(others.size()));
			// if (victim.getPossessions().)
			curse(victim);
		} else {
			super.act();
		}
	}

	public void curse(Person person) {
		List<Thing> personsPossessions = new ArrayList<Thing>(person.getPossessions());
		for (Thing thing : personsPossessions) {
			if (thing.getName() == "White Chocolate Raspberry Truffle") {
				say("Oooh, I will eat your chocolate!");
				person.lose(thing);
			} else {
			say("Hah hah hah, I'm going to turn you into a frog, " + person);
			turnIntoFrog(person);
			say("Hee hee " + person + " looks better in green!");
		}
		}
	}
	
	public void eatChocolates(Person person) {
		List<Thing> personsPossessions = new ArrayList<Thing>(person.getPossessions());
		for (Thing thing : personsPossessions) {
			// if (personsPossessions.contains(thing.getName() == "White Chocolate Raspberry Truffle"))
			person.lose(thing);
		}
	}
	
	public void turnIntoFrog(Person person) {
		// need to copy person.getPossessions() in order to avoid a ConcurrentModificationException
		List<Thing> personsPossessions = new ArrayList<Thing>(person.getPossessions());
		for (Thing thing : personsPossessions) {
			person.lose(thing);
		}
		person.say("Ribbitt!");
		person.moveTo(pond);
	}
}
