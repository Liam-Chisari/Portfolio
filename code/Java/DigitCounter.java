public class DigitCounter {
    public static int count(int n) {
        if (n == 0) {
            return 0;
        } else {
            return 1 + count(n / 10);
        }
    }
 
    public static void main(String[] args) {
        int num = 318;
        System.out.println("Number of digits in " + num + " is: " + count(num));
 
        num = -92563;
        System.out.println("Number of digits in " + num + " is: " + count(num));

        num = 654321;
        System.out.println("Number of digits in " + num + " is: " + count(num));
        num = 0;

        System.out.println("Number of digits in " + num + " is: " + count(num));
    }
 }
