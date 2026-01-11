import java.util.Comparator;
import java.util.List;

// We can use Integer, Double, String or Object Astronaut.
// Prerequisite of Object:

public class QuickSort<T> {

    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.isEmpty()) {
            return;
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private void quickSort(List<T> list, int start, int end, Comparator<T> cmp) {
        if (start >= end) {
            return; // base case
        }

        int pivotIndex = partition(list, start, end, cmp);

        quickSort(list, start, pivotIndex - 1, cmp);
        quickSort(list, pivotIndex + 1, end, cmp);
    }

    private int partition(List<T> list, int start, int end, Comparator<T> cmp) {

        T pivot = list.get(end);

        int i = start - 1;
        for (int j = start; j < end; j++) {
            T current = list.get(j);

            if (cmp.compare(current, pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        // Place pivot to the correct place
        i++;
        swap(list, i, end);
        return i;
    }

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}