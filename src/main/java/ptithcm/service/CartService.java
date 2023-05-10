package ptithcm.service;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.promotion.PromotionDao;
import ptithcm.dao.shoppingCart.ShoppingCartDao;
import ptithcm.model.shoppingCart.ShoppingCart;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.model.address.Address;


@Service
public class CartService {
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	@Autowired
	private PromotionDao promotionDao;

	
	public List<ShoppingCartItem> getAllCartItemsById(int ctmId){
		List<ShoppingCartItem> listCartItems = shoppingCartDao.getAllCartItemsById(ctmId);
		if (listCartItems == null) {
			return null;
		}
		return listCartItems;
	}
	
	public int deleteCartItem(int idCartItem) {
		int delete = shoppingCartDao.deleteCartItem(idCartItem);
		return delete;
	}
	
	public int increaseQty(int shoppingCartItemId) {
		int increase =shoppingCartDao.increaseQty(shoppingCartItemId);
		return increase;
	}
	
	public int decreaseQty(int shoppingCartItemId) {
		int decrease =shoppingCartDao.decreaseQty(shoppingCartItemId);
		return decrease;
	}

	public int getSalePrice(int productId){
		int percent = 100 - promotionDao.getPriceDiscount(productId);
		return percent;
	}

}
