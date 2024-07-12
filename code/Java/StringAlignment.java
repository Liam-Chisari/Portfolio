public class StringAlignment {
    public static void main(String[] args) {
        System.out.printf("%-10s %10s%n", "Test:", "1");
        System.out.printf("%-10s %10s%n", "Test two:", "2");

        String line1 = String.format("%-10s %10s", "Test:", "1");
        String line2 = String.format("%-10s %10s", "Test two:", "2");

        System.out.println(line1);
        System.out.println(line2);


    }
}
