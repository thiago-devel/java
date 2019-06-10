package io.rubyit;

public class Car implements Sortable {

	private String name;
	private Double price;

	public Car(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", price=" + price + "]";
	}

	@Override
	public Number getSortCompare() {
		return getPrice();
	}
}
