import java.util.Arrays;

public class CoinMachine {
    public static int[] getCoinBalances(String[] actionsPlayer1, String[] actionsPlayer2) {
        int[] coins = {3, 3};
        
        for (int i = 0; i < actionsPlayer1.length; i++) {
            if (actionsPlayer1[i].equals("share")) {
                coins[0]++;
                coins[1]++;
            } else if (actionsPlayer1[i].equals("steal")) {
                coins[1]++;
            }
            
            if (actionsPlayer2[i].equals("share")) {
                coins[0]++;
                coins[1]++;
            } else if (actionsPlayer2[i].equals("steal")) {
                coins[0]++;
            }
        }
        
        return coins;
    }

    public static void main(String[] args) {

        String[] player1Actions = {"steal", "share"};
        String[] player2Actions = {"share", "share"};
        int[] outputArray = getCoinBalances(player1Actions, player2Actions);
        String output = Arrays.toString(outputArray);
        System.out.printf("Output of getCoinBalances is: %s%n", output);


    }
}
    
