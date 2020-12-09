package crud.product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Logger;

public class Product {
	private int id;
	private String productName;
	private int productPrice;
	private String image;
	private String description;
	private InputStream inputStream;
	
	public Product() {

	}

	public Product(int id, String name, InputStream image, int price, String description) {
		super();
		this.id = id;
		this.setProductName(name);
		this.image = getBase64(image);
		this.setProductPrice(price);
		this.description = description;
		this.setInputStream(image);
	}

	public Product(String name, InputStream image, int price, String description) {
		super();
		this.image = getBase64(image);
		this.setProductName(name);
		this.setProductPrice(price);
		this.description = description;
		this.setInputStream(image);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = getBase64(image);
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBase64(InputStream image){
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		
		try {
			while ((bytesRead = image.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		byte[] imageBytes = outputStream.toByteArray();
		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		try {
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Image;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.productName + this.description+(this.image==null);
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}