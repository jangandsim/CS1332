package Olympic;

class TennisAthlete extends Athlete {
    private int serveSpeed;
    public TennisAthlete(String name, int serveSpeed, boolean isACheater) {
        super(name, isACheater);
        this.serveSpeed = serveSpeed;
    }
    
    @Override
    /**
     * Has the athlete serve a tennis ball.
     */
    public void play() {
        System.out.println(String.format("%s serves the ball at %dmph!",
            getName(), this.serveSpeed));
    }
    
    /**
     * @return the tennis serve speed of this athlete.
     */
    public int getServeSpeed() {
        return this.serveSpeed;
    }

}
