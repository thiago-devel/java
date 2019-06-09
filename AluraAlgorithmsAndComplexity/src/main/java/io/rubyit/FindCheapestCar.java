package io.rubyit;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class FindCheapestCar {

	public static void main(String[] args) {
		List<Car> cars = ImmutableList.of(
				  new Car("Laborghine", 100000.0)
				, new Car("Jipe", 46000.0)
				, new Car("Smart", 46000.0)
				, new Car("Fusca", 17000.0)
				, new Car("Brasilia", 16000.0)
				
				);

		System.out.println("The cheapeast car is: " + retrieveCheapestCar(cars, 0, cars.size()));
	}

	public static Car retrieveCheapestCar(List<Car> cars, int offset, int limit) {
		int cheapest = offset;
		Car result = null;
		for(int current = 0; current < limit; current++) {
			if (cars.get(current).getPrice() < cars.get(cheapest).getPrice()) {
				cheapest = current;
			}
		}
		result = cars.get(cheapest);
		
		return result;
	}

}
