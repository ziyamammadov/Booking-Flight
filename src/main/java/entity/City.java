package entity;

import java.util.Objects;

public class City {
    private final int id;
    private final String name;
    private final String countryName;

    public City(int id, String name, String countryName) {
        this.id = id;
        this.name = name;
        this.countryName = countryName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId() &&
                Objects.equals(getName(), city.getName()) &&
                Objects.equals(getCountryName(), city.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCountryName());
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
