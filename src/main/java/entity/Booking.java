package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Booking {
    private final int id;
    private final Timetable timeTable;
    private final Human buyer;
    private List<Human> passengers;
    private LocalDateTime date;

    public Booking(int id, Timetable timeTable, Human buyer, List<Human> passengers, LocalDateTime date) {
        this.id = id;
        this.timeTable = timeTable;
        this.buyer = buyer;
        this.passengers = passengers;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Timetable getTimeTable() {
        return timeTable;
    }

    public Human getBuyer() {
        return buyer;
    }

    public List<Human> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Human> passengers) {
        this.passengers = passengers;
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
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return getId() == booking.getId() &&
                Objects.equals(getTimeTable(), booking.getTimeTable()) &&
                Objects.equals(getBuyer(), booking.getBuyer()) &&
                Objects.equals(getPassengers(), booking.getPassengers()) &&
                Objects.equals(getDate(), booking.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTimeTable(), getBuyer(), getPassengers(), getDate());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", timeTable=" + timeTable +
                ", buyer=" + buyer +
                ", passengers=" + passengers +
                ", date=" + date +
                '}';
    }
}
