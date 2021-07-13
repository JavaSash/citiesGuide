import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static String PATH = ".\\city_ru.csv";

    public static void main(String[] args) {
        System.out.println(createCities(readFile(PATH)));
    }

    private static List<City> createCities(List<String> rawCities) {
        List<City> cities = new ArrayList<>();
        for (String cityStr : rawCities) {
            String[] cityParams = cityStr.split(";");
            if (cityParams.length == 6) {
                cities.add(new City(cityParams[1], cityParams[2], cityParams[3], Integer.parseInt(cityParams[4]), cityParams[5]));
            } else {
                cities.add(new City(cityParams[1], cityParams[2], cityParams[3], Integer.parseInt(cityParams[4])));
            }
        }
        return cities;
    }

    private static List<String> readFile(String path) {
        List<String> rawCities = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\r");
            while (sc.hasNext()) {
                rawCities.add(sc.next());
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return rawCities;
    }


}
