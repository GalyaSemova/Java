package dbApps;

import java.sql.*;
import java.util.Scanner;

public class SimpleQuery {

    private static final String SELECT_GAMES_COUNT = "SELECT u.first_name, u.last_name, COUNT(ug.game_id) " +
            "FROM users AS u " +
            "JOIN users_games AS ug " +
            "ON u.id = ug.user_id " +
            "WHERE u.user_name = ? " +
            "GROUP BY u.id;";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Connection connection = getConnection();

        String username = readUsername(sc);

        PreparedStatement stmt =
                connection.prepareStatement(SELECT_GAMES_COUNT);

        stmt.setString(1, username);

        ResultSet result = stmt.executeQuery();


        if(result.next()) {
            System.out.printf("User: %s%n", username);
            System.out.printf("%s %s has played %d games", result.getString("first_name"),
                    result.getString("last_name"),
                            result.getInt(3));
        } else {
            System.out.println("No such user exists");
        }

    }

    private static String readUsername(Scanner sc) {
        System.out.print("Enter username ");
        String username= sc.next();
        return username;
    }

    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", "root", "fondle");
        return connection;
    }
}
