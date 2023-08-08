package multithreading_practice;

import java.util.*;

public class NumberOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int input = scanner.nextInt();

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < input; i++) {
            numbers.add(i);
        }

        System.out.println("Original List: " + numbers);

        List<Integer> reversedList = reverseList(numbers);
        System.out.println("Reversed List: " + reversedList);

        List<Integer> fibonacciSequence = generateFibonacci(input);
        System.out.println("Fibonacci Sequence: " + fibonacciSequence);

        int sum = calculateSum(numbers);
        System.out.println("Sum of all numbers below " + input + ": " + sum);
        
        scanner.close();
    }

    private static List<Integer> reverseList(List<Integer> list) {
        List<Integer> reversedList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reversedList.add(list.get(i));
        }
        return reversedList;
    }

    private static List<Integer> generateFibonacci(int n) {
        List<Integer> fibonacciSequence = new ArrayList<>();
        if (n >= 1) {
            fibonacciSequence.add(0);
        }
        if (n >= 2) {
            fibonacciSequence.add(1);
        }
        for (int i = 2; i < n; i++) {
            int nextNumber = fibonacciSequence.get(i - 1) + fibonacciSequence.get(i - 2);
            fibonacciSequence.add(nextNumber);
        }
        return fibonacciSequence;
    }

    private static int calculateSum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

}
