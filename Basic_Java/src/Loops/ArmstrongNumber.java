package Loops;

import java.util.Scanner;

public class ArmstrongNumber {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = s.nextInt();
        s.close();
        
        int originalNumber = num;
        int numOfDigits = 0;
        int temp = num;
        
        
        while (temp != 0) {
            temp /= 10;
            numOfDigits++;
        }
        
        int sum = 0;
        
        
        while (num > 0) {
            int digit = num % 10;
            sum += Math.pow(digit, numOfDigits);
            num /= 10;
        }
        
        if (sum == originalNumber) {
            System.out.println(originalNumber + " is an Armstrong number.");
        } else {
            System.out.println(originalNumber + " is not an Armstrong number.");
        }
    }
}
