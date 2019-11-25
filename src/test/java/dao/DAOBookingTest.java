package dao;

import entity.Booking;
import entity.City;
import entity.Human;
import entity.Timetable;
import io.IOBooking;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DAOBookingTest {

    private DAOBooking daoBooking;

    @Before
    public void before() throws IOException {
        IOBooking ioBooking = new IOBooking();
        daoBooking = new DAOBooking(ioBooking);
        daoBooking.load();
    }

    @Test
    public void get() {
        assertEquals(LocalDateTime.parse("2019-11-19T09:17:10"), daoBooking.get(2).getDate());
    }

    @Test
    public void getAll() {
        assertEquals(2, daoBooking.getAll().size());
    }

    @Test
    public void put() {
        City city = new City(1, "Oslo", "Norway");
        City city1 = new City(2, "Bern", "Switzerland");
        Timetable timetable = new Timetable(1, city, city1, 50, 50, LocalDateTime.parse("2019-11-11T00:00"));
        List<Human> passengers = new ArrayList<>();
        passengers.add(new Human("ziya", "mammadov"));
        Booking booked = new Booking(3, timetable, passengers.get(0), passengers, LocalDateTime.now());
        daoBooking.put(booked);
        assertEquals(3, daoBooking.getAll().size());
    }

    @Test
    public void delete() {
        City city = new City(1, "Oslo", "Norway");
        City city1 = new City(2, "Bern", "Switzerland");
        Timetable timetable = new Timetable(1, city, city1, 50, 50, LocalDateTime.parse("2019-11-11T00:00"));
        List<Human> passengers = new ArrayList<>();
        passengers.add(new Human("ziya", "mammadov"));
        Booking booked = new Booking(3, timetable, passengers.get(0), passengers, LocalDateTime.now());
        daoBooking.put(booked);
        daoBooking.delete(2);
        assertEquals(2, daoBooking.getAll().size());
    }
}
