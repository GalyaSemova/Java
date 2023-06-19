package dbApps;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class E08IncreaseMinionsAge {

    private  static int[] inputIds;
    private static final String UPDATE_MINION_NAME_AGE_BY_ID =
                    "UPDATE minions AS m " +
                    "SET m.age = m.age + 1, m.name = LOWER(m.name) " +
                    "WHERE m.id = ?;";

    private static final String GET_MINION_AGE_NAME = "SELECT name, age FROM minions;";

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        Connection connection = getConnection();

        inputIds = Arrays.stream(sc.nextLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                                .toArray();

        updateMinions(connection);


        printingAllMInions(connection);


        connection.close();

    }

    private static void printingAllMInions(Connection connection) throws SQLException {
        PreparedStatement getAllMinions = connection.prepareStatement(GET_MINION_AGE_NAME);
        ResultSet result = getAllMinions.executeQuery();

        while (result.next()) {
            System.out.printf("%s %d%n",
                    result.getString("name"),
                    result.getInt("age"));
        }
    }

    private static void updateMinions(Connection connection) throws SQLException {
        PreparedStatement updateMinionNameAndAge =
                connection.prepareStatement(UPDATE_MINION_NAME_AGE_BY_ID);

        for (int i = 0; i < inputIds.length; i++) {
            int currentID = inputIds[i];
            updateMinionNameAndAge.setInt(1, currentID);
            updateMinionNameAndAge.executeUpdate();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "fondle");
    }
}
