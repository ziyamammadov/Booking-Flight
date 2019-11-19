package io;

import entity.City;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOCity {
    private final String path = "data/cities.txt";
    private final File file = new File(path);
    private List<City> cities;

    public List<City> read() throws IOException {
        if (cities == null) {
            cities = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                cities.add(defineCity(line));
            }
            br.close();
        }
        return cities;
    }

    public void write(City city) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(city.getId() + " : " + city.getName() + " : " + city.getCountryName());
        bw.newLine();
        bw.close();
    }

    private City defineCity(String line) {
        int id = Integer.parseInt(line.split(" : ")[0]);//add try catch if u need
        String name = line.split(" : ")[1];
        String countryName = line.split(" : ")[2];
        return new City(id, name, countryName);
    }
}
