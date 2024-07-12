public class App {
    public static String sevenBoom(int[] arr) {
        String regex = "7";
        for(int i : arr) {
            String s = Integer.toString(i);
            if(s.matches(regex)) {
                return "Boom!";
            }
        }
        return "there is no 7 in the array";
    }
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = {8, 6, 33, 100};
        int[] arr3 = {343, 1, 0, -7, 8, 3, 42};
        System.out.println(sevenBoom(arr1));
        System.out.println(sevenBoom(arr2));
        System.out.println(sevenBoom(arr3));
    }
}
