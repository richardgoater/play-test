package controllers;

import models.Customer;
import models.PurchaseOrder;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result index() {
		return redirect(routes.Application.allCustomers());
	}

	public static Result allCustomers() {
		return ok(views.html.index.render(Customer.all(), form(Customer.class)));
	}

	public static Result showCustomer(Long id) {
		return ok(views.html.showCustomer.render(Customer.find.byId(id), form(PurchaseOrder.class)));
	}

	public static Result newCustomer() {
		Form<Customer> form = form(Customer.class).bindFromRequest();
		if(form.hasErrors()) {
			return badRequest(
				views.html.index.render(Customer.all(), form)	
			);					
		} else {
			form.get().save();
			return index();
		}
	}

	public static Result deleteCustomer(Long id) {
		Customer.delete(id);
		return index();
	}
	
	public static Result newOrder(Long customerID) { 
		Form<PurchaseOrder> form = form(PurchaseOrder.class).bindFromRequest();
		if(form.hasErrors()) {
			return badRequest();	
		} else {
			form.get().save();
			return showCustomer(customerID);
		}
	}
	
}