package crud.product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private static final String url = "jdbc:mysql://localhost:3306/newsdata?characterEncoding=latin1&useConfigs=maxPerformance";
	private static final String user = "root";
	private static final String password = "";

	private static final String INSERT_PRODUCT_SQL = "INSERT INTO products (product_name, product_price,image,product_description) values (?, ?,?, ?)";

	private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE id =?";
	private static final String SELECT_ALL_PRODUCT = "SELECT * FROM products;";
	private static final String DELETE_PRODUCT_SQL = "DELETE FROM products WHERE id = ?;";
	private static final String UPDATE_PRODUCT_SQL = "UPDATE products SET product_name = ?,product_price=?,image=?,product_description=? WHERE id = ?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertProduct(Product product, InputStream image) throws SQLException {
		System.out.println(INSERT_PRODUCT_SQL);

		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);) {
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getProductPrice());
			preparedStatement.setBlob(3, image);
			System.out.println(product.getBase64(image));
			preparedStatement.setString(4, product.getDescription());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public Product selectProduct(int id) {
		Product product = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("product_name");
				int price = rs.getInt("product_price");
				InputStream image = rs.getBlob("image").getBinaryStream();
				String description = rs.getString("product_description");
				product = new Product(id, name, image, price, description);
			}
		} catch (SQLException e) {
		}
		return product;
	}

	public List<Product> selectAllProduct() {
		List<Product> product = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("product_name");
				int price = rs.getInt("product_price");
				InputStream image = rs.getBlob("image").getBinaryStream();
				String description = rs.getString("product_description");
				product.add(new Product(id, name, image, price, description));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return product;
	}

	public boolean deleteProduct(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProduct(Product product) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getProductPrice());

			if (product.getInputStream() != null) {
				// fetches input stream of the upload file for the blob column
				statement.setBlob(3, product.getInputStream());
			}

			statement.setString(4, product.getDescription());
			statement.setInt(5, product.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
