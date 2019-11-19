import console.Console;
import controller.BookingController;
import controller.TimetableController;
import dao.DAOBooking;
import dao.DAOTimetable;
import io.Command;
import io.IOBooking;
import io.IOTimetable;
import io.Parser;
import service.BookingService;
import service.TimetableService;

import java.io.IOException;

public class Core {
    private final Console console;
    private final Database database;
    private final Menu menu;
    private final Parser parser;
    private final TimetableController timetableController;
    private final BookingController bookingController;

    public Core(Console console, Database database) {
        this.console = console;
        this.database = database;
        this.menu = new Menu();
        this.parser = new Parser();
        IOBooking ioBooking = new IOBooking();
        IOTimetable ioTimetable = new IOTimetable();
        DAOBooking daoBooking = new DAOBooking(ioBooking);
        DAOTimetable daoTimetable = new DAOTimetable(ioTimetable);
        BookingService bookingService = new BookingService(console, daoBooking,daoTimetable);
        TimetableService timetableService = new TimetableService(console, daoTimetable);
        this.timetableController = new TimetableController(console, timetableService,bookingService);
        this.bookingController = new BookingController(console, bookingService);
    }


    public void run() throws IOException {
        if (!database.isExisted()) {
            database.createInitialData();
        }
        bookingController.load();
        timetableController.load();
        boolean cont = true;
        while (cont) {
            console.printLn("\n" + menu.show());
            console.printLn("SELECT A MENU ITEM:");
            String line = console.readLn();
            Command user_input = parser.parse(line);
            switch (user_input) {
                case TIMETABLE_SHOW:
                    timetableController.show();
                    break;
                case TIMETABLE_LINE_SHOW:
                    timetableController.showLine();
                    break;
                case FLIGHT_SEARCH:
                    timetableController.search();
                    break;
                case BOOKING_REMOVE:
                    bookingController.cancel();
                    break;
                case MY_BOOKINGS_SHOW:
                    bookingController.show();
                    break;
                case EXIT:
                    console.printLn("WE ARE HAPPY TO HOST YOU.\nIF YOU HAVE QUESTIONS,\nDO NOT HESITATE TO CONTACT US");
                    cont = false;
                    bookingController.updateFile();
                    timetableController.updateFile();
                    break;
                default:
                    console.printLn("WRONG ITEM SELECTED. SELECT 1-6");
                    break;
            }
        }
    }
}
