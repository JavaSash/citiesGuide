package citiesGuideSber.util;

import citiesGuideSber.model.City;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CityUtils {
    //Загрузка данных о городах в массив
    public static List<City> createCities() {
        List<City> cities = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(".\\city_ru.csv"))) {
            while (sc.hasNextLine()) {
                cities.add(parse(sc.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    //Разбор строки с данными о городе
    private static City parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d*");

        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = null;

        if (scanner.hasNext()) {
            foundation = scanner.next();
        }
        scanner.close();

        return new City(name, region, district, population, foundation);
    }

    //Сортировка по названию города
    public static List<City> sortByName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getName));
        return cities;
    }

    //Сортировка по федеральному округу
    public static List<City> sortByDistrict(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        return cities;
    }
}
