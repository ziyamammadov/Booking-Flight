import database.Cities;
import database.Flights;

import java.io.File;
import java.io.IOException;

public class Database {
    public boolean isExisted() {
        File a = new File("data/cities.txt");
        File b = new File("data/flights.txt");
        return a.exists() && b.exists();
    }

    public void createInitialData() throws IOException {
        if (!isExisted()) {
            new Cities().create();
            new Flights().create();
        }
    }
}
