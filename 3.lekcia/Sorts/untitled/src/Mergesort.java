import java.util.Arrays;

public class Mergesort {
    /**
     * mergesort divides the array recursively until only one element is trivially sorted and then it merges
     * it into one array via comparing elements from both arrays.
     * T(n) = O(n*log(n))
     * **/
    public static void mergesort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivot = (left + right) / 2;

        // 'Divide' the array until trivially sorted elements
        mergesort(arr, left, pivot);
        mergesort(arr, pivot + 1, right);

        // Merge two arrays into one
        merge(arr, left, pivot, right);
    }

    private static void merge(int[] arr, int left, int pivot, int right) {
        // Create 2 new sub arrays LEFT and RIGHT, that will be merged afterward
        int leftSize = pivot - left + 1;  // size of the left subarray
        int rightSize = right - pivot; // size of the right subarray

        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        // Fill in the subarrays
        fillInArray(arr, leftArr, leftSize, left);
        fillInArray(arr, rightArr, rightSize, pivot+1);

        // left and right indices of the subarrays
        int i = 0;
        int j = 0;

        // Merge 2 subarrays into the final array
        for (int l = left; l <= right; l++) {
            // left subarray is already inserted
            if (i >= leftSize && j < rightSize) {
                arr[l] = rightArr[j];
                j++;
            }
            // right subarray is already inserted
            else if (j >= rightSize && i < leftSize) {
                arr[l] = leftArr[i];
                i++;
            } else if (i >= leftSize && j >= rightSize) {
                break;
            }
            // both arrays are being compared
            else if (leftArr[i] < rightArr[j]) {
                arr[l] = leftArr[i];
                i++;
            } else {
                arr[l] = rightArr[j];
                j++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void fillInArray(int[] source, int[] dest, int size, int left) {
        for (int i = 0; i < size; i++) {
            dest[i] = source[left + i];
        }
    }
}
