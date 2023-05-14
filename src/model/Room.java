package model;

import model.exceptions.RoomAlreadyBookedException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * A Room has a name and a capacity.
 */
public class Room {
    private String name;
    private int nbMaxPlaces;
    HashMap<Date, Integer> bookedDates;

    public Room(String name, int nbMaxPlaces) {
        this.name = name;
        this.nbMaxPlaces = nbMaxPlaces;
        this.bookedDates = new HashMap<>();
    }

    public void book(Date date) throws RoomAlreadyBookedException {
        if(bookedDates.get(date) != null) throw new RoomAlreadyBookedException(this);
        bookedDates.put(date,0);
    }
    public void decrementFreePlaces(Date date) {
        Integer nbFreePlaces = bookedDates.get(date);
        if(nbFreePlaces == null) {
            throw new IllegalArgumentException("Il n'a pas de réservation pour la salle " + getName() + " le " + new SimpleDateFormat("dd/MM/yyyy").format(date));
        }
        bookedDates.put(date, nbFreePlaces + 1);
    }

    public void incrementFreePlaces(Date date) {
        Integer nbFreePlaces = bookedDates.get(date);
        if(nbFreePlaces == null) {
            throw new IllegalArgumentException("Il n'a pas de réservation pour la salle " + getName() + " le " + new SimpleDateFormat("dd/MM/yyyy").format(date));
        }
        bookedDates.put(date, nbFreePlaces - 1);
    }

    public boolean isFull(Date date) {
        Integer nbFreePlaces = bookedDates.get(date);
        return nbFreePlaces != null && nbFreePlaces >= nbMaxPlaces;
    }
    public String getName() { return name; }
    public HashMap<Date, Integer> getBookedDates() { return bookedDates; }
    public int getNbMaxPlaces() { return nbMaxPlaces; }
    public int getNbUnavailablePlaces(Date date) { return bookedDates.get(date); }
    public String getPlacesRatio(Date date) { return getNbUnavailablePlaces(date) + " / " + nbMaxPlaces; }
    public String toString() { return this.getName(); }
}
