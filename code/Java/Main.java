public class Main {
    public static void main(String[] args) {
        System.out.println(wordprocessor(7, "hello my name is Bessie and this is my essay"));

        System.out.println(wordprocessor(18,
                    "This is some text to test the functionality of this method."+
                    " I should really be using predefined strings but I'm not going to"+
                    " because this is easier."));
    }

    public static String wordprocessor(int k, String e) {
        StringBuilder result = new StringBuilder();
        String[] words = e.split(" ");
        int charCount = 0;

        for (int i = 0; i < words.length; i++) {
            if (charCount + words[i].length() > k) {
                result.append("\n");
                charCount = 0;
            }

            if (charCount > 0) {
                result.append(" ");
                charCount++;
            }

            result.append(words[i]);
            charCount += words[i].length();
        }

        return result.toString();
    }
}
