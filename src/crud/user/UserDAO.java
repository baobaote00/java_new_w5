package crud.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public User checkLogin(String username, String password) throws SQLException, 
			ClassNotFoundException {
		String jdbcURL = "jdbc:mysql://localhost:3306/newsdata?characterEncoding=latin1&useConfigs=maxPerformance";
		String dbUser = "root";
		String dbPassword = "";

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		String sql = "SELECT * FROM users WHERE username = ? and password = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, username);//colum2
		statement.setString(2, password);//colum3
		ResultSet result = statement.executeQuery();//returnresultset

		User user = null;

		if (result.next()) {
			user = new User();
			user.setUsername(result.getString("username"));//??
		
		}

		connection.close();

		return user;
	}
}
