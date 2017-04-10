package Olympic;

import java.util.Random;

/**
 * Represents a very overworked event manager.
 *
 * @author Confused Coder
 * @version 1.0
 */
public class EventManager {
    private boolean hasGroceries;
    private String[] food = {"Stew", "Fish", "Steak", "Chicken", "Veggies"};
    private int nextFood;

    /**
     * Makes a group of athletes exercise.
     *
     * @param athletes the athletes to exercise.
     */
    public void trainAthletes(Athlete... athletes) {
        for (Athlete athlete : athletes) {
            athlete.exercise(pickNumberOfPushups());
        }
    }

    /**
     * @return a random number in the range [10, 30] for number of pushups.
     */
    private int pickNumberOfPushups() {
        Random rand = new Random();
        int numberOfPushups = rand.nextInt(20) + 11;
        System.out.println("Drop and give me " + numberOfPushups + "!");
        return numberOfPushups;
    }

    /**
     * Makes a group of athletes eat.
     */
    public void feedAthletes(Athlete... athletes) {
        if (hasGroceries) {
            for (Athlete athlete : athletes) {
                athlete.eat(10, chooseFood());
            }
            hasGroceries = false;
        } else {
            System.out.println("Can't feed them without groceries!");
            driveToGroceryStore();
            feedAthletes(athletes);
        }
    }

    /**
     * Gets the groceries to cook for the athletes.
     */
    private void driveToGroceryStore() {
        System.out.println("Time to get some groceries!");
        this.hasGroceries = true;
    }

    /**
     * @return the next food item to feed the athletes.
     */
    private String chooseFood() {
        return food[(nextFood++) % food.length];
    }

    /**
     * Inspects all of the passed in athletes.
     *
     * @param athletes the athletes to inspect.
     * @return if an athlete is a cheater.
     */
    public boolean inspectAthletes(Athlete... athletes) {
        boolean foundCheater = false;
        for (Athlete athlete : athletes) {
            foundCheater = interrogateAthlete(athlete) || foundCheater;
        }
        return foundCheater;
    }

    /**
     * Interrogates one athlete.
     * @param athlete the athlete to interrogate.
     * @return if the athlete is a cheater.
     */
    private boolean interrogateAthlete(Athlete athlete) {
        System.out.println(athlete.getName()
            + ", have you been cheating?!");
        System.out.println("No, sir!");
        if (athlete.getIsACheater()) {
            System.out.println("I don't believe you!");
            return true;
        } else {
            System.out.println("K");
            return false;
        }
    }

    /**
     * Has all of the athletes compete.
     *
     * @param athletes the athletes to play.
     */
    public void organizeGames(Athlete... athletes) {
        for (Athlete athlete : athletes) {
            playGame(athlete);
        }
    }

    /**
     * Has one athlete play a game, and prints out a message if they one or
     * lost.
     *
     * @param athlete the athlete to play.
     */
    private void playGame(Athlete athlete) {
        athlete.play();
        if (athlete.getHunger() <= 15) {
            System.out.println(athlete.getName() + " won!");
        } else {
            System.out.println(athlete.getName() + " lost!");
        }
    }
}