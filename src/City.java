import java.util.Objects;

public class City {
    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    public City(String name, String region, String district, int population, String foundation) {
        Objects.requireNonNull(name, "Name of city must not be null");
        Objects.requireNonNull(region, "Region of city must not be null");
        Objects.requireNonNull(district, "District of city must not be null");
        Objects.requireNonNull(population, "Population of city must not be null");
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    public City(String name, String region, String district, int population) {
        this(name, region, district, population, "");
    }

    @Override
    public String toString() {
        return "\rCity{name='" + name +
                "', region='" + region +
                "', district='" + district +
                "', population=" + population +
                ", foundation='" + foundation + "'}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return population == city.population &&
                name.equals(city.name) &&
                region.equals(city.region) &&
                district.equals(city.district) &&
                foundation.equals(city.foundation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, district, population, foundation);
    }
}
