package service;

import console.Console;
import dao.DAOTimetable;
import entity.Timetable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimetableService {
    private final Console console;
    private final DAOTimetable daoTimetable;

    public TimetableService(Console console, DAOTimetable daoTimetable) {
        this.console = console;
        this.daoTimetable = daoTimetable;
    }

    public DAOTimetable getDaoTimetable() {
        return this.daoTimetable;
    }

    public void show(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        try {
            List<Timetable> all = daoTimetable.getAll().stream().filter(flight -> {
                LocalDateTime checkDateTime = flight.getDate();
                return (checkDateTime.compareTo(fromDateTime) >= 0) && (checkDateTime.compareTo(toDateTime) <= 0);
            }).collect(Collectors.toList());
            printFlights(all);
        } catch (Exception e) {
            console.printLn("WRONG INPUT");
        }
    }

    public void showLine(int id) {
        try {
            Timetable flight = daoTimetable.get(id);
            List<Timetable> all = new ArrayList<>();
            LocalDateTime currentTime = LocalDateTime.now();
            if (currentTime.compareTo(flight.getDate()) <= 0) {
                all.add(flight);
                printFlights(all);
            } else {
                console.printLn("THIS FLIGHT IS OUTDATED");
            }
        } catch (Exception e) {
            System.out.println("WRONG INPUT");
        }
    }

    public List<Timetable> search(String fromCityName, String toCityName, String date, String nTickets) {
        try {
            LocalDateTime currentDate = LocalDateTime.now();
            int year = Integer.parseInt(date.split("\\.")[0]);
            int month = Integer.parseInt(date.split("\\.")[1]);
            int day = Integer.parseInt(date.split("\\.")[2]);
            LocalDateTime outDate = LocalDateTime.of(year, month, day, 23, 59, 59);
            if (outDate.compareTo(currentDate) >= 0) {
                List<Timetable> all = daoTimetable.getAll().stream().filter(flight -> {
                    String from = flight.getSource().getName();
                    String to = flight.getDestination().getName();
                    boolean enoughEmptySeats = Integer.parseInt(nTickets) <= flight.getEmptySeats();
                    return enoughEmptySeats
                            && from.equalsIgnoreCase(fromCityName)
                            && to.equalsIgnoreCase(toCityName)
                            && (year == flight.getDate().getYear() && month == flight.getDate().getMonthValue() && day == flight.getDate().getDayOfMonth());
                }).collect(Collectors.toList());
                printFlights(all);
                return all;
            } else {
                console.printLn("THIS FLIGHT IS OUTDATED");
            }
        } catch (Exception e) {
            console.printLn("WRONG INPUT");
        }
        return null;
    }

    private void printFlights(List<Timetable> all) {
        if (all.size() == 0) console.printLn("NO FLIGHTS FOUND");
        else {
            console.printLn(all.size() + " AVAILABLE FLIGHTS FOUND:");
            printHeader();
            for (Timetable flight : all) {
                String s = "--------------------------------------------------------------------------------------|";
                console.printLn("|" + pad("" + flight.getId(), 10) + pad(flight.getSource().getName(), 20)
                        + pad(flight.getDestination().getName(), 20) + pad(flight.getEmptySeats() + "", 15)
                        + pad(flight.getDate() + "", 15));
                console.printLn(s);
            }
        }
    }

    public void updateFile() throws IOException {
        daoTimetable.updateFile();
    }

    private String pad(String str, int n) {
        return String.format("%-" + n + "s" + "|", str);
    }

    private void printHeader() {
        String s = "======================================================================================";
        System.out.println(s);
        System.out.println("|" + pad("ID", 10) + pad("SOURCE CITY", 20) + pad("DESTINATION CITY", 20) + pad("EMPTY SEATS", 15) + pad("DATE", 16));
        System.out.println(s + "|");
    }

    public void load() throws IOException {
        daoTimetable.load();
    }
}
