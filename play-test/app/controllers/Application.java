package controllers;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import models.Customer;
import models.PurchaseOrder;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result index() {
		return redirect(controllers.routes.Application.allCustomers());
	}

	public static Result allCustomers() {
		return ok(views.html.index.render(Customer.all(), form(Customer.class)));
	}

	public static Result showCustomer(Long id) {
		return ok(views.html.showCustomer.render(Customer.find.byId(id), form(PurchaseOrder.class)));
	}
	
	public static Result showCustomerAsFormat(Long id, String format) throws JsonGenerationException, JsonMappingException, IOException {
		if(format.equals("xml")) {
			StringWriter response = new StringWriter();
			JAXB.marshal(Customer.find.byId(id), response);
			return ok(response.toString()).as("application/xml");
		} 
		else if(format.equals("json")) {
			ObjectMapper jsonMapper = new ObjectMapper();
			return ok(jsonMapper.writeValueAsString(Customer.find.byId(id))).as("application/json");
		}
		else
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
		Form<PurchaseOrder> form = form(PurchaseOrder.class).bindFromRequest("orderNumber", "orderDate");
		if(form.hasErrors()) {
			return badRequest();	
		} else {
			form.get().save();
			return showCustomer(customerID);
		}
	}
	
}