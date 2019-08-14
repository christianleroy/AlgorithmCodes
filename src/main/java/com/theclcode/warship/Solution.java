package com.theclcode.warship;

import java.util.Scanner;

public class Solution {
    /**************** START OF USER SOLUTION ****************/

    private final static int MISS 		= 0;
    private final static int CARRIER 	= 1;
    private final static int BATTLESHIP = 2;
    private final static int CRUISER 	= 3;
    private final static int SUBMARINE 	= 4;
    private final static int DESTROYER 	= 5;
    private static int limitCopy=0;

    public static void init(int limit) {

    }

    public static void play() {
        int shipsHitCount=0;
        limitCopy = limit;

        for(int i=0; i<board.length && limitCopy>0 && shipsHitCount<5; i++){
            int j = i==0 ? 0 : i%2;
            for(j=j; j<board[i].length && limitCopy>0 && shipsHitCount<5; j+=2){
                int result = fire(i,j);
                limitCopy--;
                if(result>0){
                    int remaining=getShipSize(result)-1;
                    move(i,j, remaining);
                }

            }
        }
    }

    public static int getShipSize(int shipId){
        switch(shipId){
            case 1:
                return 5;
            case 2:
                return 4;
            case 3:
                return 3;
            case 4:
                return 3;
            case 5:
                return 2;
            default:
                return 0;
        }
    }

    public static void canFire(int result, int remaining, int latitude, int longitude){
        
    }

    public static void move(int latitude, int longitude, int remaining){
            int result=0;
            if(canMoveNorth(latitude) && limitCopy>0){
                int lat=latitude-1;
                result=fire(lat, longitude);
                limitCopy--;
                while(result>0 && remaining>0){
                    remaining--;
                    if(remaining>0 && canMoveNorth(lat)){
                        result = fire(--lat, longitude);
                        limitCopy--;
                    }
                }
            }
            if(canMoveEast(longitude) && limitCopy>0){
                int lon = longitude+1;
                result=fire(latitude, lon);
                limitCopy--;
                while(result>0 && remaining>0){
                    remaining--;
                    if(remaining>0 && canMoveEast(lon)){
                        result = fire(latitude, ++lon);
                        limitCopy--;
                    }
                }
            }
            if(canMoveSouth(latitude) && limitCopy>0){
                int lat=latitude+1;
                result=fire(lat, longitude);
                limitCopy--;
                while(result>0 && remaining>0){
                    remaining--;
                    if(remaining>0 && canMoveSouth(lat)){
                        result = fire(++lat, longitude);
                        limitCopy--;
                    }
                }
            }
            if(canMoveWest(longitude) && limitCopy>0){
                int lon = longitude-1;
                result=fire(latitude, lon);
                limitCopy--;
                while(result>0 && remaining>0){
                    remaining--;
                    if(remaining>0 && canMoveWest(lon)){
                        result = fire(latitude, --lon);
                        limitCopy--;
                    }
                }
            }
    }

    public static void printMap(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean canMoveNorth(int latitude){
        return latitude>0;
    }

    public static boolean canMoveSouth(int latitude){
        return latitude<9;
    }

    public static boolean canMoveWest(int longitude){
        return longitude>0;
    }

    public static boolean canMoveEast(int longitude){
        return longitude<9;
    }

    /***************** END OF USER SOLUTION *****************/


    /*********** START OF USER CALLABLE FUNCTIONS ***********/

    //You may call this function but don't edit the code
    public static int fire(int r, int c)
    {
        if (r < 0 || r > 9 || c < 0 || c > 9 || callcount >= MAX_CALLCOUNT)
        {
            callcount = MAX_CALLCOUNT;
            return 0;
        }

        ++callcount;

        int ret = board[r][c];

        if (board[r][c] > 0){ --hit;
            printMap();}
        board[r][c] = 0;

        return ret;
    }

    /************ END OF USER CALLABLE FUNCTIONS ************/


    public final static int board[][] = new int[10][10];

    private static int hit;
    private final static int len[] = { 5, 4, 3, 3, 2 };

    private final static int MAX_CALLCOUNT = 10000;

    private static int callcount;
    private static int limit;

    private static int seed;

    private static int pseudo_rand()
    {
        seed = seed * 214013 + 2531011;
        return (seed >> 16) & 0x7fff;
    }

    private static boolean check(int r, int c, int d, int l)
    {
        if (d == 1)
        {
            for (int m = 0; m < l; ++m)
                if (board[r][c + m] > 0)
                    return false;
            return true;
        }
        else
        {
            for (int m = 0; m < l; ++m)
                if (board[r + m][c] > 0)
                    return false;
            return true;
        }
    }

    private static void doarrangement()
    {
        for (int r = 0; r < 10; ++r)
            for (int c = 0; c < 10; ++c)
                board[r][c] = 0;

        for (int k = 0; k < 5; ++k)
        {
            while(true)
            {
                int r, c, d;

                d = pseudo_rand() % 2;
                if (d == 1)
                {
                    r = pseudo_rand() % 10;
                    c = pseudo_rand() % (10 - len[k] + 1);
                    if (check(r, c, d, len[k]))
                    {
                        for (int l = 0; l < len[k]; ++l) {
                            board[r][c + l] = k + 1;
                        }
                        break;
                    }
                }
                else
                {
                    r = pseudo_rand() % (10 - len[k] + 1);
                    c = pseudo_rand() % 10;
                    if (check(r, c, d, len[k]))
                    {
                        for (int l = 0; l < len[k]; ++l) {
                            board[r + l][c] = k + 1;
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        int TC, totalscore, totalcallcount;

        Scanner sc = new Scanner(System.in);
        TC = sc.nextInt();

        totalscore = totalcallcount = 0;
        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = 100, callcount4tc = 0;

            seed = sc.nextInt();
            limit = sc.nextInt();

            init(limit);

            for (int game = 0; game < 1; ++game)
            {
                doarrangement();
                printMap();
                hit = 0;
                for (int k = 0; k < 5; ++k)
                    hit += len[k];

                callcount = 0;
                play();

                if (hit != 0)
                    callcount = MAX_CALLCOUNT;

                if (callcount > limit)
                    score = 0;

                callcount4tc += callcount;
                printMap();
            }

            System.out.println("#" + testcase + " " + score);

            totalscore += score;
            totalcallcount += callcount4tc;
        }

        System.out.println("total score = " + totalscore / TC);
        sc.close();
    }

}
