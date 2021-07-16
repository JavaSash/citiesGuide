package citiesGuideSber;
import citiesGuideSber.model.City;

import java.util.List;

import static citiesGuideSber.util.CityUtils.*;


public class Main {
    public static void main(String[] args) {
        List<City> cities = createCities();
        System.out.println("***ДО СОРТИРОВКИ***\n\n" + cities);

        sortByNameComparator(cities);
        System.out.println("\n\n\n***ПОСЛЕ СОРТИРОВКИ ПО ИМЕНИ (компаратор)***\n\n" + cities);

        sortByNameLambda(cities);
        System.out.println("\n\n\n***ПОСЛЕ СОРТИРОВКИ ПО ИМЕНИ (лямбда)***\n\n" + cities);

        sortByDistrictAndNameComparator(cities);
        System.out.println("\n\n\n***ПОСЛЕ СОРТИРОВКИ ПО ФО И ИМЕНИ***\n\n" + cities);
    }
}
