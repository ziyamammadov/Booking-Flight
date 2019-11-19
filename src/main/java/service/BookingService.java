package service;

import console.Console;
import dao.DAOBooking;
import dao.DAOTimetable;
import entity.Booking;
import entity.ChosenFlight;
import entity.Human;
import entity.Timetable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BookingService {
    private final Console console;
    private final DAOBooking daoBooking;
    private final DAOTimetable daoTimetable;

    public BookingService(Console console, DAOBooking daoBooking, DAOTimetable daoTimetable) {
        this.console = console;
        this.daoBooking = daoBooking;
        this.daoTimetable = daoTimetable;
    }

    public void add(ChosenFlight booking) {
        List<Human> passengers = new ArrayList<>(1);
        int numOfTickets = booking.getNumOfTickets();
        Timetable flight = booking.getSearched().get(0);
        console.printLn("ENTER PASSENGERS NAME AND SURNAME");
        for (int i = 0; i < numOfTickets; i++) {
            console.printLn((i + 1) + ". PASSENGER NAME");
            String name = console.readLn().trim();
            console.printLn((i + 1) + ". PASSENGER SURNAME");
            String surname = console.readLn().trim();
            passengers.add(new Human(name, surname));
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        now = LocalDateTime.parse(formatDateTime, formatter);
        List<Booking> all = daoBooking.getAll();
        int id;
        try {
            id = all.get(all.size() - 1).getId();
        } catch (Exception e) {
            id = 0;
        }
        Booking booked = new Booking(id + 1, flight, passengers.get(0), passengers, now);
        daoBooking.put(booked);
    }

    public void delete(int id) {
        boolean isDeleted = false;
        for (int i = 0; i < daoBooking.getAll().size(); i++) {
            if (id == daoBooking.getAll().get(i).getId()) {
                int seats = daoBooking.getAll().get(i).getTimeTable().getEmptySeats() + daoBooking.getAll().get(i).getPassengers().size();
                Timetable flight = daoBooking.getAll().get(i).getTimeTable();
                flight.setEmptySeats(seats);
                daoTimetable.update(flight);
                daoBooking.delete(i);
                System.out.println("YOUR BOOKING SUCCESSFULLY CANCELED");
                isDeleted = true;
                break;
            }
        }
        if (!isDeleted) {
            System.out.println("NO BOOKING AT THIS ID!");
        }

    }

    public void show(String name, String surname) {
        List<Booking> all = daoBooking.getAll();
        boolean isShowed = false;
        boolean f = false;
        for (Booking booking : all) {
            if (name.equalsIgnoreCase(booking.getBuyer().getName()) && surname.equalsIgnoreCase(booking.getBuyer().getSurname())) {
                if (!isShowed) {
                    printHeader(booking);
                    isShowed=true;
                }
                printBooking(booking);
                f = true;
            }
        }
        if (!f) {
            console.printLn("THERE IS NO BOOKING AT YOUR NAME!");
        }
    }

    private void printBooking(Booking booking) {
        String s = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------|";
        StringJoiner sj = new StringJoiner(",");
        booking.getPassengers().forEach(passenger -> sj.add(passenger.getName().toUpperCase() + " " + passenger.getSurname().toUpperCase()));
        console.printLn("|" + pad(booking.getTimeTable().getSource().getName(), 19)
                + pad(booking.getTimeTable().getDestination().getName(), 20)
                + pad(booking.getTimeTable().getDate() + "", 20)
                + pad(booking.getDate() + "", 22)
                + pad(sj.toString(), 77));
        console.printLn(s);
    }

    private String pad(String str, int n) {
        return String.format("%-" + n + "s" + "|", str);
    }

    private void printHeader(Booking booking) {
        String s = "===================================================================================================================================================================";
        System.out.println(s);
        System.out.println(pad("|                                  " + booking.getBuyer().getName().toUpperCase() + " " + booking.getBuyer().getSurname().toUpperCase() + "'S BOOKING", 163));
        System.out.println(s + "|");
        System.out.println("|" + pad("SOURCE CITY", 19) + pad("DESTINATION CITY", 20) + pad("FLIGHT DATE", 20) + pad("BOOKING DATE", 22) + pad("PASSENGERS", 77));
        System.out.println(s + "|");
    }

    public void updateFile() throws IOException {
        daoBooking.updateFile();
    }

    public void load() throws IOException {
        daoBooking.load();
    }
}
