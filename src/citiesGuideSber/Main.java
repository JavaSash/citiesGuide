package citiesGuideSber;
import citiesGuideSber.model.City;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static citiesGuideSber.util.CityUtils.*;


public class Main {
    public static void main(String[] args) {
        List<City> cities = createCities();
        System.out.println("***ДО СОРТИРОВКИ***\n\n" + cities);
        System.out.println("\n\n\n***ПОСЛЕ СОРТИРОВКИ ПО ИМЕНИ***\n\n" + sortByName(cities));

        System.out.println("\n\n\n***ПОСЛЕ СОРТИРОВКИ ПО ФО И ИМЕНИ***\n\n" + sortByDistrict(cities));
    }
}
