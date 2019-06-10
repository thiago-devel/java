package io.rubyit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class InsertionSortTesting {

	public static void main(String[] args) {
		List<Sortable> unsortedCars = ImmutableList.of(
				  new Car("Laborghine", 100000.0)
				, new Car("Fusca", 17000.0)
				, new Car("Jipe", 46000.0)
				, new Car("Brasilia", 16000.0)
				, new Car("Smart", 46000.0)
				);
		List<Sortable> sortedCars = insertionSort(unsortedCars, unsortedCars.size());

		System.out.println("The unsorted cars are : " + unsortedCars);
		System.out.println("The sorted cars are : " + sortedCars);
	}

	public static List<Sortable> insertionSort(List<Sortable> unsortedList, int sortableAmount) {
		Sortable[] sortables = unsortedList.toArray(new Sortable[unsortedList.size()]);

		for (int current = 1; current < sortableAmount; current++) {
			int searching = current;
			while (searching > 0 && ((Double)sortables[searching].getSortCompare()) < ((Double) sortables[searching - 1].getSortCompare()) ) {
				sortables = changePositions(sortables, searching, searching - 1);
				searching = searching - 1; //searching--
			}
			
		}

		return Arrays.asList(sortables);
	}

	public static Sortable[] changePositions(Sortable[] sortables, int first, int second) {
		Sortable firstOrdenable = sortables[first];
		Sortable secondOrdenable = sortables[second];
		
		sortables[first] = secondOrdenable;
		sortables[second] = firstOrdenable;
		
		return sortables;
	}

	public static int selectMinorIndex(Sortable[] sortable, int offset, int limit) {
		int cheapestIndex = offset;
		for (int current = offset; current <= limit; current++) {
			if (((Double) sortable[current].getSortCompare()) < ((Double) sortable[cheapestIndex].getSortCompare())) {
				cheapestIndex = current;
			}
		}
		return cheapestIndex;
	}

}
