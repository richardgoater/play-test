package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class PurchaseOrder extends Model {

	private static final long serialVersionUID = 2L;

	@Id
	public Long orderNumber;
	
	@Required
	@ManyToOne
	public Customer customer;
	
	@Required
	public Date orderDate;
	
	
}
