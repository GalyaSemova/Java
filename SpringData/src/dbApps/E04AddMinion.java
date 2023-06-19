package dbApps;

import java.sql.*;
import java.util.Scanner;

public class E04AddMinion {

    private static String[] inputMinion;
    private  static String[] inputVillain;

    private static final String GET_TOWN_BY_NAME = "SELECT t.id FROM towns AS t WHERE t.name = ?;";
    private static final String INSERT_INTO_TOWNS = "INSERT INTO towns(name) VALUES(?);";

    private static final String INSERT_INTO_MINIONS= "INSERT INTO minions(name, age, town_id) VALUES(?, ?, ?);";
    private static  final String GET_INSERTED_MINION_ID = "SELECT id FROM minions ORDER BY id DESC LIMIT 1;";

    private static final String INSERT_MINION_TO_VILLAIN = "INSERT INTO minions_villains VALUES(?, ?);";
    private static final String GET_VILLAIN_BY_NAME = "SELECT v.id FROM villains AS v WHERE v.name = ?;";
    private static final String INSERT_INTO_VILLAINS = "INSERT INTO villains(name, evilness_factor) VALUES(?,?);";
    private static final String EVIL_FACTOR = "evil";

    private static int townId;
    private static int villainId;


    public static void main(String[] args) throws SQLException {


        Connection connection = getConnection();

        Scanner sc = new Scanner(System.in);
        
        inputMinion = sc.nextLine().split(": ");
        String[] minionData = inputMinion[1].split("\\s+");
        String minionName = minionData[0];
        int minionAge = Integer.parseInt(minionData[1]);
        String minionTown = minionData[2];
        
        inputVillain = sc.nextLine().split(": ");
        String villainName = inputVillain[1];

//        add town && villain if missing and finding their ids
        townId = addGetTownIfMissing(connection, minionTown);
        villainId =  addGetVillainIfMissing(connection, villainName);


        insertMinions(connection, minionName, minionAge, villainName, townId, villainId);

        connection.close();

    }
    private static void insertMinions(Connection connection, String minionName, int minionAge, String villainName, int townId, int villainId) throws SQLException {
        PreparedStatement insertMinion = connection
                .prepareStatement(INSERT_INTO_MINIONS);

        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, townId);
        insertMinion.executeUpdate();

        PreparedStatement selectInsertedMinionId = connection
                .prepareStatement(GET_INSERTED_MINION_ID);
        ResultSet lastMinionSet = selectInsertedMinionId.executeQuery();
        lastMinionSet.next();
        int lastMinionId = lastMinionSet.getInt("id");

        PreparedStatement insertMinionsVillains = connection
                .prepareStatement(INSERT_MINION_TO_VILLAIN);
        insertMinionsVillains.setInt(1, lastMinionId);
        insertMinionsVillains.setInt(2, villainId);
        insertMinionsVillains.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
    }


    private static int addGetVillainIfMissing(Connection connection, String villainName) throws SQLException {
        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_BY_NAME);
        villainStatement.setString(1, villainName);


        ResultSet villainSet = villainStatement.executeQuery();
        int id;

        if(!villainSet.next()) {
            PreparedStatement insertVillainStatement =  connection.prepareStatement(INSERT_INTO_VILLAINS);
            insertVillainStatement.setString(1, villainName);
            insertVillainStatement.setString(2,EVIL_FACTOR);
            insertVillainStatement.executeUpdate();

            ResultSet newVillainSet = villainStatement.executeQuery();
            newVillainSet.next();
            id = newVillainSet.getInt("id");


            System.out.printf("Villain %s was added to the database.%n", villainName);
        } else {
            id = villainSet.getInt("id");
        }

        return id;
    }

    private static int  addGetTownIfMissing(Connection connection, String minionTown) throws SQLException {
        PreparedStatement townStatement = connection.prepareStatement(GET_TOWN_BY_NAME);
        townStatement.setString(1, minionTown);

        ResultSet townSet = townStatement.executeQuery();

        int id;

        if(!townSet.next()) {
           PreparedStatement insertTownStatement =  connection.prepareStatement(INSERT_INTO_TOWNS);
           insertTownStatement.setString(1, minionTown);
           insertTownStatement.executeUpdate();

            ResultSet newTownSet = townStatement.executeQuery();
            newTownSet.next();
            id = newTownSet.getInt("id");

           System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            id = townSet.getInt("id");
        }

        return id;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "fondle");
    }
}

