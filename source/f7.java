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

public class f7 {

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
    // list.txt
    static long[] users = new long[131828];
    static Map<Long, Double> index4 = new <Long, Double> HashMap();
    static Map<Long, Double> index5 = new <Long, Double> HashMap();

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

    public static double rv_downhat(long u) {
        double s;
        int i = index1.get(u);
        int r = 0;
        int cnt = 0;
        while (u == reviewer1[i]) {

            r = r + rating1[i];
            cnt++;
            i++;

        }
        if (cnt == 0) {

            return 0;

        }

        s = (double) r / cnt;
        return s;
    }

    public static double f7a(long u) {
        double s = 0.0;

        if (index5.get(u) == null) {

            return -1;

        }

        double rv_downhats = index5.get(u);

        s = rv_downhats - all_avg;

        return s;

    }

    public static double f7b(long u) {
        double s = 0.0;

        if (index4.get(u) == null) {

            return -1;

        }

        double ru_downhats = index4.get(u);

        s = ru_downhats - all_avg;

        return s;
    }

    public static double f7c(long u, long v) {
        double s = 0.0;
        if (index5.get(u) == null) {

            return -1;

        }

        double rv_downhats = index5.get(v);

        if (index4.get(u) == null) {

            return -1;

        }

        double ru_downhats = index4.get(u);

        s = rv_downhats - ru_downhats;

        return s;
    }

    public static void output() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f7c1.txt"));
            PrintStream out1 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f7c2.txt"));
            PrintStream out2 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f7c3.txt"));
            PrintStream out3 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f7c4.txt"));

            double r = 0.0;

            for (int i = 0; i < 841372; i++) {

                if (index.get(user_u[i]) != null && index1.get(user_v[i]) != null) {

                    r = f7c(user_u[i], user_v[i]);
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
            out2.close();

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

        // list.txt
        int n = 0;
        String filename3 = "C:\\Users\\s6770\\Desktop\\project1\\sources\\list.txt";
        File file3 = new File(filename3);
        try {
            Scanner inputstream3 = new Scanner(file3);
            while (inputstream3.hasNext()) {
                long data = Long.parseLong(inputstream3.next());

                users[n] = data;

                n++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        double u;

        for (int y = 0; y < 131828; y++) {

            if (index.get(users[y]) != null) {

                u = ru_downhat(users[y]);
                index4.put(users[y], u);

            }

        }

        double v;

        for (int w = 0; w < 131828; w++) {

            if (index1.get(users[w]) != null) {

                v = rv_downhat(users[w]);
                index5.put(users[w], v);

            }

        }

    }

}
