package rest;

import object.Event;
import object.User;
import object.UserManagement;

import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

// http://localhost:8080/Agenda/api/user/*
@Path("/user")
public class AgendaRest {
    private int getUserId(String firstName, String lastName){
        int res = -1;
        UserManagement userManagement = new UserManagement();
        User user = userManagement.getUsers().stream()
                .filter(user1 -> firstName.equals(user1.getFirstName()) && lastName.equals(user1.getLastName()))
                .findAny()
                .orElse(null);
        if (user != null){
            res = user.getId();
        }
        return res;
    }

    private String eventToString(Event event){
        return event.getName() + " (id : " + event.getId() + ") : Commence le " +
                event.getStartDate() + " et fini le " + event.getFinishDate() + "<br>";
    }

    private String eventsToString(List<Event> events) {
        StringBuilder res = new StringBuilder();
        for (Event event: events) {
            res.append(eventToString(event));
        }
        return res.toString();
    }

    @GET
    public String getUser(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName){
        return getUserId(firstName, lastName) + "";
    }

    @POST
    public String postUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName){
        if(getUserId(firstName, lastName) == -1) {
            UserManagement.createUser(firstName, lastName);
        }
        return "user created";
    }

    @DELETE
    @Path("{userId}")
    public String deleteUser(@PathParam("userId") int userId){
        UserManagement.deleteUser(userId);
        return "User deleted";
    }

    @GET
    @Path("{userId}/event")
    public String getEvents(@PathParam("userId") int userId){
        StringBuilder stringBuilder = new StringBuilder();
        List<Event> events = UserManagement.getUserById(userId).getMonAgenda().getEvents();
        events.sort(new EventComparator());
        if(events.size() != 0) {
            for (Event event : events) {
                stringBuilder.append("<tr><td class=\"agenda-date\" class=\"active\" rowspan=\"1\">");
                stringBuilder.append("<div class=\"dayofmonth\">"+event.getJourDate().substring(0,2)+"</div>");
                stringBuilder.append("<div class=\"dayofweek\">" + event.getJourDate().substring(2) + "</div></td>");
                stringBuilder.append("<td class=\"agenda-time\">" + event.getHeureDebut() + " - " + event.getHeureFin() + "</td>");
                stringBuilder.append("<td class=\"agenda-events\"><div class=\"agenda-event\">(id : " + event.getId() + ") " + event.getName() + "</td></tr>");
            }
        }
        return stringBuilder.toString();
    }

    @POST
    @Path("{userId}/event")
    public String postEvent(@PathParam("userId") int userId, @FormParam("startDate") String startDate,
                            @FormParam("finishDate") String finishDate, @FormParam("name") String name){
        if(startDate.length()==16) {
            startDate = startDate.toUpperCase();
            String jourDate = startDate.substring(0, 10);
            String heureDebut = startDate.substring(11, 16);
            String heureFin = finishDate.substring(11, 16);
            UserManagement.getUserById(userId).getMonAgenda().newEvent(new Event(startDate, finishDate, jourDate,
                    heureDebut, heureFin, name));
            }
        return "event created";
    }

    @GET
    @Path("{userId}/event/day")
    @Produces("text/html")
    public String getEventsByDay(@PathParam("userId") int userId, @QueryParam("date") String date){
        date = date.toUpperCase();
        List<Event> events = UserManagement.getUserById(userId).getMonAgenda().getEvents();
        date = date.substring(0, 10);
        String finalDate = date;
        events = events.stream().filter(event -> event.getJourDate().equals(finalDate)).collect(Collectors.toList());
        return eventsToString(events);
    }

    @GET
    @Path("event/day")
    @Produces("application/json")
    public String getEventsByDayJson(@QueryParam("date") String date){
        StringBuilder stringBuilder = new StringBuilder();
        date = date.toUpperCase();
        date = date.substring(0, 10);
        String finalDate = date;
        stringBuilder.append("{ \"users\": [ ");
        int cptUser = 0;
        int cptEvent = 0;
        for (User user: UserManagement.listUser){
            String finalDate1 = date;
            List<Event> events = user.getMonAgenda().getEvents().stream().filter(event -> event.getJourDate().compareTo(finalDate1) == 0).collect(Collectors.toList());
            if(!events.isEmpty()) {
                cptEvent = 0;
                if (cptUser++>0) stringBuilder.append(", ");
                stringBuilder.append("{ \"user\": { ");
                stringBuilder.append("\"firstName\": \"").append(user.getFirstName()).append("\", ");
                stringBuilder.append("\"lastName\": \"").append(user.getLastName()).append("\" ");
                stringBuilder.append(" }, ");
                stringBuilder.append("\"events\": [ ");
                for (Event event : events) {
                    cptEvent++;
                    stringBuilder.append("{ \"startDate\": \"").append(event.getStartDate()).append("\", ");
                    stringBuilder.append("\"finishDate\": \"").append(event.getFinishDate()).append("\", ");
                    stringBuilder.append("\"name\": \"").append(event.getName()).append("\" ");
                    stringBuilder.append(" }");
                    if (events.size() != cptEvent) stringBuilder.append(", ");
                }
                stringBuilder.append(" ] } ");
            }
        }
        stringBuilder.append(" ] } ");
        return stringBuilder.toString();
    }

    @DELETE
    @Path("{userId}/event/{eventId}")
    public String deleteEvent(@PathParam("userId") int userId, @PathParam("eventId") int eventId){
        UserManagement.getUserById(userId).getMonAgenda().deleteEvent(eventId);
        return "event deleted";
    }
}
