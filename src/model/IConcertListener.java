package model;

public interface IConcertListener {
    void onNewConcert(ConcertEvent event);
    void onConcertAnnulation(ConcertEvent event);
}
