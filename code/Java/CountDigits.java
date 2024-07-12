public class CountDigits {


    public static int countDigits(int n) {
     if (n == 0) {
         return 1;
     }
     return (int) Math.floor(Math.log10(Math.abs(n))) + 1;
     }


    public static void main(String[] args) {

     System.out.println(countDigits(318));   
     System.out.println(countDigits(-92563));
     System.out.println(countDigits(654321));
     System.out.println(countDigits(0));

    }


}
