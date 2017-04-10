package Olympic;

/**
 * Represents a superfan at the Olympics.
 *
 * @author Confused Coder
 * @version 1.0
 */
public class SuperFan extends Spectator {

    /**
     * Public constructor.
     *
     * @param favoriteAthlete this spectator's favorite athlete... for now...
     */
    public SuperFan(Athlete favoriteAthlete) {
        super(favoriteAthlete);
    }

    @Override
    public void cheerForFavorite() {
        for (int i = 0; i < 5; i++) {
            System.out.println("I LOVE YOU " + getFavoriteAthlete().getName()
                + "!!!!!!!!!!");
        }
    }

    @Override
    public void setFavoriteAthlete(Athlete favoriteAthlete) {}
}
