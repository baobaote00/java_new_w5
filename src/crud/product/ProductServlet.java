package crud.product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ProductServleet
 */
@WebServlet("/product/*")
@MultipartConfig(maxFileSize = 16177215)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private List<Product> listProductCart;
	
	public void init() {
		productDAO = new ProductDAO();
		listProductCart = new ArrayList<Product>();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getPathInfo();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertProduct(request, response);
				break;
			case "/delete":
				deleteProduct(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateProduct(request, response);
				break;
			case "/list":
				listProduct(request, response);
				break;
			case "/add":
				addToCard(request,response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	public void addToCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = productDAO.selectProduct(Integer.parseInt(request.getParameter("id")));
		listProductCart.add(product);
		request.setAttribute("listProductCart", listProductCart);
		RequestDispatcher dispatcher = request.getRequestDispatcher("../productCart.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		// TODO Auto-generated method stub
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listProduct = productDAO.selectAllProduct();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("../productList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("../productForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingProduct = productDAO.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("../productForm.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);
	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));

		InputStream image = null;
		Part filePart = request.getPart("image");
		
		if (filePart != null) {
			image = filePart.getInputStream();
		}
		
		String description = request.getParameter("description");
		Product product = new Product(name, image, price, description);
		productDAO.insertProduct(product,image);
		
		Logger logger = Logger.getLogger(this.getClass().getName());
		logger.info("hinh = "+(filePart==null));
		logger.info(product.toString());
		logger.info((image==null)+"");
		logger.info(product.getBase64(image));
		logger.info((product.getInputStream().read()==-1)+"");
		logger.info((image.read()==-1)+"");
		response.sendRedirect("../product/list");
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		int price = Integer.parseInt(request.getParameter("price"));

		InputStream image = null;

		Part filePart = request.getPart("image");
		if (filePart != null) {
			image = filePart.getInputStream();
		}
		String description = request.getParameter("description");

		Product product = new Product(id, name, image, price, description);
		productDAO.updateProduct(product);
		response.sendRedirect("../list");
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.deleteProduct(id);
		response.sendRedirect("../product/list");
	}
}
