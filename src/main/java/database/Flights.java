package database;

import entity.City;
import entity.Timetable;
import io.IOCity;
import io.IOTimetable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Flights {
    private List<City> cities = new IOCity().read();
    private final List<Timetable> data = Arrays.asList(
            new Timetable(1, cities.get(0),cities.get(1), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(2, cities.get(1),cities.get(2), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(3, cities.get(2),cities.get(3), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(4, cities.get(3),cities.get(4), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(5, cities.get(4),cities.get(5), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(6, cities.get(5),cities.get(6), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(7, cities.get(6),cities.get(7), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(8, cities.get(7),cities.get(8), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(9, cities.get(8), cities.get(9), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(10, cities.get(9), cities.get(10), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(11, cities.get(10), cities.get(11), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(12, cities.get(11), cities.get(12), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(13, cities.get(12), cities.get(13), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(14, cities.get(13), cities.get(14), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(15, cities.get(14), cities.get(15), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(16, cities.get(15), cities.get(16), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(17, cities.get(16), cities.get(17), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(18, cities.get(17), cities.get(18), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(19, cities.get(18), cities.get(19), 50, 50, LocalDateTime.parse("2019-11-11T00:00")),
            new Timetable(20, cities.get(19), cities.get(0), 50, 50, LocalDateTime.parse("2019-11-11T00:00")));

    public Flights() throws IOException {
    }

    public void create() {
        IOTimetable io=new IOTimetable();
        int k = 0;
        for (int n = 0; n < 15; n++) {
            for (Timetable flight : data) {
                try {
                    flight.setDate(flight.getDate().plusHours((int) (Math.random() * 100)));
                    flight.setId(k);
                    k += 1;
                    io.write(flight);
                    flight.setDate(flight.getDate().plusDays((int) (Math.random() * 50)));
                    flight.setId(k);
                    k += 1;
                    io.write(flight);
                    flight.setDate(flight.getDate().plusMonths((int) (Math.random() * 5)));
                    flight.setId(k);
                    k += 1;
                    io.write(flight);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
