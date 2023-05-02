package model;

/**
 * A Room has a name and a capacity.
 */
public class Room {
    private String name;
    private int nbMaxPlaces;
    private int nbFreePlaces;

    public Room(String name, int nbMaxPlaces) {
        this.name = name;
        this.nbMaxPlaces = nbMaxPlaces;
        this.nbFreePlaces = nbMaxPlaces;
    }

    public Room(String name, int nbMaxPlaces, int nbFreePlaces) {
        this.name = name;
        this.nbMaxPlaces = nbMaxPlaces;
        this.nbFreePlaces = nbFreePlaces;
    }

    public void decrementFreePlaces() {
        this.nbFreePlaces -= 1;
    }

    public void incrementFreePlaces() {
        this.nbFreePlaces += 1;
    }

    public boolean isFull() { return nbFreePlaces <= 0; }
    public String getName() {
        return name;
    }

    public int getNbMaxPlaces() {
        return nbMaxPlaces;
    }

    public int getNbFreePlaces() {
        return nbFreePlaces;
    }
}
