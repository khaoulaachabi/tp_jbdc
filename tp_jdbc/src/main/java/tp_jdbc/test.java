package tp_jdbc;
import java.sql.*;
public class test {

	
		// TODO Auto-generated method stub
		 private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		    private static final String DB_URL = "jdbc:mysql://localhost:3306/tp_jdbc";
		    private static final String USERNAME = "root";
		    private static final String PASSWORD = "";
		    private static Connection connection;

		    static {
		        try {
		            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    public static void establishConnection() {
		        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
		            System.out.println("Connected to the database successfully!");
		            try (Statement checkStatement = connection.createStatement();
		                 ResultSet checkResultSet = checkStatement.executeQuery("SELECT 1")) {
		                if (checkResultSet.next()) {
		                    System.out.println("Connection is working properly!");
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    public static void insertEtudiant(String firstname, String lastname, String age) {
		        String insertSql = "INSERT INTO etudiants (nom, prenom, age) VALUES (?, ?, ?)";
		        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
		            insertStatement.setString(1, firstname);
		            insertStatement.setString(2, lastname);
		            insertStatement.setString(3, age);
		            insertStatement.executeUpdate();
		            System.out.println("Inserted a new student: " + firstname + " " + lastname);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    public static void updateEtudiant(String firstname, String lastname, String age, int id) {
		        String updateSql = "UPDATE etudiants SET nom = ?, prenom = ?, age = ? WHERE id = ?";
		        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
		            updateStatement.setString(1, lastname);
		            updateStatement.setString(2, firstname);
		            updateStatement.setString(3, age);
		            updateStatement.setInt(4, id);
		            updateStatement.executeUpdate();
		            System.out.println("Updated student with ID " + id + ": " + firstname + " " + lastname);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    public static void deleteEtudiant(int id) {
		        String deleteSql = "DELETE FROM etudiants WHERE id = ?";
		        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
		            deleteStatement.setInt(1, id);
		            deleteStatement.executeUpdate();
		            System.out.println("Deleted student with ID " + id);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    public static void displayEtudiants() {
		        String selectSql = "SELECT * FROM etudiants";
		        try (Statement selectStatement = connection.createStatement();
		             ResultSet displayResultSet = selectStatement.executeQuery(selectSql)) {
		            System.out.println("Displaying students:");
		            while (displayResultSet.next()) {
		                int id = displayResultSet.getInt("id");
		                String displayFirstName = displayResultSet.getString("nom");
		                String displayLastName = displayResultSet.getString("prenom");
		                String displayPersonAge = displayResultSet.getString("age");
		                System.out.println("ID: " + id + ", FirstName: " + displayFirstName +
		                        ", LastName: " + displayLastName + ", Age: " + displayPersonAge);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    public static void main(String[] args) {
		        establishConnection();

		        insertEtudiant("khaoula", "achabi", "20");
		        displayEtudiants();

		        updateEtudiant("saad", "nihaw", "52", 2);
		        displayEtudiants();

		        deleteEtudiant(1);
		        displayEtudiants();
		        deleteEtudiant(1);

	}

}
