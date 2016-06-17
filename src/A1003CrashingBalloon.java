
import java.util.*;

/**
 * Created by suhd on 2016-04-07.

 On every June 1st, the Children's Day, there will be a game named "crashing balloon" on TV.   The rule is very simple.  On the ground there are 100 labeled balloons, with the numbers 1 to 100.  After the referee shouts "Let's go!" the two players, who each starts with a score of  "1", race to crash the balloons by their feet and, at the same time, multiply their scores by the numbers written on the balloons they crash.  After a minute, the little audiences are allowed to take the remaining balloons away, and each contestant reports his\her score, the product of the numbers on the balloons he\she's crashed.  The unofficial winner is the player who announced the highest score.

 Inevitably, though, disputes arise, and so the official winner is not determined until the disputes are resolved.  The player who claims the lower score is entitled to challenge his\her opponent's score.  The player with the lower score is presumed to have told the truth, because if he\she were to lie about his\her score, he\she would surely come up with a bigger better lie.  The challenge is upheld if the player with the higher score has a score that cannot be achieved with balloons not crashed by the challenging player.  So, if the challenge is successful, the player claiming the lower score wins.

 So, for example, if one player claims 343 points and the other claims 49, then clearly the first player is lying; the only way to score 343 is by crashing balloons labeled 7 and 49, and the only way to score 49 is by crashing a balloon labeled 49.  Since each of two scores requires crashing the balloon labeled 49, the one claiming 343 points is presumed to be lying.

 On the other hand, if one player claims 162 points and the other claims 81, it is possible for both to be telling the truth (e.g. one crashes balloons 2, 3 and 27, while the other crashes balloon 81), so the challenge would not be upheld.

 By the way, if the challenger made a mistake on calculating his/her score, then the challenge would not be upheld. For example, if one player claims 10001 points and the other claims 10003, then clearly none of them are telling the truth. In this case, the challenge would not be upheld.

 Unfortunately, anyone who is willing to referee a game of crashing balloon is likely to get over-excited in the hot atmosphere that he\she could not reasonably be expected to perform the intricate calculations that refereeing requires.  Hence the need for you, sober programmer, to provide a software solution.

 Input

 Pairs of unequal, positive numbers, with each pair on a single line, that are claimed scores from a game of crashing balloon.
 Output

 Numbers, one to a line, that are the winning scores, assuming that the player with the lower score always challenges the outcome.
 Sample Input

 343 49
 3599 610
 62 36

 Sample Output

 49
 610
 62

 */
public class A1003CrashingBalloon {

    static boolean flagMaxNum = false, flagMinNum = false;

    public static void main(String[] args) {
        int firstNum = 0, secondNum = 0, maxNum = 0, minNum = 0;

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            firstNum = Integer.valueOf(sc.next());
            secondNum = Integer.valueOf(sc.next());

            //find the max one and set its value to maxNum, find the min one and set its value to minNum.
            if(firstNum > secondNum) {
                maxNum = firstNum;
                minNum = secondNum;
            }else {
                maxNum = secondNum;
                minNum = firstNum;
            }

            flagMaxNum = false;
            flagMinNum = false;
            //if there's a same num they both used, then maxNum takes the first decouple action.
            dfs(maxNum,minNum,100);

            //has no collision between two valid value, minNum and maxNum both be decoupled.
            if(flagMaxNum && flagMinNum) {
                System.out.println(maxNum);
            }else {
                //there's a collsion between two valid value, and minNum be decoupled, maxNum could not.
                if(!flagMaxNum && flagMinNum) {
                    System.out.println(minNum);
                }
                //minNum and maxNum could not decoupled. the challenge would not be upheld.
                else {
                    System.out.println(maxNum);
                }
            }
        }
    }

    /**
     * use dfs to solve the problem.
     *
     * if there's a same num they both used, maxNum decoupled and minNum decoupled, then maxNum is the result.
     *
     * @param maxNum
     * @param minNum
     * @param fac
     */
    private static void dfs(int maxNum, int minNum, int fac) {
        if(maxNum==1 && minNum==1) {
            flagMaxNum = true;
            flagMinNum = true;
            return;
        }
        if(minNum == 1) {
            flagMinNum = true;
        }
        if(fac < 2) {
            return;
        }
        if(maxNum%fac == 0) {
            dfs(maxNum/fac,minNum,fac-1);
        }
        if(minNum%fac == 0) {
            dfs(maxNum,minNum/fac,fac-1);
        }
        dfs(maxNum,minNum,--fac);
    }

}
