public class Billet {
    private Concert concert;

    public Billet(Concert concert) {
        this.concert = concert;
    }

    public Concert getConcert() {
        return concert;
    }

    public String toString() {
        return "Billet pour le concert de " + this.concert.getNom();
    }
}
