package dbApps;

import java.sql.*;


public class E02GetVillainsNames {
    private static final String GET_NAMES_AND_MINION_COUNT = 
            "SELECT v.name, COUNT(mv.minion_id) AS minion_count FROM villains AS v " +
            "JOIN minions_villains AS mv " +
            "ON v.id = mv.villain_id " +
            "GROUP BY v.id " +
            "HAVING minion_count > 15 " +
            "ORDER BY minion_count DESC;";
    
    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement(GET_NAMES_AND_MINION_COUNT);

        ResultSet result = stmt.executeQuery();

        while(result.next()) {
            System.out.printf("%s %d%n",
                    result.getString("name"),
                    result.getInt("minion_count"));
        }

        connection.close();

    }
    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "fondle");
    }
}
