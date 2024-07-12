public class FactorialChecker {
public static boolean isFactorial(int n) {
    int count = 1;
    
    for (int i = 2; i <= n; i++) {
        if (count > n) {
            return false;
        }
        count *= i;
    }
    
    return count == n;
}
   public static void main(String[] args) {
       System.out.println(isFactorial(2));  // true
       System.out.println(isFactorial(27)); // false
       System.out.println(isFactorial(24)); // true
       System.out.println(isFactorial(120)); // true
   }
}
