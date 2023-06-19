package dbApps;

import java.sql.*;
import java.util.Scanner;

public class E06RemoveVillain {

    private static final String GET_VILLAIN_NAME_BY_ID = "SELECT name FROM villains WHERE id = ?;";

    private static final String GET_RELEASED_MINIONS_COUNT_BY_VILLAINID =
            "SELECT COUNT(minion_id) FROM minions_villains WHERE villain_id = ?;";

    private static final String DELETE_MINIONS_VILLAINS_BY_VILLAINID =
            "DELETE FROM minions_villains WHERE villain_id = ?;";

    private static final String DELETE_VILLAIN_BY_ID = "DELETE FROM villains WHERE id = ?;";

    private static String villainName;
    private static int minionsCount;

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        int villainID = Integer.parseInt(sc.nextLine());

        Connection connection = getConnection();

        PreparedStatement getVillainName = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        getVillainName.setInt(1, villainID);

        ResultSet resultVillain = getVillainName.executeQuery();

        if(resultVillain.next()) {
            villainName = resultVillain.getString("name");

//            finding minions that serve him
           minionsCount =  getMinionsCount(villainID, connection);
//            DELETE
            deleteVillainAndReleaseMinions(villainID, connection);

        } else {
            System.out.println("No such villain was found");
        }

        connection.close();
    }

    private static int getMinionsCount(int villainID, Connection connection) throws SQLException {
        PreparedStatement getCountOfMinionServants =
                connection.prepareStatement(GET_RELEASED_MINIONS_COUNT_BY_VILLAINID);
        getCountOfMinionServants.setInt(1, villainID);

        ResultSet minionsCountSet = getCountOfMinionServants.executeQuery();
        if(minionsCountSet.next()) {
            minionsCount = minionsCountSet.getInt(1);
        }

        return minionsCount;
    }

    private static void deleteVillainAndReleaseMinions(int villainID, Connection connection) throws SQLException {
        connection.setAutoCommit(false);

        try {
            PreparedStatement releaseServants = connection.prepareStatement(DELETE_MINIONS_VILLAINS_BY_VILLAINID);
            releaseServants.setInt(1, villainID);
            releaseServants.executeUpdate();

            PreparedStatement deleteVillain = connection.prepareStatement(DELETE_VILLAIN_BY_ID);
            deleteVillain.setInt(1, villainID);
            deleteVillain.executeUpdate();

            connection.commit();

            printingTheOutput();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

            connection.rollback();
        }

    }

    private static void printingTheOutput() {
        System.out.printf("%s was deleted%n", villainName);
        System.out.printf("%d minions released", minionsCount);
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "fondle");
    }
}
