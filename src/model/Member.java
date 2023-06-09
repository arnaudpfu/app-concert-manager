package model;

import model.exceptions.FullConcertException;
import model.exceptions.MemberAlreadyBookedException;
import model.exceptions.UnknownTicketException;
import view.pages.MemberPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Member implements IConcertListener, ITicketListener {
    private String name;
    private double priceThreshold;
    private ArrayList<Ticket> tickets;
    private ArrayList<ITicketListener> ticketListeners;
    private MemberPage window;

    public Member(String name, double priceThreshold) {
        this.name = name.toLowerCase();
        this.priceThreshold = priceThreshold;
        this.tickets = new ArrayList<>();
        // Adds itself to the ticket listeners
        this.ticketListeners = new ArrayList<>(List.of(this));
        this.window = null;
    }

    public void addTicketsListener(ArrayList<ITicketListener> listeners) { ticketListeners.addAll(listeners); }

    @Override
    public void onNewConcert(ConcertEvent event) {
        Concert concert = event.getConcert();
        if(window != null) {
            window.addNotification("Le concert " + concert.getName() + " peut vous intéresser");
            window.updateConcerts();
        }
    }

    @Override
    public void onConcertAnnulation(ConcertEvent event) {
        Concert concert = event.getConcert();
        System.out.println(getName() + " apprends que  " + concert.getName() + " est annulé (il a " + getTickets().size() + " tickets)");

        // Getting all tickets to delete
        ArrayList<Ticket> ticketsToDelete = tickets.stream()
                .filter(ticket -> ticket.getConcert() == concert)
                .collect(Collectors.toCollection(ArrayList::new));

        // Deleting tickets
        for (Ticket ticket : ticketsToDelete) {
            tickets.remove(ticket);
        }

        // Notifying the window
        if(window == null) return;
        window.removeNotification("Le concert " + concert.getName() + " peut vous intéresser");
        window.updateConcerts();

        if(ticketsToDelete.isEmpty()) return;
        window.addNotification("Le concert " + concert.getName() + " a été annulé, vous avez perdu " + ticketsToDelete.size() + " tickets :(");
        window.updateTickets();
    }

    @Override
    public void onReservation(TicketEvent event) {
        Ticket ticket = event.getTicket();

        if(ticket.getMember() != this) return;
        tickets.add(ticket);

        if (window == null) return;
        // Updates the member's window
        window.addNotification("Votre ticket pour " + ticket.getConcert().getName() + " à bien été ajouté");
        window.removeNotification("Votre ticket pour " + ticket.getConcert().getName() + " à bien été supprimé");
        window.updateConcerts();
        window.updateTickets();
    }

    @Override
    public void onAnnulation(TicketEvent event) {
        Ticket ticket = event.getTicket();

        if(ticket.getMember() != this) return;
        tickets.remove(ticket);

        if (window == null) return;
        // Updates the member's window
        window.removeNotification("Votre ticket pour " + ticket.getConcert().getName() + " à bien été ajouté");
        window.addNotification("Votre ticket pour " + ticket.getConcert().getName() + " à bien été supprimé");
        window.updateTickets();
        window.updateConcerts();
    }

    public boolean hasThresholdFor(Concert concert) {
        return this.getPriceThreshold() >= concert.getTicketPrice();
    }

    public boolean hasNoTickets() { return this.getTickets().isEmpty(); }

    public double getPriceThreshold() {
        return priceThreshold;
    }

    public void book(Concert concert) throws MemberAlreadyBookedException, FullConcertException {
        // Test if member already booked
        if(hasBooked(concert)) throw new MemberAlreadyBookedException(concert, this);

        // Test if concert is full
        if(concert.isFull()) throw new FullConcertException(concert);

        // Books a place from the room
        concert.decrementFreePlaces();

        System.out.println(getNameFormat() + " booked for " + concert.getName());

        Ticket ticket = new Ticket(concert, this);
        TicketEvent event = new TicketEvent(this, ticket);
        for (ITicketListener listener : ticketListeners) {
            listener.onReservation(event);
        }
    }

    public void unbook(Ticket ticket) {
        if(!tickets.contains(ticket)) throw new UnknownTicketException(ticket.getConcert(), this);

        // Frees a place from the room
        ticket.getConcert().incrementFreePlaces();

        System.out.println(getNameFormat() + " unbooked for " + ticket.getConcert().getName());

        this.tickets.remove(ticket);
        TicketEvent event = new TicketEvent(this, ticket);
        for (ITicketListener listener : ticketListeners) {
            listener.onAnnulation(event);
        }
    }

    public boolean hasBooked(Concert concert) {
        ArrayList<Concert> concerts = new ArrayList<>();
        for (Ticket ticket: getTickets()) {
            concerts.add(ticket.getConcert());
        }
        return concerts.contains(concert);
    }

    public void setWindow(MemberPage _window) { window = _window;}

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    public String getName() { return name; }

    public String getNameFormat() {
        if (name == null || name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
