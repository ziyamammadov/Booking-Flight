package io;

import entity.City;
import entity.Timetable;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IOTimetable {
    private final String path = "data/flights.txt";
    private final File file = new File(path);
    private List<Timetable> timetables;

    public List<Timetable> read() throws IOException {
        if (timetables == null) {
            timetables = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                timetables.add(defineFlight(line));
            }
            br.close();
        }
        return timetables;
    }

    public void write(Timetable timeTable) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw;
        bw = new BufferedWriter(fw);
        bw.write(timeTable.getId() + " : " + timeTable.getSource().getName() + " : " + timeTable.getDestination().getName() + " : " + timeTable.getSeats() + " : " + timeTable.getEmptySeats() + " : " + timeTable.getDate());
        bw.newLine();
        bw.close();
    }

    private Timetable defineFlight(String line) throws IOException {
        IOCity ioCity = new IOCity();
        List<City> cities = ioCity.read();
        String[] split = line.split(" : ");
        int id = Integer.parseInt(split[0]);
        String citySource = split[1];
        String cityDes = split[2];
        int seats = Integer.parseInt(split[3]);
        int emptySeats = Integer.parseInt(split[4]);
        LocalDateTime date = LocalDateTime.parse(split[5]);
        City source = null;
        City destination = null;
        for (City city : cities) {
            if (city.getName().equals(citySource)) {
                source = city;
            }
            if (city.getName().equals(cityDes)) {
                destination = city;
            }
        }
        return new Timetable(id, source, destination, seats, emptySeats, date);
    }

    public void updateFile(List<Timetable> timetables) throws IOException {
        deleteFile(file);
        for (Timetable item : timetables) {
            write(item);
        }
    }

    private void deleteFile(File file) {
        file.delete();
    }
}

