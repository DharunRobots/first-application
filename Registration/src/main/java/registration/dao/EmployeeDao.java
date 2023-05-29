package registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

import registration.module.Employee;

public class EmployeeDao {
	
	/*
	 * private static int count = 0; private int jobID; private String name; private
	 * boolean isFilled;
	 */
	
	   static int id = 0;
	 public int registerEmployee(Employee employee) throws ClassNotFoundException {
		 String GET_ID = "select max(id) from employee;";
	        String INSERT_USERS_SQL = "INSERT INTO employee" +
	            "  (id, firstname, lastname, username, password, address, contact) VALUES " +
	            " (?, ?, ?, ?, ?,?,?);";

	        int result = 0;
	        
	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/employee", "root", "8946078990");
	        		
	        		PreparedStatement prep = connection.prepareStatement(GET_ID);        		
	        		
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setInt(1,EmployeeDao.id++);
	            preparedStatement.setString(2, employee.getFirstname());
	            preparedStatement.setString(3, employee.getFirstname());
	            preparedStatement.setString(4, employee.getUsername());
	            preparedStatement.setString(5, employee.getPassword());
	            preparedStatement.setString(6, employee.getAddress());
	            preparedStatement.setString(7, employee.getContact());

	            System.out.println(preparedStatement);
	            //System.out.println(prep);
	            // Step 3: Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
}
