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

public class f4 {

    static long[] user = new long[13668105];
    static int[] rating = new int[13668105];
    static long[] reviewer = new long[13668105];
    static Map<Long, Integer> index = new <Long, Integer> HashMap();

    // user_rating.txt
    static long[] user_u = new long[841372];
    static long[] user_v = new long[841372];
    static int[] trust = new int[841372];
    // list.txt
    static long[] users = new long[131828];
    static Map<Long, Double> index4 = new <Long, Double> HashMap();

    public static Set<Long> Yu(long u) {
        Set<Long> set1 = new HashSet<Long>();

        int i = index.get(u);

        while (u == user[i]) {

            set1.add(reviewer[i]);

            i++;
        }

        return set1;

    }

    public static double ruy(long u, long y) {
        double s;
        int r = 0;
        int cnt = 0;
        int i = index.get(u);

        while (u == user[i]) {

            if (y == reviewer[i]) {

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

    public static double ru_downhat(long u) {
        Set<Long> y_set = new HashSet<Long>();

        y_set = Yu(u);
        double s1 = 0.0;
        double s = 0.0;

        if (y_set.isEmpty()) {

            return 0;
        }

        for (Long y : y_set) {
            s1 = s1 + ruy(u, y);

        }
        s = s1 / y_set.size();

        return s;
    }

    public static double f4(long u, long v) {
        Set<Long> u_set = new HashSet<Long>();
        Set<Long> v_set = new HashSet<Long>();
        u_set = Yu(u);
        v_set = Yu(v);
        Set<Long> intersect = new HashSet<Long>(u_set);
        intersect.retainAll(v_set);

        if (intersect.isEmpty()) {

            return -1;

        }
        double s1 = 0.0;
        double s2 = 0.0;
        double s3 = 0.0;
        double s = 0.0;
        double a;
        double b;

        if (index4.get(u) == null) {

            return -1;

        }

        double ru_downhat = index4.get(u);

        if (index4.get(v) == null) {

            return -1;

        }
        double rv_downhat = index4.get(v);

        for (Long y : intersect) {

            a = ruy(u, y) - ru_downhat;
            b = ruy(v, y) - rv_downhat;

            s1 = s1 + (a * b);
            s2 = s2 + (a * a);
            s3 = s3 + (b * b);

        }

        s = s1 / (Math.sqrt(s2) * Math.sqrt(s3));

        return s;

    }

    public static void output() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f41.txt"));
            PrintStream out1 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f42.txt"));
            PrintStream out2 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f43.txt"));
            PrintStream out3 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f44.txt"));

            double r = 0.0;

            for (int i = 0; i < 841372; i++) {

                if (index.get(user_u[i]) != null && index.get(user_v[i]) != null) {

                    r = f4(user_u[i], user_v[i]);

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

                    //item[count[0]] = data;
                    //count[0]++;
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
        // list.txt
        int j = 0;
        String filename3 = "C:\\Users\\s6770\\Desktop\\project1\\sources\\list.txt";
        File file3 = new File(filename3);
        try {
            Scanner inputstream3 = new Scanner(file3);
            while (inputstream3.hasNext()) {
                long data = Long.parseLong(inputstream3.next());

                users[j] = data;

                j++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        double v;

        for (int y = 0; y < 131828; y++) {

            if (index.get(users[y]) != null) {

                v = ru_downhat(users[y]);

                index4.put(users[y], v);

            }

        }
        output();

    }

}
