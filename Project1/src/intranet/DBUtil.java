package intranet;
import java.sql.*;

public class DBUtil {
	static String url="jdbc:oracle:thin:@localhost:1521:XE";
	static String user="intranet", pwd="tiger";
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getCon() throws SQLException{
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;
	}
}
