package model;

import model.exceptions.RoomAlreadyBookedException;
import model.exceptions.RoomNotBookedException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

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
        if(isBookedOn(date)) throw new RoomAlreadyBookedException(this);
        bookedDates.put(date,0);
    }

    public void unbook(Date date) throws RoomNotBookedException {
        if(!isBookedOn(date)) throw new RoomNotBookedException(this);
        bookedDates.remove(date);
    }

    private boolean isBookedOn(Date date) {
        return bookedDates.get(date) != null;
    }

    public void decrementFreePlaces(Date date) {
        if(!isBookedOn(date)) {
            throw new IllegalArgumentException("Il n'a pas de réservation pour la salle " + getName() + " le " + new SimpleDateFormat("dd/MM/yyyy").format(date));
        }
        bookedDates.put(date, bookedDates.get(date) + 1);
    }

    public void incrementFreePlaces(Date date) {
        if(!isBookedOn(date)) {
            throw new IllegalArgumentException("Il n'a pas de réservation pour la salle " + getName() + " le " + new SimpleDateFormat("dd/MM/yyyy").format(date));
        }
        bookedDates.put(date, bookedDates.get(date) - 1);
    }

    public boolean isFull(Date date) {
        if(!isBookedOn(date)) {
            throw new IllegalArgumentException("Il n'a pas de réservation pour la salle " + getName() + " le " + new SimpleDateFormat("dd/MM/yyyy").format(date));
        }
        return bookedDates.get(date) >= nbMaxPlaces;
    }
    public String getName() { return name; }
    public int getNbMaxPlaces() { return nbMaxPlaces; }
    public int getNbUnavailablePlaces(Date date) {
        if(!isBookedOn(date)) {
            throw new IllegalArgumentException("Il n'a pas de réservation pour la salle " + getName() + " le " + new SimpleDateFormat("dd/MM/yyyy").format(date));
        }
        return bookedDates.get(date);
    }
    public String getPlacesRatio(Date date) { return getNbUnavailablePlaces(date) + " / " + nbMaxPlaces; }
    public String toString() { return this.getName(); }

    public boolean hasNoReservation() { return bookedDates.isEmpty(); }

    public Set<Date> getDates() { return bookedDates.keySet(); }
}
