package entity;

import java.util.List;
import java.util.Objects;

public class ChosenFlight {
    private int numOfTickets;
    private List<Timetable> searched;

    public ChosenFlight(int numOfTickets, List<Timetable> searched) {
        this.numOfTickets = numOfTickets;
        this.searched = searched;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }


    public List<Timetable> getSearched() {
        return searched;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChosenFlight)) return false;
        ChosenFlight that = (ChosenFlight) o;
        return getNumOfTickets() == that.getNumOfTickets() &&
                Objects.equals(getSearched(), that.getSearched());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumOfTickets(), getSearched());
    }

    @Override
    public String toString() {
        return "ChosenFlight{" +
                "numOfTickets=" + numOfTickets +
                ", searched=" + searched +
                '}';
    }
}
