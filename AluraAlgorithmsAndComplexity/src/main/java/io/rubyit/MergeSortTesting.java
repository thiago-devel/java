package io.rubyit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class MergeSortTesting {

	public static void main(String[] args) {
		List<Sortable> carList1 = ImmutableList.of(
				  new Car("Brasilia", 16000.0)
				, new Car("Fusca", 17000.0)
				, new Car("Jipe", 46000.0)
				, new Car("Smart", 46000.0)
				, new Car("Laborghine", 1000000.0)
				);
		List<Sortable> carList2 = ImmutableList.of(
				  new Car("Scort", 22000.0)
				, new Car("Palio", 25000.0)
				, new Car("Fit", 41000.0)
				, new Car("Camaro", 316000.0)
				);
		List<Sortable> sortedCars = mergeSort(carList1, carList1.size(), carList2, carList2.size());

		System.out.println("The car list 1 are : " + carList1);
		System.out.println("The car list 2 are : " + carList2);
		System.out.println("The sorted cars are : " + sortedCars);
	}

	public static List<Sortable> mergeSort(List<Sortable> list1, int list1Size, List<Sortable> list2, int list2Size) {
		Sortable[] alist1 = list1.toArray(new Sortable[list1Size]);
		Sortable[] alist2 = list2.toArray(new Sortable[list1Size]);
		Sortable[] result = new Sortable[list1Size + list2Size];
		int current = 0;
		int cursor1 = 0;
		int cursor2 = 0;
		
		while (cursor1 < list1Size && cursor2 < list2Size) {
			Sortable item1 = alist1[cursor1];
			Sortable item2 = alist2[cursor2];
			
			if (((Double) item1.getSortCompare()) < ((Double) item2.getSortCompare())) {
				result[current] = item1;
				cursor1++;
			} else {
				result[current] = item2;
				cursor2++;
			}
			
			current++;
		}
		
		while (cursor1 < list1Size) {
			result[current] = alist1[cursor1];
			cursor1++;
			current++;
		}
		while (cursor2 < list2Size) {
			result[current] = alist2[cursor2];
			cursor2++;
			current++;
		}
		
		return Arrays.asList(result);
	}

}
