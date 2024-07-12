import java.io.*;
import java.util.*;
public class FibonacciSequence{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        List<Integer> fibonacciList = new ArrayList<>();
        fibonacciList.add(0);
        fibonacciList.add(1);
        printFibonacciSequence(n,fibonacciList);
        saveFibonacciSequenceToFile(fibonacciList);
    }
    public static void printFibonacciSequence(int n, List<Integer> fibonacciList){
        if(fibonacciList.get(fibonacciList.size()-1)<=n){
            fibonacciList.add(fibonacciList.get(fibonacciList.size()-1)+fibonacciList.get(fibonacciList.size()-2));
            printFibonacciSequence(n,fibonacciList);
        }
        else{
            System.out.print(String.join(",", fibonacciList.toString()));
        }
    }
    public static void saveFibonacciSequenceToFile(List<Integer> fibonacciList){
        try{
            FileWriter fileWriter = new FileWriter("FibonacciSequence.txt");
            for(int i: fibonacciList){
                fileWriter.write(i+",");
            }
            fileWriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
