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

public class f5 {

    static long[] item = new long[13668105];
    static long[] user = new long[13668105];
    static int[] rating = new int[13668105];
    static long[] type = new long[13668105];
    static long[] reviewer = new long[13668105];
    static Map<Long, Integer> index = new <Long, Integer> HashMap();

    //for category.txt/////
    static long[] item1 = new long[13668105];
    static long[] user1 = new long[13668105];
    static long[] type1 = new long[13668105];
    static long[] reviewer1 = new long[13668105];
    static int[] rating1 = new int[13668105];
    static Map<Long, Integer> index1 = new <Long, Integer> HashMap();
    // user_rating.txt
    static long[] user_u = new long[841372];
    static long[] user_v = new long[841372];
    static int[] trust = new int[841372];
    //help parameter
    static double all_avg;

    public static Set<Long> Iur(long u, int r) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index.get(u);

        while (u == user[i]) {

            if (rating[i] == r) {
                set1.add(item[i]);

            }
            i++;

        }

        return set1;

    }

    public static Set<Long> Iu_up(long u) {
        Set<Long> four_set = new HashSet<Long>();
        Set<Long> five_set = new HashSet<Long>();
        four_set = Iur(u, 4);
        five_set = Iur(u, 5);
        Set<Long> union = new HashSet<Long>(four_set);

        union.addAll(five_set);

        return union;

    }

    public static Set<Long> Iu_down(long u) {
        Set<Long> one_set = new HashSet<Long>();
        Set<Long> two_set = new HashSet<Long>();
        one_set = Iur(u, 1);
        two_set = Iur(u, 2);

        Set<Long> union = new HashSet<Long>(one_set);
        union.addAll(two_set);

        return union;

    }

    public static Set<Long> Jv(long u) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index1.get(u);

        while (u == reviewer1[i]) {

            set1.add(item1[i]);

            i++;

        }

        return set1;

    }

    public static double ru_avg(long u) {
        int r = 0;
        int count = 0;
        double r_avg = 0.0;

        int i = index.get(u);

        while (u == user[i]) {

            r = r + rating[i];
            count++;

            i++;
        }

        r_avg = (double) r / count;

        return r_avg;

    }

    public static double rv_avg(long v) {

        int r = 0;
        int count = 0;
        double r_avg = 0.0;

        int i = index1.get(v);

        while (v == reviewer1[i]) {

            r = r + rating1[i];
            count++;

            i++;
        }

        r_avg = (double) r / count;

        return r_avg;

    }

    public static double ruv(long u, long v) {
        double s;
        int r = 0;
        int cnt = 0;
        int i = index.get(u);

        while (u == user[i]) {

            if (v == reviewer[i]) {

                r = r + rating[i];
                cnt++;
            }

            i++;

        }

        if (cnt == 0) {

            return 0;

        }

        s = (double) r / cnt;

        return s;
    }

    public static double f5a(long u, long v) {
        double s = 0.0;
        double r = ruv(u, v);

        s = all_avg + (rv_avg(v) - all_avg) + (ru_avg(u) - all_avg) + (r - all_avg);

        return s;

    }

    public static double f5b(long u, long v) {
        double s = 0.0;
        Set<Long> upu_set = new HashSet<Long>();
        Set<Long> v_set = new HashSet<Long>();
        upu_set = Iu_up(u);
        if (upu_set.isEmpty()) {

            return -1;
        }

        v_set = Jv(v);
        if (v_set.isEmpty()) {

            return -1;

        }

        Set<Long> intersect = new HashSet<Long>(upu_set);
        intersect.retainAll(v_set);

        if (intersect.isEmpty()) {

            return -1;
        }

        s = (double) intersect.size() / upu_set.size();

        return s;
    }

    public static double f5c(long u, long v) {
        double s = 0.0;
        Set<Long> downu_set = new HashSet<Long>();
        Set<Long> v_set = new HashSet<Long>();
        downu_set = Iu_down(u);
        if (downu_set.isEmpty()) {

            return -1;
        }

        v_set = Jv(v);
        if (v_set.isEmpty()) {

            return -1;

        }

        Set<Long> intersect = new HashSet<Long>(downu_set);
        intersect.retainAll(v_set);

        if (intersect.isEmpty()) {

            return -1;
        }

        s = (double) intersect.size() / downu_set.size();

        return s;
    }

    public static void output() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f5c1.txt"));
            PrintStream out1 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f5c2.txt"));
            PrintStream out2 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f5c3.txt"));
            PrintStream out3 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f5c4.txt"));

            double r = 0.0;

            for (int i = 0; i < 841372; i++) {

                if (index.get(user_u[i]) != null && index1.get(user_v[i]) != null) {

                    r = f5c(user_u[i], user_v[i]);
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

        String filename = "C:\\Users\\s6770\\Desktop\\project1\\sources\\rating.txt";
        File file = new File(filename);
        int[] count = new int[6];

        int i = 0;
        int r = 0;
        long c_id = 199775;
        index.put(c_id, 0);

        try {
            Scanner inputstream = new Scanner(file);
            while (inputstream.hasNext()) {
                long data = Long.parseLong(inputstream.next());

                if (i == 0) {

                    item[count[0]] = data;
                    count[0]++;

                } else if (i == 1) {
                    if (c_id != data) {
                        c_id = data;
                        index.put(data, count[1]);

                    }

                    user[count[1]] = data;
                    count[1]++;

                } else if (i == 2) {

                    rating[count[2]] = (int) data;
                    r = r + (int) data;
                    count[2]++;

                } else if (i == 3) {

                } else if (i == 4) {

                    type[count[4]] = data;
                    count[4]++;

                } else if (i == 5) {

                    reviewer[count[5]] = data;
                    count[5]++;

                }

                i++;

                if (i > 5) {
                    i = 0;

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        all_avg = (double) r / 13668104;

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

                    item1[count1[0]] = data;
                    count1[0]++;

                } else if (j == 1) {

                    user1[count1[1]] = data;
                    count1[1]++;

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
                    rating1[count1[4]] = (int) data;

                    count1[4]++;
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
