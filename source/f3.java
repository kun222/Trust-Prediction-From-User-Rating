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

public class f3 {
    //for category.txt/////

    static long[] type1 = new long[13668105];
    static long[] reviewer1 = new long[13668105];

    static Map<Long, Integer> index1 = new <Long, Integer> HashMap();
    // user_rating.txt
    static long[] user_u = new long[841372];
    static long[] user_v = new long[841372];
    static int[] trust = new int[841372];

    public static Set<Long> Du(long u) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index1.get(u);

        while (u == reviewer1[i]) {

            set1.add(type1[i]);

            i++;

        }

        return set1;

    }

    public static double f3(long u, long v) {
        double s = 0.0;
        Set<Long> u_set = new HashSet<Long>();
        Set<Long> v_set = new HashSet<Long>();

        u_set = Du(u);
        v_set = Du(v);

        Set<Long> intersect = new HashSet<Long>(u_set);
        Set<Long> union = new HashSet<Long>(u_set);

        intersect.retainAll(v_set);
        union.addAll(v_set);

        if (intersect.isEmpty() || union.isEmpty()) {
            return -1;
        }

        s = (double) intersect.size() / (double) union.size();

        return s;

    }

    public static void output() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f31.txt"));
            PrintStream out1 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f32.txt"));
            PrintStream out2 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f33.txt"));
            PrintStream out3 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f34.txt"));

            double r = 0.0;

            for (int i = 0; i < 841372; i++) {

                if (index1.get(user_u[i]) != null && index1.get(user_v[i]) != null) {

                    r = f3(user_u[i], user_v[i]);

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

        ///////////////start reading category.txt///////////////
        String filename1 = "C:\\Users\\s6770\\Desktop\\project1\\sources\\category.txt";
        File file1 = new File(filename1);
        int[] count1 = new int[5];

        int j = 0;
        long c_id1 = 199775;
        index1.put(c_id1, 0);

        try {
            Scanner inputstream1 = new Scanner(file1);
            while (inputstream1.hasNext()) {
                long data = Long.parseLong(inputstream1.next());

                if (j == 0) {

                } else if (j == 1) {

                } else if (j == 2) {

                    type1[count1[2]] = data;
                    count1[2]++;

                } else if (j == 3) {

                    if (c_id1 != data) {
                        c_id1 = data;
                        index1.put(data, count1[3]);

                    }
                    reviewer1[count1[3]] = data;
                    count1[3]++;

                } else if (j == 4) {

                } else if (j == 5) {

                    continue;

                }

                j++;

                if (j > 5) {
                    j = 0;

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        // start reading user_rating.txt
        String filename2 = "C:\\Users\\s6770\\Desktop\\project1\\sources\\user_rating.txt";
        File file2 = new File(filename2);
        int k = 0;
        int[] count2 = new int[3];
        try {
            Scanner inputstream2 = new Scanner(file2);
            while (inputstream2.hasNext()) {
                long data = Long.parseLong(inputstream2.next());

                if (k == 0) {

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
