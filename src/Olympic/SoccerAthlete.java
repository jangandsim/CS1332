package Olympic;

class SoccerAthlete extends Athlete {
    private boolean canScoreGoal;
    public SoccerAthlete(String name, boolean canScoreGoal,boolean isACheater) {
        super(name, isACheater);
        this.canScoreGoal = canScoreGoal;
    }
    
    @Override
    /**
     * Has the athlete shoot for a goal.
     */
    public void play() {
        System.out.println(String.format("%s shoots and %s!",
            getName(), canScoreGoal ? "scores" : "misses"));
    }
    /**
     * @return whether or not this athlete is good at soccer.
     */
    public boolean getCanScoreGoal() {
        return this.canScoreGoal;
    }
}
