package citiesGuideSber.util;

import citiesGuideSber.model.City;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CityUtils {
    private static final Path PATH_WITH_DATA = Paths.get("city_ru.csv").toAbsolutePath();
    private static List<City> cities = createCities();
    private static final String COMMAND_LIST = "СПИСОК КОМАНД";
    private static final String CITIES_LIST = "1. СПИСОК ГОРОДОВ";
    private static final String SORT_BY_NAME_CASE_SENSITIVE = "2. СОРТИРОВКА ПО ИМЕНИ С УЧЁТОМ РЕГИСТРА";
    private static final String SORT_BY_NAME_WITHOUT_CASE_SENSITIVE = "3. СОРТИРОВКА ПО ИМЕНИ БЕЗ УЧЁТА РЕГИСТРА";
    private static final String MAX_POPULATION = "4. ГОРОД С НАИБОЛЬШИМ НАСЕЛЕНИЕМ";
    private static final String NUMBER_OF_CITIES_IN_REGION = "5. КОЛИЧЕСТВО ГОРОДОВ В РЕГИОНЕ";
    private static final String EXIT = "6. ВЫХОД";

    public static final void startMenu() {
        Scanner scan = new Scanner(System.in);
        boolean repeatCycle = true;

        while (repeatCycle) {
            System.out.printf("%100s \n", COMMAND_LIST);
            System.out.printf("|| %s || %s || %s || %s || %s || %s || \n\n", CITIES_LIST, SORT_BY_NAME_CASE_SENSITIVE,
                    SORT_BY_NAME_WITHOUT_CASE_SENSITIVE, MAX_POPULATION, NUMBER_OF_CITIES_IN_REGION, EXIT);
            System.out.print("Введите номер команды: ");
            int command = scan.nextInt();
            switch (command) {
                case 1: printData();
                    break;
                case 2: sortByDistrictAndNameComparator();
                    break;
                case 3: sortByNameLambda();
                    break;
                case 4: findMaxPopulation();
                    break;
                case 5: numberOfCitiesByStream();
                    break;
                case 6: repeatCycle = false;
                    break;
                default: continue;
            }
        }
        scan.close();
    }

    //Загрузка данных о городах в массив
    private static List<City> createCities() {
        List<City> cities = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(String.valueOf(PATH_WITH_DATA)))) {
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

    private static void printData() {
        System.out.println("***ДО СОРТИРОВКИ***\n\n" + cities);
    }

    //Сортировка по названию города через компаратор без учёта регистра
    private static void sortByNameComparator(List<City> cities) {
        List<City> citiesCopy = new ArrayList<>(cities);
        citiesCopy.sort(new Comparator<>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        System.out.println("\n\n***ПОСЛЕ СОРТИРОВКИ ПО ИМЕНИ (компаратор)***\n\n" + citiesCopy);
    }

    //Сортировка по названию города через лямбда без учёта регистра
    private static void sortByNameLambda() {
        List<City> citiesCopy = new ArrayList<>(cities);
        citiesCopy.sort((o1, o2) -> o2.getName().compareToIgnoreCase(o1.getName()));
        System.out.println("\n\n***ПОСЛЕ СОРТИРОВКИ ПО ИМЕНИ (лямбда)***\n\n" + citiesCopy);
    }

    //Сортировка по федеральному округу через компаратор с учётом регистра
    private static void sortByDistrictAndNameComparator() {
        List<City> citiesCopy = new ArrayList<>(cities);
        citiesCopy.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName).reversed());
        System.out.println("\n\n***ПОСЛЕ СОРТИРОВКИ ПО ФО И ИМЕНИ***\n\n" + citiesCopy);
    }

    //Поиск города с наибольшим населением путём простого перебора
    private static void findBySimpleBruteForce() {
        City[] citiesArray = cities.toArray(new City[0]);
        City maxPopulation = citiesArray[0];
        int index = 0;

        for (int i = 0; i < citiesArray.length - 1; i++) {
            if (citiesArray[i].getPopulation() > maxPopulation.getPopulation()) {
                maxPopulation = citiesArray[i];
                index = i;
            }
        }
        System.out.println("\n\n***ГОРОД С САМЫМ БОЛЬШИМ НАСЕЛЕНИЕМ (перебором)***\n\n" +
                MessageFormat.format("[{0}] = {1}", index, citiesArray[index].getPopulation()));
    }

    //Поиск города с наибольшим количеством жителей путем сортировки вставками
    private static void findByInsertionSort() {
        City[] citiesArray = cities.toArray(new City[0]);

        for (int i = 1; i < citiesArray.length; i++) {
            City current = citiesArray[i];
            int j = i - 1;
            while (j >= 0 && current.getPopulation() < citiesArray[j].getPopulation()) {
                citiesArray[j + 1] = citiesArray[j];
                j--;
            }
        }
        System.out.println("\n\n***ГОРОД С САМЫМ БОЛЬШИМ НАСЕЛЕНИЕМ (вставками)***\n\n" +
                MessageFormat.format("[{0}] = {1}", citiesArray.length - 1, citiesArray[citiesArray.length - 1].getPopulation()));
    }

    //Поиск города с наибольшим количеством жителей через lambda
    private static void findMaxPopulation() {
        System.out.println("\n\n***ГОРОД С САМЫМ БОЛЬШИМ НАСЕЛЕНИЕМ (лямбда)***");
        City maxPop = cities.stream().max(Comparator.comparing(City::getPopulation)).get();
        System.out.println(MessageFormat.format("[{0}] = {1}", cities.indexOf(maxPop), maxPop.getPopulation()));
    }

    //Поиск количества городов в регионе
    private static void numberOfCities() {
        Map<String, Integer> regions = new HashMap<>();
        for (City city : cities) {
            if (!regions.containsKey(city.getRegion())) {
                regions.put(city.getRegion(), 1);
            } else {
                regions.put(city.getRegion(), regions.get(city.getRegion()) + 1);
            }
        }
        System.out.println("\n\n***КОЛИЧЕСТВО ГОРОДОВ В РЕГИОНЕ***\n");
        for (String key : regions.keySet()) {
            System.out.println(MessageFormat.format(" {0} = {1}", key, regions.get(key)));
        }
    }

    //Поиск количества городов в регионе через Stream API
    private static void numberOfCitiesByStream() {
        Map<String, Long> regions = cities.stream()
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));
        System.out.println("\n\n***КОЛИЧЕСТВО ГОРОДОВ В РЕГИОНЕ (потоки)***\n");
        regions.forEach((k, v) -> System.out.println(MessageFormat.format(" {0} = {1}", k, v)));
    }
}