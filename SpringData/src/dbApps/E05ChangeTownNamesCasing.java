package dbApps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E05ChangeTownNamesCasing {

    private static final String GET_TOWNS_IN_COUNTRY = "SELECT name FROM towns WHERE country = ?;";

    private static List<String> townsList = new ArrayList<>();



    public static void main(String[] args) throws SQLException {
        String country = new Scanner(System.in).nextLine();

        Connection connection = getConnection();

        findingTowns(country, connection);

        printingOutput();


    }

    private static void findingTowns(String country, Connection connection) throws SQLException {
        PreparedStatement findTownsByCountry = connection.prepareStatement(GET_TOWNS_IN_COUNTRY);
        findTownsByCountry.setString(1, country);

        ResultSet townNamesSet = findTownsByCountry.executeQuery();

        while(townNamesSet.next()){
            String town = townNamesSet.getString("name");
            townsList.add(town);
        }
    }

    private static void printingOutput() {
        if(townsList.isEmpty()) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.%n", townsList.size());
            String upperTownNames = townsList.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.joining(", "));

            System.out.println("[" + upperTownNames + "]");
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "fondle");
    }
}
