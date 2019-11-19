package dao;

import entity.Booking;
import io.IOBooking;

import java.io.IOException;
import java.util.List;

public class DAOBooking implements DAO<Booking> {
    private final IOBooking ioBooking;
    private List<Booking> bookings;

    public DAOBooking(IOBooking ioBooking) {
        this.ioBooking = ioBooking;
    }

    public void load() throws IOException {
        this.bookings = ioBooking.read();
    }

    public Booking get(int id) {
        for (Booking booking : bookings) {
            if (id == booking.getId()) {
                return booking;
            }
        }
        throw new IllegalArgumentException("NO BOOKING AT THIS ID");
    }

    public List<Booking> getAll() {
        return bookings;
    }

    public void put(Booking booking) {
        bookings.add(booking);
        System.out.println("SUCCESSFULLY BOOKED");
    }

    public void delete(int index) {
        bookings.remove(index);
    }

    public void updateFile() throws IOException {
        ioBooking.updateFile(bookings);
    }

}
