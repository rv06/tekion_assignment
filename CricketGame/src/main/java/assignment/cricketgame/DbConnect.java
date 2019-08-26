package assignment.cricketgame;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnect {
    private String userName;
    private String password;
    private static Connection connection;
    private static PreparedStatement stmt = null;
    private static ResultSet res = null;
    private static DbConnect dbconnection = null;
    private DbConnect() {
        getDBConnection();
    }
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }
    private static void getDBConnection() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cricket?useSSL=false", "root", "mysql_connect");
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void updateTeams(Team team) {
        try {
            stmt = connection.prepareStatement("select Name, Role from Players where Team = ?");
            stmt.setString(1, team.getTeamName());
            res = stmt.executeQuery();

            while(res.next()) {
                team.getTeam().add(new Player(res.getString(1), Player.Role.valueOf(res.getString(2))));
            }
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public static  DbConnect getInstance() {
        if(dbconnection == null)
            dbconnection = new DbConnect();

        return dbconnection;
    }

    public void closeConnection() throws SQLException {
        if(res != null)
            res.close();
        if(stmt != null)
            stmt.close();
        if(connection != null)
            connection.close();
    }

    public int updateMatchTable(String battingFirst, String battingSecond) {
        try{
            stmt = connection.prepareStatement("insert into MatchResult(TeamBattingFirst, TeamBattingSecond) values ('"+ battingFirst + "','" + battingSecond +"'  )");
            stmt.executeUpdate();
            stmt = connection.prepareStatement("select last_insert_id() as last_id from MatchResult");
            res = stmt.executeQuery();
            res.next();
            String lastid = res.getString("last_id");
            System.out.println("mathcid " + lastid);
            return Integer.valueOf(lastid);
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
        return 0;
    }

    public void updateMatchData(int matchId, int over, Team team) {
        try{
            String score = team.getRuns() + "-" + team.getWickets();
            stmt = connection.prepareStatement("insert into MatchData values ("+ matchId + "," + over +",'" + team.getTeamName() +"'," + score + " )");
            stmt.executeUpdate();

        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void updateMatchResult(int matchId, String ScoreFirstInnings, String ScoreSecondInnings, String Result) {
        try{
            stmt = connection.prepareStatement("update MatchResult set ScoreFirstInnings='" + ScoreFirstInnings + "', ScoreSecondInnings='" + ScoreSecondInnings +
                    "', Result='" + Result +"' where Id = ?");
            stmt.setInt(1, matchId);
            stmt.executeUpdate();
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        List<Player> lt = new ArrayList<>();
        Team t = new Team("India", lt);
        getDBConnection();
        DbConnect db = new DbConnect();
        db.updateTeams(t);
        for(Player p : t.getTeam())
            System.out.println(p.getName() + "  " + p.getRole());


    }
}
