package models;


import java.util.HashMap;


public class CarsMap {
    
    public static HashMap<Integer,Car> cars = new HashMap<Integer,Car>();
    public static int id = 1;
    
    public void insertCar(Car car){
        cars.put(id, car);
        setId(id+1);
    }

    public void setCar(int id, Car car){
    	cars.put(id, car);
    }

    public void deleteCar(int id){
    	cars.remove(id);
    }
    
    public HashMap<Integer,Car> getCars(){
        return cars;
    }

    public void setCars(HashMap<Integer,Car> cars){
    	this.cars = cars;
    }
    
    public void delete(int id){
        cars.remove(id);
    }

    public int getId(){
    	return id;
    }

    public void setId(int id){
    	this.id = id;
    }
    
}
