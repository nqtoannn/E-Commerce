package ptithcm.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.model.customer.Customer;
import ptithcm.model.customer.CustomerReview;
import ptithcm.model.product.ProductItem;
import ptithcm.model.shoppingCart.ShoppingCart;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.model.user.User;
import ptithcm.service.CustomerService;
import ptithcm.service.ProductService;
import ptithcm.service.ShoppingCartService;
import ptithcm.util.SessionUtil;

@Transactional
@Controller
@RequestMapping("/e-commerce/")
public class ProductController {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ProductService productService;
	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	CustomerService customerService;

	@RequestMapping("shop")
	public String shop(ModelMap model, HttpServletRequest request) {
		List<ProductItem> list = productService.getListProducts();
		model.addAttribute("listProduct", list);
		User user = (User) SessionUtil.getInstance().getValue(request, "USER_MODEL");
		return "e-commerce/shop";
	}

	static Integer id = 0;

	@RequestMapping(value = "product/{productId}", method = RequestMethod.GET)
	public String product(ModelMap model, @PathVariable("productId") int productId) {
		ProductItem product = productService.getProductById(productId);
		int cartId = shoppingCartService.isHaveCart(1);
		Customer customer = customerService.getCustomerById(1);
		id = customer.getId();
		System.out.println("Cart ID: " + cartId);
		if (cartId > 0) {
			int quantityOrdered = shoppingCartService.getTotalQuantityOrdered(cartId);
			model.addAttribute("quantityOrdered", quantityOrdered);
			List<CustomerReview> comments = productService.getAllCommentsById(productId);
			if (comments != null) {
				Collections.reverse(comments);
				model.addAttribute("comments", comments);
			}
		}
		model.addAttribute("product", product);
		return "e-commerce/product";
	}

	public static Integer getIdCustomer() {
		return id;
	}

	@RequestMapping("list")
	public String index(ModelMap model) {
		List<ProductItem> list = productService.getListProducts();
		model.addAttribute("listProduct", list);
		return "e-commerce/list";
	}

	@RequestMapping(value = "product/{productId}", method = RequestMethod.POST, params = "addToCart")
	public String addToCart(ModelMap model, @PathVariable("productId") int productId,
			@ModelAttribute("shoppingCartItem") ShoppingCartItem shoppingCartItem, HttpServletRequest request) {
		ProductItem product = productService.getProductById(productId);
		model.addAttribute("product", product);
		Integer quantity = Integer.valueOf(request.getParameter("quantityInput"));
		shoppingCartItem.setQuantity(quantity);
		Customer customer = customerService.getCustomerById(1);
		int cartId = shoppingCartService.isHaveCart(1);
		System.out.println("Cart ID: " + cartId);
		if (cartId > 0) {
			int quantityOrdered = shoppingCartService.getTotalQuantityOrdered(cartId);
			model.addAttribute("quantityOrdered", quantityOrdered);
			List<CustomerReview> comments = productService.getAllCommentsById(productId);
			if (comments != null) {
				model.addAttribute("comments", comments);
			}
		}
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		if (cartId > 0) {
			shoppingCartItem.setCart(shoppingCartService.getShoppingCartId(cartId, 1));
			try {
				session.save(shoppingCartItem);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công! ");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại! ");
			} finally {
				session.close();
			}
		} else {
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setCustomer(customer);
			try {
				session.save(shoppingCart);
				t.commit();
				model.addAttribute("message", "Thêm mới giỏ hàng thành công! ");

			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới giỏ hàng thất bại! ");
			} finally {
				session.close();
			}
		}
		int quantityOrdered = shoppingCartService.getTotalQuantityOrdered(cartId);
		model.addAttribute("quantityOrdered", quantityOrdered);
		return "e-commerce/product";
	}

	@RequestMapping(value = "product/{productId}", method = RequestMethod.POST, params = "addComment")
	public String addComment(ModelMap model, @PathVariable("productId") int productId,
			@ModelAttribute("CustomerReview") CustomerReview customerReview, HttpServletRequest request) {
		Customer customer = customerService.getCustomerById(getIdCustomer());
		customerReview.setCustomer(customer);
		String comment = request.getParameter("commentInput").trim();
		System.out.println("Comment: " + comment);
		customerReview.setComment(comment);
		customerReview.setOrderLine(productService.getOrderLinebyId(productId));
		customerReview.setRatingValue(5);
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		if (customerReview.getComment() != "") {
			try {
				session.save(customerReview);
				t.commit();
				model.addAttribute("message", "Success");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Fail");
			} finally {
				session.close();
			}
		}
		return product(model, productId);
	}

}
