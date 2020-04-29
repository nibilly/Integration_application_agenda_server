package object;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    public static int compteurId;
    List<Event> mesEvents;
    User owner;
    int id;

    public Agenda(User owner) {
        this.mesEvents = new ArrayList<Event>();
        this.owner = owner;
        this.id = compteurId;
        compteurId++;
    }

    public void showEvents(){
        System.out.println("Affichage des " + mesEvents.size() + " évènements de " + owner.getFirstName());
        for(int i = 0; i < mesEvents.size(); i++){
            mesEvents.get(i).showDetails();
        }
    }

    public Event getEventById(int id) {
        Event monEvent = new Event();
        for (int i = 0; i < mesEvents.size(); i++) {
            if (mesEvents.get(i).getId() == id) {
                monEvent = mesEvents.get(i);
            }
        }
        return monEvent;
    }

        /*private List<Event> getEventsByDay(Date day) {
        List<Event> eventsOfDay = new ArrayList<>();
        for (int i = 0; i < mesEvents.size(); i++) {
            // Comparer le jour et non pas la date exacte
            if (mesEvents.get(i).getStartDate() == day) {
                eventsOfDay.add(mesEvents.get(i));
            }
        }
        return eventsOfDay;

    }*/



    public void deleteEvent(int eventId) {
        for (int i = 0; i < mesEvents.size(); i++) {
            int id = mesEvents.get(i).getId();
            if (id == eventId) {
                mesEvents.remove(i);
            }
        }
    }

    public void setMesEvents(List<Event> mesEvents) {
        this.mesEvents = mesEvents;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void newEvent(Event event) {
        mesEvents.add(event);
    }

    public List<Event> getEvents() {
        return mesEvents;
    }

}
