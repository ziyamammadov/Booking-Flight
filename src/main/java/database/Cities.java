package database;

import entity.City;
import io.IOCity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Cities {
    private final List<City> data = Arrays.asList(
            new City(1, "Oslo", "Norway"),
            new City(2, "Bern", "Switzerland"),
            new City(3, "Canberra", "Australia"),
            new City(4, "Dublin", "Ireland"),
            new City(5, "Berlin", "Germany"),
            new City(6, "Reykjavik", "Iceland"),
            new City(7, "Beijing", "China"),
            new City(8, "Stockholm", "Sweden"),
            new City(9, "Copenhagen", "Denmark"),
            new City(10, "Ottawa", "Canada"),
            new City(11, "Washington", "United States"),
            new City(12, "London", "United Kingdom"),
            new City(13, "Helsinki", "Finland"),
            new City(14, "Roma", "Italy"),
            new City(15, "Brussels", "Belgium"),
            new City(16, "Tokyo", "Japan"),
            new City(17, "Vienna", "Austria"),
            new City(18, "Paris", "France"),
            new City(19, "Tallinn", "Estonia"),
            new City(20, "Nicosia", "Cyprus"));

    public void create() {
        IOCity io = new IOCity();
        data.forEach(city -> {
            try {
                io.write(city);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
