
import java.util.Arrays;
public class CoinGame {
    public static int[] getCoinBalances(String[] actions1, String[] actions2) {
        int player1Coins = 3;
        int player2Coins = 3;

        for (int i = 0; i < Math.max(actions1.length, actions2.length); i++) {
            String action1 = i < actions1.length ? actions1[i] : "share";
            String action2 = i < actions2.length ? actions2[i] : "share";

            if (action1.equals("share")) {
                player1Coins--;
                player2Coins += 3;
            } else if (action2.equals("share")) {
                player2Coins--;
                player1Coins += 3;
            }
        }

        return new int[]{player1Coins, player2Coins};
    }

    public static void main(String[] args) {
        /*
        String[] actions1 = {"share", "share", "share"};
        String[] actions2 = {"steal", "share", "steal"};
        int[] balances = getCoinBalances(actions1, actions2);
        System.out.println("Player 1 coins: " + balances[0]);
        System.out.println("Player 2 coins: " + balances[1]);
        */


        String[] player1Actions = {"steal", "share"};
        String[] player2Actions = {"share", "share"};
        int[] outputArray = getCoinBalances(player1Actions, player2Actions);
        String output = Arrays.toString(outputArray);
        System.out.printf("Output of getCoinBalances is: %s%n", output);
    }
}
    
