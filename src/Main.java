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

    private static List<String> readFile(String path) {
        List<String> cities = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter(";|\r");
            while (sc.hasNext()) {
                cities.add(sc.next());
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return cities;
    }

    private static List<City> createCities(List<String> citiesStr) {
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < citiesStr.size(); i += 6) {
            cities.add(new City(citiesStr.get(i + 1), citiesStr.get(i + 2), citiesStr.get(i + 3), citiesStr.get(i + 4), citiesStr.get(i + 5)));
        }
        return cities;
    }
}
