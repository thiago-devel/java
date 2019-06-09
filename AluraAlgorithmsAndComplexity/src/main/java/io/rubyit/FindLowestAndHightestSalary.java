package io.rubyit;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class FindLowestAndHightestSalary {

	public static void main(String[] args) {
		Map<String, Double> salaries = new HashMap<>();
		salaries.put("Fernando", 3200.0);
		salaries.put("Alfredo", 6000.0);
		salaries.put("Flavio", 5000.0);
		salaries.put("Flavia", 2200.0);
		
		System.out.println("The lowest salary is: " + retrieveLowestSalary(salaries));
		System.out.println("The highest salary is: " + retrieveHighestSalary(salaries));
	}
	
	public static Map<String, Double> retrieveLowestSalary(Map<String, Double> salaries) {
		Double lowest = Double.MAX_VALUE;
		String employee = "";
		Double atual = 0.0;
		for (Map.Entry<String, Double> item : salaries.entrySet()) {
			atual = item.getValue();
			if (atual < lowest) {
				employee = item.getKey();
				lowest = item.getValue();
			}
		}
		
		return ImmutableMap.of(employee, lowest);
	}
	public static Map<String, Double> retrieveHighestSalary(Map<String, Double> salaries) {
		Double highest = 0.0;
		String employee = "";
		Double atual = 0.0;
		for (Map.Entry<String, Double> item : salaries.entrySet()) {
			atual = item.getValue();
			if (atual > highest) {
				employee = item.getKey();
				highest = item.getValue();
			}
		}
		
		return ImmutableMap.of(employee, highest);
	}
	
}
