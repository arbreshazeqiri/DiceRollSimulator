package pdg.controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection{
	public Connection databaselink;
	public Connection getConnection(){
		//Per log in
		String databaseName="";
		String databaseUser="";
		String databasePassword="";
		String url="jdbc:mysql://localhost/" +  databaseName;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaselink= DriverManager.getConnection(url,databaseUser,databasePassword);

		}catch(Exception ex){
              ErrorPopupComponent.show(ex)
		}
		return databaselink
}
}