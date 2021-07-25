package citiesGuideSber.util;

import citiesGuideSber.model.City;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
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

    //Сортировка по названию города через компаратор без учёта регистра
    public static void sortByNameComparator(List<City> cities) {
        cities.sort(new Comparator<>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    //Сортировка по названию города через лямбда без учёта регистра
    public static void sortByNameLambda(List<City> cities) {
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }

    //Сортировка по федеральному округу через компаратор с учётом регистра
    public static void sortByDistrictAndNameComparator(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    //Поиск города с наибольшим населением путём простого перебора
    public static String findBySimpleBruteForce(List<City> cities) {
        City[] citiesArray = cities.toArray(new City[0]);
        City maxPopulation = citiesArray[0];
        int index = 0;

        for (int i = 0; i < citiesArray.length - 1; i++) {
            if (citiesArray[i].getPopulation() > maxPopulation.getPopulation()) {
                maxPopulation = citiesArray[i];
                index = i;
            }
        }
        return MessageFormat.format("[{0}] = {1}", index, citiesArray[index].getPopulation());
    }

    //Поиск города с наибольшим количеством жителей путем сортировки вставками
    public static String findByInsertionSort(List<City> cities) {
        City[] citiesArray = cities.toArray(new City[0]);

        for (int i = 1; i < citiesArray.length; i++) {
            City current = citiesArray[i];
            int j = i - 1;
            while (j >= 0 && current.getPopulation() < citiesArray[j].getPopulation()) {
                citiesArray[j + 1] = citiesArray[j];
                j--;
            }
        }
        return MessageFormat.format("[{0}] = {1}", citiesArray.length - 1, citiesArray[citiesArray.length - 1].getPopulation());
    }

//    Поиск города с наибольшим количеством жителей через lambda
    public static void findMaxPopulation(List<City> cities) {
        System.out.println(cities.stream().max(Comparator.comparing(City::getPopulation)));
    }
}