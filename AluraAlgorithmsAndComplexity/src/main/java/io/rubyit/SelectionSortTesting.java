package io.rubyit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class SelectionSortTesting {

	public static void main(String[] args) {
		List<Sortable> unsortedCars = ImmutableList.of(
				  new Car("Laborghine", 100000.0)
				, new Car("Fusca", 17000.0)
				, new Car("Jipe", 46000.0)
				, new Car("Brasilia", 16000.0)
				, new Car("Smart", 46000.0)
				);
		List<Sortable> sortedCars = selectionSort(unsortedCars, unsortedCars.size());

		System.out.println("The unsorted cars are : " + unsortedCars);
		System.out.println("The sorted cars are : " + sortedCars);
	}

	public static List<Sortable> selectionSort(List<Sortable> unsortedList, int sortableAmount) {
		Sortable[] sortables = unsortedList.toArray(new Sortable[unsortedList.size()]);

		for (int current = 0; current < sortableAmount - 1; current++) {
//			System.out.println("Estou na casinha " + atual);

			int minor = selectMinorIndex(sortables, current, sortableAmount - 1);
//	        System.out.println("Trocando " + atual + " com o " + menor);
			Sortable currentOrdenable = sortables[current];
			Sortable currentMinor = sortables[minor];

//	        System.out.println("Trocando "+ produtoAtual.getName() + " " + produtoMenor.getName());

			sortables[current] = currentMinor;
			sortables[minor] = currentOrdenable;
		}

		return Arrays.asList(sortables);
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
