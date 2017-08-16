package win.sunshine.base.service.api.domain;

import java.util.List;

import org.springframework.core.annotation.Order;

/**
 * 客户
 * @author huyanqiu
 *
 */
public class Person {
	private String id;  
    private String name;  
    private String address;  
    private String tel;  
    private List<Order> orders; 
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override  
    public String toString() {  
        return "{id: " + id + ", name: " + name + ", address: " + address + ", tel: " + tel + "}";  
    }  
}
