package pdg.components;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection{
    public Connection databaseLink;

    public Connection getConnection(){
        //Per log in
        String databaseName="pdg";
        String databaseUser="root";
        String databasePassword="passwordijuvqitu";
        String url="jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch(Exception ex){
            ErrorPopupComponent.show(ex.toString());
        }
        return databaseLink;
    }
}