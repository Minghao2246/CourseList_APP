package model;

import exception.EmptyListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestEventLog {
    private Event e1;
    private Event e2;
    private Event e3;
    private Event e4;
    private Event e5;
    private CourseList courseList;
    private Course course1;


    @BeforeEach
    public void loadEvents() throws EmptyListException {
        courseList = new CourseList();
        course1 = new Course("MATH", 74,"yay!");
        e1 = new Event("MATH added to the list");
        e2 = new Event("MATH removed to the list");
        e3 = new Event("Average calculated");
        e4 = new Event("Looked up grade for MATH");
        e5 = new Event("Viewed comment for MATH");
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);
        el.logEvent(e3);
        el.logEvent(e4);
        el.logEvent(e5);
    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
        assertTrue(l.contains(e4));
        assertTrue(l.contains(e5));
    }

    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> itr = el.iterator();
        assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
