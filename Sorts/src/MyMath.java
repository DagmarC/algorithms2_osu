public class MyMath {

    public static void main(String[] args) {
        String s = "Abc";
        System.out.println(reverse(s));
    }

    public static String reverse(String s) {
        if (s.length() <= 1) return s;

        return reverseRec(s, 0);
    }

    private static String reverseRec(String s, int i) {
        if (i >= s.length()) return "";

        String result = reverseRec(s, i + 1);
        return result + s.charAt(i);
    }

    public static int Fibonacci(int n) {
        if (n <= 1) return n;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public static int[] insertToSortedArray(int[] array, int value) {
        if (array == null || array.length == 0)
            return new int[]{value};

        int n = array.length - 1;
        int[] result = new int[n+2];

        // Copy the first part - greater than value
        while (n >= 0 && array[n] > value) {
            result[n+1] = array[n];
            n--;
        }
        // Insert the value
        result[n+1] = value;

        // Copy the rest - smaller than value
        // System.arraycopy(array, 0, result, 0, n);
        while (n >= 0) {
            result[n] = array[n];
            n--;
        }
        return result;
    }
}
