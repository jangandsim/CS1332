package Olympic;


public class LolAthlete extends Athlete {
    private boolean trollOrNot;
    public LolAthlete(String name, boolean isACheater, boolean trollOrNot) {
        super(name, isACheater);
        this.trollOrNot = trollOrNot;
    }
    
    @Override
    public void play() {
        System.out.println(String.format("%s played and %s!", getName(), trollOrNot ? "trolled" : "carried"));
    }
}
