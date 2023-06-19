package dbApps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class E07PrintAllMinionNames {

    private static final String GET_ALL_MINION_NAMES = "SELECT name FROM minions;";

    private static List<String> resultNameList = new ArrayList<>();


    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();


        PreparedStatement getMinionNames = connection.prepareStatement(GET_ALL_MINION_NAMES);
        ResultSet minionNamesSet = getMinionNames.executeQuery();

        while (minionNamesSet.next()){
            resultNameList.add(minionNamesSet.getString("name"));
        }

        connection.close();

//        printing the result

        int count = resultNameList.size();
        int end = count / 2;

        for (int i = 0; i < end; i++) {
              System.out.println(resultNameList.get(i));
              System.out.println(resultNameList.get(count - 1 - i));

        }

        if (count % 2 == 1) {
            System.out.println(resultNameList.get(count / 2));
        }


    }
    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "fondle");
    }
}
