package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Timetable {
    private final City source;
    private final City destination;
    private int id;
    private final int seats;
    private int emptySeats;
    private LocalDateTime date;

    public Timetable(int id, City source, City destination, int seats, int emptySeats, LocalDateTime date) {
        this.source = source;
        this.destination = destination;
        this.id = id;
        this.seats = seats;
        this.emptySeats = emptySeats;
        this.date = date;
    }

    public City getSource() {
        return source;
    }

    public City getDestination() {
        return destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public int getEmptySeats() {
        return emptySeats;
    }

    public void setEmptySeats(int emptySeats) {
        this.emptySeats = emptySeats;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timetable)) return false;
        Timetable timeTable = (Timetable) o;
        return getId() == timeTable.getId() &&
                getSeats() == timeTable.getSeats() &&
                getEmptySeats() == timeTable.getEmptySeats() &&
                Objects.equals(getSource(), timeTable.getSource()) &&
                Objects.equals(getDestination(), timeTable.getDestination()) &&
                Objects.equals(getDate(), timeTable.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getDestination(), getId(), getSeats(), getEmptySeats(), getDate());
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "source=" + source +
                ", destination=" + destination +
                ", id=" + id +
                ", seats=" + seats +
                ", emptySeats=" + emptySeats +
                ", date=" + date +
                '}';
    }
}
