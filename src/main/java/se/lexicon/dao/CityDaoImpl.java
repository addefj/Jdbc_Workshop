package se.lexicon.dao;

import se.lexicon.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the implementation of CityDao for interacting with the 'city' table in the database.
 */
public class CityDaoImpl implements CityDao {

    //fields
    private Connection connection;

    //constructor
    public CityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<City> findById(int id) {
        String sqlQuery = "SELECT * FROM city WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(new City(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌Error finding city: " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> cityList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM city WHERE CountryCode = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    cityList.add(new City(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
                }
                return cityList;
            }

        } catch (SQLException e) {
            System.err.println("❌Error finding city: " + e.getMessage());
            e.printStackTrace();
        }

        return cityList;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cityList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM city WHERE Name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    cityList.add(new City(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
                }
                return cityList;
            }

        } catch (SQLException e) {
            System.err.println("❌Error finding city: " + e.getMessage());
            e.printStackTrace();
        }

        return cityList;
    }

    @Override
    public List<City> findAll() {
        ArrayList<City> cityList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM city";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
            return cityList;

        } catch (SQLException e) {
            System.err.println("❌Error finding all cities: " + e.getMessage());
            e.printStackTrace();
        }

        return cityList;
    }


    @Override
    public City save(City city) {
        String sqlQuery = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int generatedCityId = resultSet.getInt(1);
                    city.setId(generatedCityId);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌Error saving city: " + e.getMessage());
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public void update(City city) {
        String sqlQuery = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error updating city: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sqlQuery = "DELETE FROM city WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error deleting city: " + e.getMessage());
        }
    }
}
