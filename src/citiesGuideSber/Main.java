package citiesGuideSber;

import citiesGuideSber.model.City;

import java.util.List;

import static citiesGuideSber.util.CityUtils.*;


public class Main {
    public static void main(String[] args) {
        List<City> cities = createCities();
        System.out.println("***ДО СОРТИРОВКИ***\n\n" + cities);

        sortByNameComparator(cities);
        System.out.println("\n\n***ПОСЛЕ СОРТИРОВКИ ПО ИМЕНИ (компаратор)***\n\n" + cities);

        sortByNameLambda(cities);
        System.out.println("\n\n***ПОСЛЕ СОРТИРОВКИ ПО ИМЕНИ (лямбда)***\n\n" + cities);

        sortByDistrictAndNameComparator(cities);
        System.out.println("\n\n***ПОСЛЕ СОРТИРОВКИ ПО ФО И ИМЕНИ***\n\n" + cities);

        System.out.println("\n\n***ГОРОД С САМЫМ БОЛЬШИМ НАСЕЛЕНИЕМ (перебором)***\n\n" + findBySimpleBruteForce(cities));
        System.out.println("\n\n***ГОРОД С САМЫМ БОЛЬШИМ НАСЕЛЕНИЕМ (вставками)***\n\n" + findByInsertionSort(cities));
        System.out.println("\n\n***ГОРОД С САМЫМ БОЛЬШИМ НАСЕЛЕНИЕМ (лямбда)***");
        findMaxPopulation(cities);

        System.out.println("\n\n***КОЛИЧЕСТВО ГОРОДОВ В РЕГИОНЕ***\n");
        numberOfCities(cities);

        System.out.println("\n\n***КОЛИЧЕСТВО ГОРОДОВ В РЕГИОНЕ (потоки)***\n");
        numberOfCitiesByStream(cities);
    }
}
