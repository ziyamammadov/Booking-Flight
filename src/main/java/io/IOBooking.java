package io;

import entity.Booking;
import entity.Human;
import entity.Timetable;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOBooking {
    private final String path = "data/bookings.txt";
    private final File file = new File(path);
    private List<Booking> bookings;

    public List<Booking> read() throws IOException {
        if (bookings == null) {
            if (!file.exists()) {
                file.createNewFile();
            }
            bookings = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                bookings.add(defineBooking(line));
            }
            br.close();
        }
        return bookings;
    }

    public void write(Booking booking) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        StringBuilder sb = new StringBuilder();
        StringBuilder passengers = new StringBuilder();
        for (Human p : booking.getPassengers()) {
            passengers.append(p.getName()).append(" ").append(p.getSurname()).append("/");
        }
        sb.append(booking.getId()).append(" : ").append(booking.getTimeTable().getSource().getName()).append(" : ").append(booking.getTimeTable().getDestination().getName()).append(" : ").append(passengers).append(" : ").append(booking.getDate());
        bw.write(sb.toString());
        bw.newLine();
        bw.close();
    }

    private Booking defineBooking(String line) throws IOException {
        String[] split = line.split(" : ");
        int bookingId = Integer.parseInt(split[0]);
        String sourceName = split[1];
        String destinationName = split[2];
        List<Human> passengers = new ArrayList<>();
        Arrays.stream(split[3].split("/")).forEach(s -> {
            String name = s.split(" ")[0];
            String surname = s.split(" ")[1];
            passengers.add(new Human(name, surname));
        });
        String date_s = split[4];
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime date = LocalDateTime.parse(date_s, formatter);
        List<Timetable> timetables = new IOTimetable().read();
        Timetable timeTable = null;
        for (Timetable flight : timetables) {
            if (sourceName.equalsIgnoreCase(flight.getSource().getName()) && destinationName.equalsIgnoreCase(flight.getDestination().getName())) {
                timeTable = flight;
            }
        }
        return new Booking(bookingId, timeTable, passengers.get(0), passengers, date);
    }

    public void updateFile(List<Booking> bookings) throws IOException {
        deleteFile(file);

        for (Booking item : bookings) {
            write(item);
        }
    }

    private void deleteFile(File file) {
        file.delete();
    }
}
