package controllers.PUT;

import play.*;
import play.mvc.*;

import play.data.FormFactory;
import play.data.Form;
import javax.inject.*;

import models.CarsMap;
import models.Car;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.Json.*;

import java.util.HashMap;



//@BodyParser.Of(play.mvc.BodyParser.Json.class)
public class Cars extends Controller {

	@Inject FormFactory formFactory;

    public Result saveCarEdition() {

    	Form<Car> carForm = formFactory.form(Car.class).bindFromRequest();

    	if (carForm.hasErrors()) {
    		return badRequest(carForm.errorsAsJson());
		}else{
	    	Car car = carForm.bindFromRequest().get();

	   		CarsMap carsMap = new CarsMap();
			carsMap.setCar(car.getId(), car);

		    return ok("Carro modificado com sucesso");
		}
    }

}