package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Customer extends Model {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	@Required
	public String name;
	public String address1;
	
	@OneToMany(mappedBy="customer")
	public List<PurchaseOrder> orders;
	
	public static Finder<Long, Customer> find = new Finder<Long, Customer>(Long.class, Customer.class);
	
	public static List<Customer> all() {
		return find.all();
	}
	
	public static void delete(Long id) {	
		find.ref(id).delete();
	}

}
