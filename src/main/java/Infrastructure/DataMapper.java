package Infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class DataMapper { 
	Connection mConnection = null;

	public DataMapper( ){ openConnection("sofeea"); }
	
	private void openConnection( String pDBName ){ 
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
			 
		try {
			mConnection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/"+pDBName, "postgres",
						"pass");
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
	}
	public void closeConnection(){
		try {
			mConnection.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	
	public void executeSQL(String pSQL) throws SQLException{ 
		Statement lStatement = mConnection.createStatement(); 
		lStatement.execute(pSQL);
		lStatement.close();
	}
	public ResultSet executeSelect(String pSQL) throws SQLException{ 
		Statement lStatement = mConnection.createStatement();
		ResultSet rResultSet  = lStatement.executeQuery(pSQL);  
		return rResultSet;
	} 
}
