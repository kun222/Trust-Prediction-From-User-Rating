/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s6770
 */
import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;

public class f8 {

    // user_rating.txt
    static long[] user_u = new long[841372];
    static long[] user_v = new long[841372];
    static int[] trust = new int[841372];

    static Map<Long, Integer> index2 = new <Long, Integer> HashMap();

    public static Set<Long> Tu(long u) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index2.get(u);

        while (u == user_u[i]) {

            if (trust[i] == 1) {

                set1.add(user_v[i]);

            }

            i++;
        }

        return set1;

    }

    public static double f8(long u, long v) {
        double s;
        Set<Long> tu_set = new HashSet<Long>();
        Set<Long> tv_set = new HashSet<Long>();
        tu_set = Tu(u);
        tv_set = Tu(v);

        Set<Long> intersect = new HashSet<Long>(tu_set);
        Set<Long> union = new HashSet<Long>(tu_set);

        intersect.retainAll(tv_set);
        union.addAll(tv_set);

        if (intersect.isEmpty() || union.isEmpty()) {
            return -1;
        }

        s = (double) intersect.size() / union.size();

        return s;

    }

    public static void output() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f81.txt"));
            PrintStream out1 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f82.txt"));
            PrintStream out2 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f83.txt"));
            PrintStream out3 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f84.txt"));

            double r = 0.0;

            for (int i = 0; i < 841372; i++) {

                if (index2.get(user_u[i]) != null && index2.get(user_v[i]) != null) {

                    r = f8(user_u[i], user_v[i]);
                    if (r > 0.0) {
                        out.println(user_u[i]);
                        out1.println(user_v[i]);
                        out2.println(String.format("%.9f", r));
                        out3.println(trust[i]);
                    }

                }
                System.out.print(i + "\n");
            }

            out.close();
            out1.close();
            out2.close();
            out3.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        // start reading user_rating.txt
        String filename2 = "C:\\Users\\s6770\\Desktop\\project1\\sources\\user_rating.txt";
        File file2 = new File(filename2);
        int k = 0;
        int[] count2 = new int[3];
        long c_id = 199781;
        index2.put(c_id, 0);

        try {
            Scanner inputstream2 = new Scanner(file2);
            while (inputstream2.hasNext()) {
                long data = Long.parseLong(inputstream2.next());

                if (k == 0) {

                    if (c_id != data) {
                        c_id = data;
                        index2.put(data, count2[0]);

                    }

                    user_u[count2[0]] = data;
                    count2[0]++;

                } else if (k == 1) {

                    user_v[count2[1]] = data;
                    count2[1]++;

                } else if (k == 2) {

                    trust[count2[2]] = (int) data;
                    count2[2]++;

                }

                k++;

                if (k > 2) {

                    k = 0;

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        output();

    }

}
