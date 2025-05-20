package se.lexicon.model;

/**
 * Represents a City entity based on the 'city' table in the 'world' database.
 */
public class City {

    //fields
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private int population;

    //constructor
    public City(String name, String countryCode, String district, int population) {
        setName(name);
        setCountryCode(countryCode);
        setDistrict(district);
        setPopulation(population);
    }

    public City(int id, String name, String countryCode, String district, int population) {
        this(name, countryCode, district, population);
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name can't be null or empty");
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        if (countryCode == null || countryCode.trim().isEmpty())
            throw new IllegalArgumentException("CountryCode can't be null or empty");
        this.countryCode = countryCode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPopulation(int population) {
        if(population <= 0)
            throw new IllegalArgumentException("Population must be positive");
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
