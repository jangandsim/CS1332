package Olympic;

/**
 * Represents a spectator at the Olympics.
 *
 * @author Confused Coder
 * @version 1.0
 */
public class Spectator {
    private Athlete favoriteAthlete;

    /**
     * Public constructor.
     *
     * @param favoriteAthlete this spectator's favorite athlete... for now...
     */
    public Spectator(Athlete favoriteAthlete) {
        this.favoriteAthlete = favoriteAthlete;
    }

    /**
     * Prints out a cheer for this spectator's favorite athlete!
     */
    public void cheerForFavorite() {
        System.out.println("Yay! Go " + favoriteAthlete.getName() + "!");
    }

    /**
     * Sets the new favorite athlete of this spectator.
     *
     * @param favoriteAthlete this spectator's favorite athlete... for now...
     */
    public void setFavoriteAthlete(Athlete favoriteAthlete) {
        this.favoriteAthlete = favoriteAthlete;
    }

    /**
     * @return this spectator's favorite athlete... for now...
     */
    public Athlete getFavoriteAthlete() {
        return this.favoriteAthlete;
    }
}
