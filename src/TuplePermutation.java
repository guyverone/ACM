/**
 * Created by guyver on 2016/6/19.
 */
public class TuplePermutation {
    static int[] solution = new int[5];

    public static void  main(String[] args) {
        backtrack(0);
    }

    public static void printSolution() {
        for(int i=0; i<solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
        System.out.println();
    }

    public static void backtrack(int n) {
        if(n == 5) {
            printSolution();
            return;
        }
        solution[n] = 1;
        backtrack(n+1);
        solution[n] = 2;
        backtrack(n+1);
        solution[n] = 3;
        backtrack(n+1);
        solution[n] = 4;
        backtrack(n+1);
        solution[n] = 5;
        backtrack(n+1);
        solution[n] = 6;
        backtrack(n+1);
        solution[n] = 7;
        backtrack(n+1);
        solution[n] = 8;
        backtrack(n+1);
        solution[n] = 9;
        backtrack(n+1);
        solution[n] = 10;
        backtrack(n+1);
    }
}
