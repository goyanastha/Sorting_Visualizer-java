package Visualizer.Sorts;

import Visualizer.SortingVisualizer;

public class QuickSort implements Runnable {

    // Run method that initiates the sorting process
    public void run() {
        Integer[] toBeSorted = SortingVisualizer.toBeSorted;
        inPlaceSort(toBeSorted);
        SortingVisualizer.isSorting = false;
    }

    // Public method to start the quicksort
    public void inPlaceSort(Integer[] array) {
        inPlaceSort(array, 0, array.length - 1);
    }

    // Main recursive quicksort method
    private void inPlaceSort(Integer[] array, int low, int high) {
        // Base case: if the partition size is 1 or less, we're done
        if (low >= high) return;

        // Partition the array and get the pivot position
        int pivotPos = partition(array, low, high);

        // Recursively sort the left and right portions
        inPlaceSort(array, low, pivotPos - 1);
        inPlaceSort(array, pivotPos + 1, high);
    }

    // Partition method that arranges elements around a pivot
    private int partition(Integer[] array, int low, int high) {
        // Choose the rightmost element as pivot
        int pivot = array[high];

        // Index of smaller element
        int i = low - 1;

        // Traverse through all elements
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (array[j] <= pivot) {
                i++; // Increment index of smaller element

                // Swap elements
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                // Visualize the current state
                // i is working position (green)
                // j is comparing position (red)
                // high is reading position (yellow)
                SortingVisualizer.frame.reDrawArray(array, i, j, high);

                // Add delay for visualization
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Place pivot in its final position
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        // Visualize the final swap for this partition
        SortingVisualizer.frame.reDrawArray(array, i + 1, high, -1);
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return the position of the pivot
        return i + 1;
    }
}
