package com.hust.soict.khang.helper;

public class BubbleSort implements NumberSorter{

	@Override
	public void sort(int[] arr) {
		boolean sorted;
		for(int i = 0; i< arr.length; i++) {
			sorted = true;
			for(int j = 1; j< arr.length - 1; j++) {
				if(arr[j - 1] > arr[j]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
					sorted = false;
				}
			}
			if(sorted) break;
		}
	}
	
}
