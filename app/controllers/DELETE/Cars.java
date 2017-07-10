package controllers.DELETE;

import play.*;
import play.mvc.*;
import play.libs.Json;
import models.CarsMap;
import java.util.HashMap;

public class Cars extends Controller {

    public Result deleteCar(String id){
        CarsMap carsMap = new CarsMap();

        if(carsMap.getCars().keySet().contains(Integer.parseInt(id))){
            carsMap.deleteCar(Integer.parseInt(id));
            return ok("CARRO DELETADO COM SUCESSO!");
        }else{
            return badRequest("CARRO INEXISTENTE");
        }
    }
    
    
}
