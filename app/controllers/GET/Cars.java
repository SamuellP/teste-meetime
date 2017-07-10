package controllers.GET;

import play.*;
import play.mvc.*;
import play.libs.Json;
import models.CarsMap;
import java.util.HashMap;
import models.Car;

public class Cars extends Controller {
    
	public Result getCars() {
        CarsMap carsMap = new CarsMap();

        return ok(Json.toJson(carsMap.getCars()));
    }

    public Result getCar(String id){
        CarsMap carsMap = new CarsMap();
        Car car;

        if(carsMap.getCars().keySet().contains(Integer.parseInt(id))){
            car = carsMap.getCars().get(Integer.parseInt(id));
            return ok(Json.toJson(car));
        }else{
            return badRequest("CARRO INEXISTENTE");
        }
    }
    
    
}
