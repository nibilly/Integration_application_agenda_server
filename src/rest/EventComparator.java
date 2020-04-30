package rest;

import object.Event;

import java.util.Comparator;

class EventComparator implements Comparator<Event>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Event a, Event b)
    {
        return a.getJourDate().compareTo(b.getJourDate());
    }
}
