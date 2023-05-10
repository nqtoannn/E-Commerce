package ptithcm.controller.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.model.customer.CustomerAddress;
import ptithcm.model.product.ProductItem;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.model.user.User;
import ptithcm.service.AddressService;
import ptithcm.service.CartService;
import ptithcm.util.SessionUtil;

@RequestMapping("/e-commerce/")
@Controller
@Transactional
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	AddressService addressService;

	public static ShoppingCartItem shoppingCartItem;
	public static ProductItem productItem;

	@RequestMapping(value = "cart")
	public String showCart(ModelMap model, HttpSession ss, HttpServletRequest request) {
		long sum = 0;
		int id = (int) ((User) SessionUtil.getInstance().getValue(request, "USER_MODEL")).getId();
		List<ShoppingCartItem> listCart = cartService.getAllCartItemsById(id);
		List<Integer> price = new ArrayList<>();
		for (ShoppingCartItem item : listCart) {
			System.out.println(item.getProductItem().getStatus());
			if (item.getProductItem().getStatus().equals("ON_SALE")) {
				System.out.println(item.getProductItem().getId());
				System.out.println(cartService.getSalePrice(item.getProductItem().getId()));
				int salePrice = cartService.getSalePrice(item.getProductItem().getId())
						* item.getProductItem().getPrice() / 100;
				price.add(salePrice);
				sum += salePrice* item.getQuantity();
			}
			else {
				price.add(item.getProductItem().getPrice());
				sum += item.getProductItem().getPrice() * item.getQuantity();
			}
		}
		System.out.println(listCart.size());
		model.addAttribute("shoppingCart", listCart);
		ss.setAttribute("price",price);
		ss.setAttribute("sum", sum);
		return "e-commerce/cart";
	}

	@RequestMapping(params = "checkOut")
	public String showAddress(ModelMap model) {
		List<CustomerAddress> addressList = addressService.getAddressListByID(1);
		model.addAttribute("customerAddress", addressList);
		return "e-commerce/address";
	}

	@RequestMapping(value = "cart/increase", method = RequestMethod.POST)
	public String increaseCartItem(@RequestParam("productId") int productId) {
		cartService.increaseQty(productId);
		return "redirect:/e-commerce/cart.htm";
	}

	@RequestMapping(value = "cart/decrease", method = RequestMethod.POST)
	public String decreaseCartItem(@RequestParam("productId") int productId) {
		cartService.decreaseQty(productId);
		return "redirect:/e-commerce/cart.htm";
	}

	@RequestMapping(value = "cart/delete", method = RequestMethod.POST)
	public String deleteCartItem(@RequestParam("productId") int productId) {
		cartService.deleteCartItem(productId);
		return "redirect:/e-commerce/cart.htm";
	}

}