
import java.util.*;

/**
 * Created by suhd on 2016-04-05.
 */

/**
 * ZOJ 1002
 *
 *
 Suppose that we have a square city with straight streets. A map of a city is a square board with n rows and n columns, each representing a street or a piece of wall.

 A blockhouse is a small castle that has four openings through which to shoot. The four openings are facing North, East, South, and West, respectively. There will be one machine gun shooting through each opening.

 Here we assume that a bullet is so powerful that it can run across any distance and destroy a blockhouse on its way. On the other hand, a wall is so strongly built that can stop the bullets.

 The goal is to place as many blockhouses in a city as possible so that no two can destroy each other. A configuration of blockhouses is legal provided that no two blockhouses are on the same horizontal row or vertical column in a map unless there is at least one wall separating them. In this problem we will consider small square cities (at most 4x4) that contain walls through which bullets cannot run through.

 The following image shows five pictures of the same board. The first picture is the empty board, the second and third pictures show legal configurations, and the fourth and fifth pictures show illegal configurations. For this board, the maximum number of blockhouses in a legal configuration is 5; the second picture shows one way to do it, but there are several other ways.


 Your task is to write a program that, given a description of a map, calculates the maximum number of blockhouses that can be placed in the city in a legal configuration.

 The input file contains one or more map descriptions, followed by a line containing the number 0 that signals the end of the file. Each map description begins with a line containing a positive integer n that is the size of the city; n will be at most 4. The next n lines each describe one row of the map, with a '.' indicating an open space and an uppercase 'X' indicating a wall. There are no spaces in the input file.

 For each test case, output one line containing the maximum number of blockhouses that can be placed in the city in a legal configuration.


 Sample input:

 4
 .X..
 ....
 XX..
 ....
 2
 XX
 .X
 3
 .X.
 X.X
 .X.
 3
 ...
 .XX
 .XX
 4
 ....
 ....
 ....
 ....
 0
 Sample output:

 5
 1
 5
 2
 4
 */
public class A1002FireNet {
    public static void main(String[] args) {int i=0;
        String[][] mapArray = null;

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String obj = sc.next();
            if(isObjNum(obj)) {
                int num = Integer.valueOf(obj);yyyyyyyy
                //0 means to end this program.
                if(num==0) {
                    sc.close();
                    return;
                }
                //the map has most 4*4 square
                else if(num<=4){
                    mapArray = new String[num][num];

                    //initial mapArray with given value
                    for(int i=0; i<mapArray.length && sc.hasNext(); i++) {
                        fillValueIntoMapArray(sc.next(),mapArray,i);
                    }
                    //place blockhouses to right places
                    placeBlockHouses(mapArray);
                    //find max amount of blockhouses
                    int amount = countBlockHouses(mapArray);
                    System.out.println(amount);
                    //print the result map for checking
                    /*for(int i=0; i<mapArray.length; i++) {
                        for(int j=0; j<mapArray[i].length; j++) {
                            System.out.print(mapArray[i][j]);
                        }
                        System.out.println();
                    }*/
                }
            }
        }

