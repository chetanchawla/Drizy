package com.example.divyansh.drizy;

/**
 * Created by HP on 7/10/2017.
 */

public class hmm {
    static double[][] Point = {{0,0},{28.54609,77.18670},{28.54667,77.186918},{28.54549,77.18797},{28.54664,77.18544},{28.54531,77.18621}};

    static int[][] Hotspot = {{2,1},{3,1},{4,1},{5,1}};

//    Point[0][0] = 0;
//    Point[0][1] = 0;
//    Point[1][0] = 28.54609;
//    Point[1][1] = 77.18670;
//    Point[2][0] = 28.54667;
//    Point[2][1] = 77.186918;
//    Point[3][0] = 28.54549;
//    Point[3][1] = 77.18797;
//    Point[4][0] = 28.54664;
//    Point[4][1] = 77.18544;

//    Hotspot[0][0] = 2;
//    Hotspot[0][1] = 1;
//    Hotspot[1][0] = 3;
//    Hotspot[1][1] = 1;
//    Hotspot[2][0] = 4;
//    Hotspot[2][1] = 1;

    static float Thresh = 5;
    static float distance_thresh = 1;

    public static double Road(double x, double y, int i)
    {
        double x1 = Point[Hotspot[i][0]][0];
        double y1 = Point[Hotspot[i][0]][1];
        double x2 = Point[Hotspot[i][1]][0];
        double y2 = Point[Hotspot[i][1]][1];
        x = (x * 100000) % 100000;
        y = (y * 100000) % 100000;
        x1 = (x1 * 100000) % 100000;
        y1 = (y1 * 100000) % 100000;
        x2 = (x2 * 100000) % 100000;
        y2 = (y2 * 100000) % 100000;



        double dist1 = Math.sqrt(((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1)));
        double dist2 = Math.sqrt(((x2 - x)*(x2 - x) + (y2 - y)*(y2 - y)));
        double dist3 = Math.sqrt(((x1 - x)*(x1 - x) + (y1 - y)*(y1 - y)));
        if (dist2 + dist3 - dist1 < Thresh)
            return dist2 + dist3 - dist1;
        return -1;
    }


    public static int[] FindRoad(double lat, double lng, double prevx, double prevy)
    {
        double found = Thresh;
        int road_number = 0;
        int d;

        for (int i = 0; i < Hotspot.length; i++)
        {
            double dist = Road(lat, lng, i);
            if((dist != -1) && (dist < found))
            {
                found = dist;
                road_number = i;
            }
        }
        if (found != Thresh)
        {
            double x1 = Point[1][0];
            double y1 = Point[1][1];
            double x = lat;
            double y = lng;
            x = (x * 100000) % 100000;
            y = (y * 100000) % 100000;
            x1 = (x1 * 100000) % 100000;
            y1 = (y1 * 100000) % 100000;
            prevx = (prevx * 100000) % 100000;
            prevy = (prevy * 100000) % 100000;
            double prevdistance = Math.sqrt(((prevx - x1)*(prevx - x1) + (prevy - y1)*(prevy - y1)));
            double currdistance = Math.sqrt(((x - x1)*(x - x1) + (y - y1)*(y - y1)));
            if (prevdistance - currdistance > distance_thresh)
            {
                d = 1;
            }
            else if(prevdistance - currdistance < (-1 * distance_thresh))
            {
                d = 0;
            }
            else
            {
                d = -1;
            }
            x1 = Point[Hotspot[road_number][0]][0];
            y1 = Point[Hotspot[road_number][0]][1];
            double x2 = Point[Hotspot[road_number][1]][0];
            double y2 = Point[Hotspot[road_number][1]][1];

            int[] returnInsideThing = new int[3];
            returnInsideThing[0] = 0;
            returnInsideThing[1] = road_number;
            returnInsideThing[2] = d;
//            returnInsideThing[3] = x2;
//            returnInsideThing[4] = x1;
//            returnInsideThing[5] = y2;
//            returnInsideThing[6] = y1;
            return returnInsideThing;
        }
        int[] returnThing = new int[3];
        for(int i = 0; i< 3; i++)
        {
            returnThing[i] = -1;
        }
        return returnThing;
    }
    public int checkDistance(double lat,double lng, double prevx, double prevy)
    {
        double x1 = Point[1][0];
        double y1 = Point[1][1];
        double x = lat;
        double y = lng;
        x = (x * 100000) % 100000;
        y = (y * 100000) % 100000;
        x1 = (x1 * 100000) % 100000;
        y1 = (y1 * 100000) % 100000;
        prevx = (prevx * 100000) % 100000;
        prevy = (prevy * 100000) % 100000;
        double prevdistance = Math.sqrt(((prevx - x1)*(prevx - x1) + (prevy - y1)*(prevy - y1)));
        double currdistance = Math.sqrt(((x - x1)*(x - x1) + (y - y1)*(y - y1)));
        if (prevdistance - currdistance > distance_thresh)
        {
            return 1;
        }
        else if(prevdistance - currdistance < (-1 * distance_thresh))
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
