package dao;

import entity.Timetable;
import io.IOTimetable;

import java.io.IOException;
import java.util.List;

public class DAOTimetable implements DAO<Timetable> {
    private List<Timetable> timetables;
    private final IOTimetable ioTimetable;

    public DAOTimetable(IOTimetable ioTimetable) {
        this.ioTimetable = ioTimetable;
    }

    public void load() throws IOException {
        this.timetables = new IOTimetable().read();
    }

    public Timetable get(int id) {
        return timetables.get(id);
    }

    public List<Timetable> getAll() {
        return timetables;
    }

    public void put(Timetable timeTable) {
        timetables.add(timeTable);
        System.out.println("Successfully added!");
    }

    public void delete(int id) {
        timetables.remove(id);
        System.out.println("Successfully deleted!");
    }

    public void update(Timetable timeTable) {
        timetables.forEach(t -> {
            if (t.getId() == timeTable.getId()) {
                timetables.set(t.getId(), timeTable);
            }
        });
    }

    public void updateFile() throws IOException {
        ioTimetable.updateFile(timetables);
    }
}
