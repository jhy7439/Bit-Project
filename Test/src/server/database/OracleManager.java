package server.database;

import java.io.File;
import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class OracleManager implements DBManager {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private Connection con;
	
	
	public OracleManager() {
		try {
			String url = "jdbc:oracle:thin:@192.168.0.38:1521:xe";
			con = DriverManager.getConnection(url,"scott","tiger");		
		}catch(Exception e) {
			System.out.println("DB연결에러.."+e.getMessage());
		}
	}


	@Override
	public PreparedStatement create(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}

	@Override
	public Statement create() throws SQLException {
		return con.createStatement();
	}

}
