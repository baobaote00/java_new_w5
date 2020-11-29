package crud.category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/newsdata?characterEncoding=latin1&useConfigs=maxPerformance";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_CATEGORY_SQL ="INSERT INTO category (name) VALUES (?);";

	private static final String SELECT_CATEGORY_BY_ID = "SELECT id,name FROM category WHERE id =?";
	private static final String SELECT_ALL_CATEGORY = "SELECT * FROM category;";
	private static final String DELETE_CATEGORY_SQL = "DELETE FROM category WHERE id = ?;";
	private static final String UPDATE_CATEGORY_SQL = "UPDATE category SET name = ? WHERE id = ?;";

	public CategoryDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertCategory(Category category) throws SQLException {
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
			preparedStatement.setString(1, category.getName());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Category selectCategory(int id) {
		Category category = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				category = new Category(id,name);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return category;
	}

	public List<Category> selectAllCategory() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Category> category = new ArrayList<>();

		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				category.add(new Category(id, name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return category;
	}

	public boolean deleteCategory(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateCategory(Category category) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY_SQL);) {
			statement.setString(1, category.getName());
			statement.setInt(2, category.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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
