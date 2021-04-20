package board_proj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author 
 * test src folder에서만 쓰는 일시적인 파일 (Jndi는 web에서 쓰는 것이기 때문에 Junit에서 쓸 수 없음)
 */
public class JdbcUtil {

	public static Connection getConnection() {

		Connection con = null;
		try {
			String url = "jdbc:mysql://localhost:3306/web_gradle_erp?useSSL=false";
			String id = "user_gradle_erp";
			String passwd = "rootroot";

			con = DriverManager.getConnection(url, id, passwd);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

}
