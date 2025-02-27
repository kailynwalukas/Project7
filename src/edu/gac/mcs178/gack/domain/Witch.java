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
			curse(victim);
		} else {
			super.act();
		}
	}

	public void curse(Person person) {
		// modify curse so that a witch will eat the victim's chocolate instead of turning them into a frog
		boolean hasChocolates = false;
		List<Thing> personsPossessions = new ArrayList<Thing>(person.getPossessions());
		for (Thing thing : personsPossessions) {
			if (thing instanceof Chocolate) {
				hasChocolates = true;
			}
		}
		if (hasChocolates) {
			say("Oooh, I will eat your chocolate!");
			eatChocolate(person);
		} else {
			say("Hah hah hah, I'm going to turn you into a frog, " + person);
			turnIntoFrog(person);
			say("Hee hee " + person + " looks better in green!");
		}
			
	}
	
	public void eatChocolate(Person person) {
		List<Thing> personsPossessions = new ArrayList<Thing>(person.getPossessions());
		ArrayList<Chocolate> personsChocolates = new ArrayList<Chocolate>();
		for (Thing thing : personsPossessions) {
			if (thing instanceof Chocolate) {
				personsChocolates.add((Chocolate) thing);
			}
		}
		for (Chocolate chocolate : personsChocolates) {
			if (chocolate instanceof Chocolate) {
				this.take(chocolate);
				this.eat(chocolate);
			}
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
