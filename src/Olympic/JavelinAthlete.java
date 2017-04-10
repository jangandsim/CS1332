package Olympic;

class JavelinAthlete extends Athlete {

    protected double javelinDamage;
    
    public JavelinAthlete(String name, double javelinDamage, boolean isACheater) {
        super(name, isACheater);
        this.javelinDamage = javelinDamage;
    }

    @Override
    /**
     * Has the athlete throw their javelin.
     */
    public void play() {
        System.out.println(String.format("%s throws the javelin and inflicts"
            + " %f damage!", getName(), this.javelinDamage));
    }
    /**
     * @return the damage this athlete's javelin inflicts.
     */
    public double getJavelinDamage() {
        return this.javelinDamage;
    }
}
