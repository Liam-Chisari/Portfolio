public class Split {
    public static void main(String[] args) {

        String someString = "Hello.txt";
        //System.out.printf("String is: %s %n", someString);
        String[] splitString = someString.split(".");
        System.out.printf("Length of splitString is:%d %n", splitString.length);
        //System.out.printf("splitString[0] is: %s %n", splitString[0]);



    }


}
