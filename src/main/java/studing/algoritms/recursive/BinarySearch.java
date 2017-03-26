package studing.algoritms.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author mash0916
 */
public class BinarySearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrayLength = scanner.nextInt();
        int[] array = new int[arrayLength];
        int i = 0;
        while (arrayLength != i) {
            array[i++] = scanner.nextInt();
        }
        int searchNumberCount = scanner.nextInt();
        i = 0;
        int[] searchNumbers = new int[searchNumberCount];
        while (searchNumberCount != i) {
            searchNumbers[i++] = scanner.nextInt();
        }
        List<Integer> results = new ArrayList<>();
        for (int searchNumber : searchNumbers) {
            int numberIndex = binarySearch(array, searchNumber, 0, array.length - 1);
            results.add(numberIndex == -1 ? numberIndex : numberIndex + 1);
        }

        StringBuilder builder = new StringBuilder();

        for (Integer result : results) {

            builder.append(result).append(" ");
        }
        System.out.println(builder.toString());

    }


    private static int binarySearch(int[] array, int searchNumber, int begin, int end) {
        int midIndex = (begin + end) / 2;
        int middleElement = array[midIndex];
        if ((begin == midIndex || midIndex == end) && middleElement != searchNumber) {
            if (array[begin] == searchNumber) return begin;
            if (array[end] ==  searchNumber) return end;
            return -1;
        }
        if (searchNumber < middleElement) {
            return binarySearch(array, searchNumber, begin, midIndex);
        } else if (searchNumber > middleElement) {
            return binarySearch(array, searchNumber, midIndex, end);
        }
        return midIndex;
    }
}
