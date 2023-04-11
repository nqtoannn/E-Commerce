package ptithcm.model.order;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ptithcm.model.shop.ShopOrder;

@Entity
@Table(name = "Order_Status")
public class OrderStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="status")
	private String orderStatus;

	@OneToMany(mappedBy = "orderStatus", fetch = FetchType.EAGER)
	private Collection<ShopOrder> shopOrders;

	public OrderStatus() {
		super();
	}

	public OrderStatus(Integer id, String status, Collection<ShopOrder> shopOrders) {
		super();
		this.id = id;
		this.orderStatus = status;
		this.shopOrders = shopOrders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return orderStatus;
	}

	public void setStatus(String status) {
		this.orderStatus = status;
	}

	public Collection<ShopOrder> getShopOrders() {
		return shopOrders;
	}

	public void setShopOrders(Collection<ShopOrder> shopOrders) {
		this.shopOrders = shopOrders;
	}

}