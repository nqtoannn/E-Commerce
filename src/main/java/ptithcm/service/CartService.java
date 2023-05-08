package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.shoppingCart.ShoppingCartDao;
import ptithcm.model.shoppingCart.ShoppingCartItem;


@Service
public class CartService {
	@Autowired
	private ShoppingCartDao shoppingCartDao;

	
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
	
}
