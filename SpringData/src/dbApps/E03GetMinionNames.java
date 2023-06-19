package dbApps;

import java.sql.*;
import java.util.Scanner;

public class E03GetMinionNames {
    private static final String FIND_MINION_NAMES_BY_VILLAIN_ID =
            "SELECT v.name, m.name, m.age FROM minions AS m " +
            "JOIN minions_villains AS mv " +
            "ON m.id = mv.minion_id " +
            "JOIN villains As v " +
            "ON v.id = mv.villain_id " +
            "WHERE v.id = ? " +
            "ORDER BY m.name;";


    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Connection connection = getConnection();

        System.out.print("Enter villain id: ");
        int villainID = Integer.parseInt(sc.nextLine());

        PreparedStatement stmt = connection.prepareStatement(FIND_MINION_NAMES_BY_VILLAIN_ID);
        stmt.setInt(1, villainID);

        ResultSet result = stmt.executeQuery();
        int counter = 1;

        if(result.next()) {
            System.out.printf("Villain: %s%n", result.getString("v.name"));
            while (result.next()) {

                System.out.printf("%d. %s %d%n", counter, result.getString("m.name"),
                        result.getInt("m.age"));
                counter++;
            }
        } else {
            System.out.printf("No villain with %d exists in the database.", villainID);
        }

        connection.close();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "fondle");
    }
}
