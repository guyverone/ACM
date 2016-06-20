/**
 * Created by suhd on 2016-06-17.
 */
public class Permutation {
    static String[] s = {"a","b","c"};
    static String[] solution = new String[3];
    static boolean used[] = new boolean[3];

    public static void permutation(int k, int n) {
        if(k == n) {
            for(int i=0; i<n; i++) {
                System.out.print(solution[i]);
            }
            System.out.println();
        }else {
            for(int i=0; i<n; i++) {
                if(!used[i]) {
                    used[i] = true;
                    solution[k] = s[i];
                    permutation(k+1,n);
                    used[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int k=0;
        permutation(0,3);
    }
}
