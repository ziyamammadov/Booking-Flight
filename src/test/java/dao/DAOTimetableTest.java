package dao;

import entity.City;
import entity.Timetable;
import io.IOTimetable;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;


public class DAOTimetableTest {
    private DAOTimetable daoTimetable;

    @Before
    public void before() throws IOException {
        IOTimetable ioTimetable = new IOTimetable();
        daoTimetable = new DAOTimetable(ioTimetable);
        daoTimetable.load();
    }
    @Test
    public void get() {
        City city = new City(1, "Oslo", "Norway");
        City city1 = new City(2, "Bern", "Switzerland");
        Timetable timetable = new Timetable(0, city, city1, 50, 50, LocalDateTime.parse("2019-11-11T19:00"));
        assertEquals(timetable,daoTimetable.get(0));
    }

    @Test
    public void getAll() {
        assertEquals(900,daoTimetable.getAll().size());
    }

    @Test
    public void put() {
        City city = new City(1, "Oslo", "Norway");
        City city1 = new City(2, "Bern", "Switzerland");
        Timetable timetable = new Timetable(901, city, city1, 50, 50, LocalDateTime.parse("2019-11-11T19:00"));
        daoTimetable.put(timetable);
        assertEquals(901,daoTimetable.getAll().size());
    }

    @Test
    public void delete() {
        daoTimetable.delete(0);
        assertEquals(899,daoTimetable.getAll().size());
    }
}
