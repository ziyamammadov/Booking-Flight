package controller;

import console.Console;
import service.BookingService;

import java.io.IOException;

public class BookingController {
    private final BookingService bookingService;
    private final Console console;

    public BookingController(Console console, BookingService bookingService) {
        this.console=console;
        this.bookingService=bookingService;
    }

    public void cancel(){
        console.printLn("WRITE YOUR BOOKING ID TO CANCEL:");
        String line;
        int id;
        while (true) {
            try {
                line = console.readLn();
                id = Integer.parseInt(line);
                break;
            } catch (Exception e) {
                console.printLn("PLEASE, ENTER VALID ID: ");
            }
        }
        bookingService.delete(id);
    }

    public void show() {
        console.printLn("ENTER YOUR NAME:");
        String name = console.readLn();
        console.printLn("ENTER YOUR SURNAME:");
        String surname = console.readLn();
        bookingService.show(name.trim(), surname.trim());
    }

    public void updateFile() throws IOException {
        bookingService.updateFile();
    }
    public void load() throws IOException {
        bookingService.load();
    }
}
