package se.lexicon;

import se.lexicon.dao.CityDao;
import se.lexicon.dao.CityDaoImpl;
import se.lexicon.db.MySQLConnection;
import se.lexicon.model.City;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Represents the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {

        try {
            Connection mySQLConnection = MySQLConnection.getConnection();
            CityDao cityDao = new CityDaoImpl(mySQLConnection);

            //test findAll()
            //System.out.println(cityDao.findAll());

            System.out.println(cityDao.findByCode("SWE"));
            System.out.println("--------");
            System.out.println(cityDao.findById(120));
            System.out.println("--------");
            System.out.println(cityDao.findByName("London"));
            System.out.println("--------");
            //cityDao.save(new City("TEST", "SWE", "TEST", 10));
            //cityDao.update(new City(120, "Concordial", "ARG", "Entre Rios", 116485));
            //cityDao.deleteById(120);

        } catch (SQLException e) {
            System.out.println("MySQL DB connection failed");
            e.printStackTrace();
        }
    }
}