        sc.close();
    }

    /**
     * check if it is number.
     *
     * @param obj
     * @return
     */
    private static boolean isObjNum(String obj) {
        char[] charArgs = obj.toCharArray();
        for(int i=0; i<charArgs.length; i++) {
            if(!(charArgs[i] >= '0' && charArgs[i] <='9')) {
                return false;
            }
        }
        return true;
    }

    /**
     * To build the n*n map by given value.
     *
     * @param lineValue
     * @param mapArray
     * @param i
     */
    private static void fillValueIntoMapArray(String lineValue, String[][] mapArray, int i) {
        char[] charArray = lineValue.toCharArray();

        for(int j=0; j<charArray.length; j++) {
            String value = String.valueOf(charArray[j]);

            if(value.equals(".")) {
                mapArray[i][j] = value;
            }
            if(value.equals("X")) {
                mapArray[i][j] = value;
            }
        }
    }

    /**
     * find the most suited place to place the blockhouse.
     *
     * @param mapArray
     */
    private static void placeBlockHouses(String[][]mapArray) {
        Map<String,Integer> canGoThroughDirectionsMap = new HashMap<>();

        for(int k=0; k<mapArray.length*mapArray.length; k++) {
            for(int i=0; i<mapArray.length; i++) {
                for(int j=0; j<mapArray[i].length; j++) {
                    /**
                     * [0] record how many step can it walk -- the min amount step from witch walk to four direction,that's the place should be placed blockhouse first
                     */
                    int[] canWalkStepNum = new int[1];
                    /**
                     * [0] leftLineCanGoThrough
                     * [1] rightLineCanGoThrough
                     * [2] upLineCanGoThrough
                     * [3] downLineCanGoThrough
                     */
                    boolean[] canGoThrough = new boolean[4];
                    /**
                     * [0] leftLineHaveBlockHouse
                     * [1] rightLineHaveBlockHouse
                     * [2] upLineHaveBlockHouse
                     * [3] downLineHaveBlockHouse
                     */
                    boolean[] haveBlockHouse = new boolean[4];
                    int canGoThroughDirections = 0;

                    if(mapArray[i][j].equals(".")) {
                        //left direction
                        int x=i,y=j;
                        while(--y>=0) {
                            if(isCallBreak(canWalkStepNum,canGoThrough,haveBlockHouse,0,mapArray,x,y)) {
                                break;
                            }
                        }
                        //right direction
                        x=i;y=j;
                        while(++y<mapArray[i].length) {
                            if(isCallBreak(canWalkStepNum,canGoThrough,haveBlockHouse,1,mapArray,x,y)) {
                                break;
                            }
                        }
                        //up direction
                        x=i;y=j;
                        while (--x>=0) {
                            if(isCallBreak(canWalkStepNum,canGoThrough,haveBlockHouse,2,mapArray,x,y)) {
                                break;
                            }
                        }
                        //down direction
                        x=i;y=j;
                        while (++x<mapArray.length) {
                            if(isCallBreak(canWalkStepNum,canGoThrough,haveBlockHouse,3,mapArray,x,y)) {
                                break;
                            }
                        }

                        //if there is a blockhouse on one of its four direction(can shoot it), then it needn't be put in canGoThroughDirectionsMap for calculation.
                        if(!(haveBlockHouse[0]||haveBlockHouse[1]||haveBlockHouse[2]||haveBlockHouse[3])) {
                            canGoThroughDirectionsMap.put(i+""+j,canWalkStepNum[0]);
                        }
                    }
                }
            }
            //place a blockhouse on that coordination
            String coordination = findCoordinationToPlaceBlockhouse(canGoThroughDirectionsMap);
            int[] coordinationArray = new int[2];
            if(coordination != null) {
                convertCoordinationFromStringToInteger(coordination,coordinationArray);
                mapArray[coordinationArray[0]][coordinationArray[1]] = "H";
                canGoThroughDirectionsMap.clear();
            }
        }
    }

    /**
     * check if it's time to stop searching one of four directions for the map has a limited border reason.
     * And set the amount step of it can walk from its ordinary spot to all four directions into canWalkStepNum array.
     * And set true or false witch depend on whether there's already a blockhouse on one line of its four directions(a blockhouse separated by wall excluded) into haveBlockHouse array.
     *
     * @param canWalkStepNum
     * @param canGoThrough
     * @param haveBlockHouse
     * @param n
     * @param mapArray
     * @param x
     * @param y
     * @return
     */
    private static boolean isCallBreak(int[] canWalkStepNum,boolean[] canGoThrough, boolean[] haveBlockHouse, int n, String[][] mapArray, int x, int y) {
        if(mapArray[x][y].equals(".")) {
            canGoThrough[n] = true;
            canWalkStepNum[0]++;
        }
        if(mapArray[x][y].equals("X")) {
            canGoThrough[n] = false;
        }
        if(mapArray[x][y].equals("H")) {
            haveBlockHouse[n] = true;
        }
        if(!canGoThrough[n] || haveBlockHouse[n]) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * find the coordination to place the blockhouse by calculating the input map.
     * return the coordination of a spot has the min amount walk step.
     *
     * @param canGoThroughDirectionsMap
     * @return
     */
    private static String findCoordinationToPlaceBlockhouse(Map<String,Integer> canGoThroughDirectionsMap) {
        String coordination = null;
        int num = Integer.MAX_VALUE;

        //find the coordination of witch has the min direction that can go through
        for(Iterator it=canGoThroughDirectionsMap.keySet().iterator(); it.hasNext();) {
            String tempCoordination = (String)it.next();

            if(canGoThroughDirectionsMap.get(tempCoordination) < num) {
                num = canGoThroughDirectionsMap.get(tempCoordination);
                coordination = tempCoordination;
            }
        }

        return coordination;
    }

    /**
     * turn String type of coordination into Integer type and set them into array.
     *
     * @param coordination
     * @param coordinationArray
     */
    private static void convertCoordinationFromStringToInteger(String coordination,int[] coordinationArray) {
        coordinationArray[0] = Integer.parseInt(String.valueOf(coordination.charAt(0)));
        coordinationArray[1] = Integer.parseInt(String.valueOf(coordination.charAt(1)));
    }

    /**
     * return the amount of placed blockhouses.
     *
     * @param mapArray
     * @return
     */
    private static int countBlockHouses(String[][] mapArray) {
        int count = 0;

        for(int i=0; i<mapArray.length; i++) {
            for(int j=0; j<mapArray[i].length; j++) {
                if(mapArray[i][j].equals("H")) {
                    count++;
                }
            }
        }

        return count;
    }
}
