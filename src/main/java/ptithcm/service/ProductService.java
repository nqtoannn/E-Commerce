package ptithcm.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.logging.log4j.core.appender.rolling.action.IfFileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.product.ProductDao;
import ptithcm.model.customer.Customer;
import ptithcm.model.customer.CustomerReview;
import ptithcm.model.order.OrderLine;
import ptithcm.model.product.ProductItem;
import ptithcm.model.shop.ShopOrder;

@Service
public class ProductService {
	@Autowired 
	private ProductDao productDao;
	
	public List<ProductItem> getListProducts() {
		List<ProductItem> listProducts = productDao.getAllProducts();
		if (!listProducts.isEmpty()) {
			return listProducts;
		}
		return null;
	}
	
	
	public ProductItem getProductById(int id) {
		ProductItem product = productDao.getProductById(id);
		if (product != null) {
			return product;
		}
		return null;
	}
	
	public List<CustomerReview> getAllCommentsById(int id) {
		List<CustomerReview> comments = productDao.getAllCommentsById(id);
		if (!comments.isEmpty()) {
			return comments;
		}
		return null;
	}

	public int getOrderId(int id) {
		int value = productDao.getOrderID(id);
		return value;
	}

	
	public Double getRatingAverageProduct(int id) {
		Double value = productDao.getRatingAverageProduct(id);
		if (value != null) {
			return value;
		}
		return null;
	}
	
	public OrderLine getOrderLinebyId(int id) {
		OrderLine orderLine = productDao.getOrderLineById(id);
		if (orderLine != null) {
			return orderLine;
		}
		return null;
	}
	
}
