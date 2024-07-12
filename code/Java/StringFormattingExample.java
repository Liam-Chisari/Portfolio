public class StringFormattingExample {

  public static void main(String[] args) {
    String label1 = "Test:";
    int value1 = 1;

    String label2 = "Test two:";
    int value2 = 2;

    // Calculate the maximum label width for consistent alignment
    int maxLabelWidth = Math.max(label1.length(), label2.length());

    // Format and print the lines
    System.out.printf("%-" + maxLabelWidth + "s %d\n", label1, value1);
    System.out.printf("%-" + maxLabelWidth + "s %d\n", label2, value2);
  }
}
