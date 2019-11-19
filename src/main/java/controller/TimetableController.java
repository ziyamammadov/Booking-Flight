package controller;

import console.Console;
import entity.ChosenFlight;
import entity.Timetable;
import service.BookingService;
import service.TimetableService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TimetableController {
    private final TimetableService timetableService;
    private final BookingService bookingService;
    private final Console console;

    public TimetableController(Console console, TimetableService timetableService, BookingService bookingService) {
        this.bookingService=bookingService;
        this.timetableService = timetableService;
        this.console = console;
    }

    public void show() {
        try {
            console.printLn("PLEASE ENTER THE DATE WHICH YOU WANNA SEE FLIGHTS: (yyyy.MM.dd)");
            LocalDateTime fromDateTime = LocalDateTime.now();
            String endDate = console.readLn().trim();
            int year = Integer.parseInt(endDate.split("\\.")[0]);
            int month = Integer.parseInt(endDate.split("\\.")[1]);
            int day = Integer.parseInt(endDate.split("\\.")[2]);
            LocalDateTime toDateTime = LocalDateTime.of(year, month, day, 23, 59, 59);
            timetableService.show(fromDateTime, toDateTime);
        } catch (Exception e) {
            console.printLn("WRONG INPUT!");
        }
    }

    public void showLine() {
        console.printLn("ENTER FLIGHT ID");
        String line;
        int id;
        while (true) {
            try {
                line = console.readLn().trim();
                id = Integer.parseInt(line);
                break;
            } catch (Exception e) {
                console.printLn("PLEASE, ENTER AN INTEGER : ");
            }
        }
        timetableService.showLine(id);
    }

    public void search() {
        console.printLn("ENTER 'SOURCE CITY' NAME:");
        String fromCityName = console.readLn();
        console.printLn("ENTER 'DESTINATION CITY' NAME:");
        String toCityName = console.readLn();
        console.printLn("ENTER FLIGHT DATE: (Example: yyyy.MM.dd)");
        String date = console.readLn();
        console.printLn("ENTER NUMBER OF TICKETS TO BUY:");
        String nTickets = console.readLn();
        List<Timetable> searched = timetableService.search(fromCityName.trim(), toCityName.trim(), date.trim(), nTickets.trim());
        if (searched != null) {
            chosenFlight(nTickets, searched);
        }
    }

    private void chosenFlight(String numOfTickets, List<Timetable> searched)  {
        console.printLn("PLEASE ENTER A FLIGHT ID TO BOOK FLIGHT: ");
        String in = console.readLn();
        int flightId = checkInputIsInteger(in);
        for (Timetable flight : searched) {
            if (flight.getId() != flightId) {
                searched.remove(flight);
            }
        }
        int seats = searched.get(0).getEmptySeats() - Integer.parseInt(numOfTickets);
        searched.get(0).setEmptySeats(seats);
        timetableService.getDaoTimetable().update(searched.get(0));
        int n = checkInputIsInteger(numOfTickets);
        bookingService.add(new ChosenFlight(n,searched));
    }

    private int checkInputIsInteger(String input) {
        int id ;
        while (true) {
            try {
                id = Integer.parseInt(input);
                break;
            } catch (Exception e) {
                console.printLn("Please, enter an right integer : ");
                input = console.readLn();
            }
        }
        return id;
    }

    public void updateFile() throws IOException {
        timetableService.updateFile();
    }

    public void load() throws IOException {
        timetableService.load();
    }
}
