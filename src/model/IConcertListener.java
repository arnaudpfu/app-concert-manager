package model;

import java.util.ArrayList;

public interface IConcertListener {
    void onNewConcert(ConcertEvent event);
    void onConcertAnnulation(ConcertEvent event);
}
