package ptithcm.dao.product;



import java.util.List;

import ptithcm.model.customer.CustomerReview;
import ptithcm.model.order.OrderLine;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;


public interface ProductDao {
	public List<ProductItem> getAllProducts();
	public ProductItem getProductById(int id);
	public Integer getOrderID(int id);
	public List<CustomerReview> getAllCommentsById(int id);
	public Double getRatingAverageProduct(int productId);
	public OrderLine getOrderLineById(int id);
	public void deleteProductItem(int id);
	public List<ProductItem> searchProductItem(String name);
	public List<Product> getAllProductByCateId(int categoryId);
	
	
}
