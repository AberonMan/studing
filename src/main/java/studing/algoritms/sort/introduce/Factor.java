package studing.algoritms.sort.introduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Factor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        List<Integer> easyNumbers = calculateEasyNumbers(number);
        System.out.println(easyNumbers.size());
        for (Integer easyNumber : easyNumbers) {
            System.out.print(easyNumber + " ");
        }
    }

    private static List<Integer> calculateEasyNumbers(int number) {

        List<Integer> factors = new ArrayList<>();
        int sum = 0;
        int factor = 1;
        while(sum < number){
            sum += factor;
            factors.add(factor);
            factor++;
        }
        if (sum == number) return factors;
        int distance = sum - number;
        factors.remove(Integer.valueOf(distance));
        return factors;
    }

}
