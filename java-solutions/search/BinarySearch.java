package search;

public class BinarySearch {

    // Pre: args.length > 0 && args[0] это x - целое число
    //      && args\args[0] - массив целых чисел, отсортированный по невозрастанию
    ///     && элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
    // Post: i - минимальное значение, при котором array[i] <= x && i >= 0
    public static void main(String[] args) {
        // args.length > 0 && args[0] это x - целое число
        // && args\args[0] - массив целых чисел, отсортированный по невозрастанию
        // && элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
        int x = Integer.parseInt(args[0]);
        // args.length > 0 && args[0] это x - целое число && x = args[0]
        // && args\args[0] - массив целых чисел, отсортированный по невозрастанию
        // && элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
        int[] array = new int[args.length - 1];
        // args.length > 0 && args[0] это x - целое число && x = args[0] && array.length = args.length - 1
        // && args\args[0] - массив целых чисел, отсортированный по невозрастанию
        // && элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
        int j = 1;
        // args.length > 0 && args[0] это x - целое число && x = args[0] && array.length = args.length - 1
        // && args\args[0] - массив целых чисел, отсортированный по невозрастанию && j = 1
        // && элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
        // Inv: args.length > 0 && args\args[0] - массив целых чисел, отсортированный по невозрастанию
        //      && x = args[0] && array.length = args.length - 1 && j = j' + 1 && j > 0
        //      && j <= args.length && array[0..j - 2] - массив целых чисел, отсортированный по невозрастанию
        //      && элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
        while(j < args.length) {
            // args.length > 0 && args\args[0] - массив целых чисел, отсортированный по невозрастанию &&
            // x = args[0] && array.length = args.length - 1 && j = j' + 1 && j > 0 &&
            // j < args.length &&
            // элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
            array[j - 1] = Integer.parseInt(args[j]);
            // args.length > 0 && args\args[0] - массив целых чисел && x = args[0] &&
            // array.length = args.length - 1 && j = j' + 1 && j > 0 && j < args.length &&
            // array[0..j - 1] - массив целых чисел, отсортированный по невозрастанию &&
            // элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
            j++;
            // args.length > 0 && args\args[0] - массив целых чисел && x = args[0] &&
            // array.length = args.length - 1 && j = j' + 1 && j > 0 && j <= args.length &&
            // array[0..j - 2] - массив целых чисел, отсортированный по невозрастанию &&
            // элементы массива args принадлежат диапозону [Integer.MIN_VALUE, Integer.MAX_VALUE]
        }
        // args.length > 0 && array.length = args.length - 1 &&
        // x - целое число && array - массив целых чисел, отсортированный по невозрастанию
        int i = BinarySearchIterative(x, array);
        // args.length > 0 && array.length = args.length - 1 &&
        // x - целое число && array - массив целых чисел, отсортированный по невозрастанию &&
        // i - минимальное значение, при котором array[i] <= x && i >= 0
        System.out.println(i);
        // i - минимальное значение, при котором array[i] <= x && i >= 0
    }

    // Pre: x - целое число && array - массив целых чисел, отсортированный по невозрастанию
    // Post: r - минимальное значение, при котором array[r] <= x && r >= 0
    private static int BinarySearchIterative(int x, int[] array) {
        // x - целое число && array - массив целых чисел, отсортированный по невозрастанию
        int l = -1, r = array.length;
        // x - целое число && array - массив целых чисел, отсортированный по невозрастанию &&
        // l = -1 && r = array.length
        // Inv: array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 0
        while (r - l > 1) {
            // array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 1
            int m = (l + r) / 2;
            // array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 0 && m = (l + r) / 2 < r
            if (array[m] <= x) {
                // array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 0 && m < r && array[m] <= x
                r = m;
                // array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 0
            } else {
                // array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 0 && m < r && array[m] > x
                l = m;
                // array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 0
            }
            // array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 0
        }
        // array[l] > x && array[r] <= x && r - l < r' - l' && r - l > 0 &&
        // r - минимальное значение, при котором array[r] <= x && r >= 0
        return r;
    }

    // Pre: x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
    //      r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 && r - l > 0
    // Post: r - минимальное значение, при котором array[r] <= x && r >= 0
    private static int BinarySearchRecursive(int l, int r, int x, int[] array) {
        // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
        // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 && r - l > 0
        if (r - l > 1) {
            // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
            // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 &&
            // r - l > 1
            int m = (l + r) / 2;
            // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
            // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 &&
            // r - l > 1 && m < r
            if (array[m] <= x) {
                // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
                // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 &&
                // r - l > 1 && array[m] <= x && m < r
                r = m;
                // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
                // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 &&
                // r - l > 0 && && r - l < r' - l'
            } else {
                // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
                // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 &&
                // r - l > 1 && array[m] > x && m >= l
                l = m;
                // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
                // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 &&
                // r - l > 0 && r - l < r' - l'
            }
            // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
            // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 &&
            // r - l > 0 && r - l < r' - l'
            return BinarySearchRecursive(l, r, x, array);
        } else {
            // x - целое число && array - массив целых чисел, отсортированный по невозрастанию && l >= -1 &&
            // r <= array.length && i in (l, r] - минимальное значение, при котором array[i] <= x && i >= 0 &&
            // r - l < 2 && i = r
            return r;
        }
    }
}
