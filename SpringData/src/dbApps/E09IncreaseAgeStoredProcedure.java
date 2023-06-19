package dbApps;

import java.sql.*;
import java.util.Scanner;

public class E09IncreaseAgeStoredProcedure {
    private static final String UPDATE_MINION_AGE_PROCEDURE = "CALL usp_get_older(?)";

    private static final String GET_MINION_NAME_AGE_BY_ID =
            "SELECT name, age FROM minions WHERE id = ?";


    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();

        Scanner sc = new Scanner(System.in);

        int minionId = Integer.parseInt(sc.nextLine());


        updateMinionAge(connection, minionId);

        printingUpdatedMInion(connection, minionId);


        connection.close();

    }

    private static void printingUpdatedMInion(Connection connection, int minionId) throws SQLException {
        PreparedStatement getUpdatedMinion = connection.prepareStatement(GET_MINION_NAME_AGE_BY_ID);
        getUpdatedMinion.setInt(1, minionId);
        ResultSet minion = getUpdatedMinion.executeQuery();

        if(minion.next()) {
            System.out.printf("%s %d%n", minion.getString("name"),
                    minion.getInt("age"));
        }
    }

    private static void updateMinionAge(Connection connection, int minionId) throws SQLException {
        CallableStatement updateAge = connection.prepareCall(UPDATE_MINION_AGE_PROCEDURE);

        updateAge.setInt(1, minionId);

        updateAge.execute();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "fondle");
    }
}
