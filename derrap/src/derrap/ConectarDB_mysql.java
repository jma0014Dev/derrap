package derrap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConectarDB_mysql {
	
	private static final String CONTROLADOR="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/derrap";
	private static final String USUARIO="root";
	private static final String CLAVE="root";
	
	
	Connection cn=null;
	Statement stm=null;
	ResultSet resultado=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	public Connection conectar() {
		
		
		try {
			cn=DriverManager.getConnection(URL,USUARIO,CLAVE);
			System.out.println("conexion realizada");
			stm= cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("conexion no  realizada");
			e.printStackTrace();
		}
		return cn;
	}
	
	
}