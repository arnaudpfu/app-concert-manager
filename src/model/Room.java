package model;

/**
 * A Room has a name and a capacity.
*/
public class Room {
    private String name;
    private int nbPlaces;
    private int placesDispo;


    public Room(String name, int nbPlaces, int placesDispo) {
        this.name = name;
        this.nbPlaces = nbPlaces;
        this.placesDispo = placesDispo;
    }

    public void decrementPlacesDispo() {
         this.placesDispo -= 1;
    }

    public void incrementPlacesDispo() {
        this.placesDispo += 1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getPlacesDispo() {
        return placesDispo;
    }

    public void setPlacesDispo(int placesDispo) {
        this.placesDispo = placesDispo;
    }
}
