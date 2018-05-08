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

public class f1 {

    static long[] item = new long[13668105];
    static long[] user = new long[13668105];
    static int[] rating = new int[13668105];

    static Map<Long, Integer> index = new <Long, Integer> HashMap();

    static long[] user_u = new long[841372];
    static long[] user_v = new long[841372];
    static int[] trust = new int[841372];
    //list.txt

    static long[] users = new long[131828];
    static Map<Long, Double> index4 = new <Long, Double> HashMap();

    public static Set<Long> Iu(long u) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index.get(u);

        while (u == user[i]) {

            set1.add(item[i]);

            i++;

        }
        return set1;

    }

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

    public static int rui(long u, long i) {

        int k = index.get(u);

        while (u == user[k]) {

            if (item[k] == i) {

                return rating[k];
            }
            k++;
        }

        return 0;
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

        r_avg = (double) r / (double) count;

        return r_avg;

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

    public static double f1a(long u, long v) {
        Set<Long> u_set = new HashSet<Long>();
        Set<Long> v_set = new HashSet<Long>();

        u_set = Iu(u);
        v_set = Iu(v);

        Set<Long> intersect = new HashSet<Long>(u_set);
        intersect.retainAll(v_set);

        if (intersect.isEmpty()) {

            return -1;

        }

        double s1 = 0.0;
        double s2 = 0.0;
        double s3 = 0.0;
        double s = 0.0;

        double a = 0.0;
        double b = 0.0;

        if (index4.get(u) == null) {

            return -1;

        }
        double u_avg = index4.get(u);

        if (index4.get(v) == null) {

            return -1;

        }
        double v_avg = index4.get(v);

        for (Long i : intersect) {
            a = rui(u, i) - u_avg;
            b = rui(v, i) - v_avg;

            s1 = s1 + a * b;
            s2 = s2 + a * a;
            s3 = s3 + b * b;

        }

        s = s1 / (Math.sqrt(s2) * Math.sqrt(s3));

        return s;

    }

    public static double f1b(long u, long v) {
        double s = 0.0;
        Set<Long> upu_set = new HashSet<Long>();
        Set<Long> upv_set = new HashSet<Long>();
        upu_set = Iu_up(u);
        upv_set = Iu_up(v);
        Set<Long> intersect = new HashSet<Long>(upu_set);
        Set<Long> union = new HashSet<Long>(upu_set);
        intersect.retainAll(upv_set);
        union.addAll(upv_set);

        if (intersect.isEmpty() || union.isEmpty()) {
            return -1;
        }
        s = (double) intersect.size() /  union.size();

        return s;
    }

    public static double f1c(long u, long v) {
        double s = 0;

        Set<Long> downu_set = new HashSet<Long>();
        Set<Long> downv_set = new HashSet<Long>();
        downu_set = Iu_down(u);
        downv_set = Iu_down(v);
        Set<Long> intersect = new HashSet<Long>(downu_set);
        Set<Long> union = new HashSet<Long>(downu_set);

        intersect.retainAll(downv_set);
        union.addAll(downv_set);

        if (intersect.isEmpty() || union.isEmpty()) {
            return -1;
        }

        s = (double) intersect.size() / union.size();

        return s;
    }

    public static double f1d(long u, long v) {
        double s = 0.0;

        Set<Long> upu_set = new HashSet<Long>();
        Set<Long> downv_set = new HashSet<Long>();
        upu_set = Iu_up(u);
        downv_set = Iu_down(v);

        Set<Long> intersect = new HashSet<Long>(upu_set);
        Set<Long> union = new HashSet<Long>(upu_set);

        intersect.retainAll(downv_set);
        union.addAll(downv_set);

        if (intersect.isEmpty() || union.isEmpty()) {
            return -1;
        }
        s = (double) intersect.size() / union.size();
        return s;
    }

    public static double f1e(long u, long v) {
        double s = 0.0;

        Set<Long> downu_set = new HashSet<Long>();
        Set<Long> upv_set = new HashSet<Long>();
        downu_set = Iu_down(u);
        upv_set = Iu_up(v);

        Set<Long> intersect = new HashSet<Long>(downu_set);
        Set<Long> union = new HashSet<Long>(downu_set);

        intersect.retainAll(upv_set);
        union.addAll(upv_set);

        intersect.retainAll(upv_set);
        union.addAll(upv_set);

        if (intersect.isEmpty() || union.isEmpty()) {
            return -1;
        }

        s = (double) intersect.size() / union.size();
        return s;
    }

    public static void output() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f1e1.txt"));
            PrintStream out1 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f1e2.txt"));
            PrintStream out2 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f1e3.txt"));
            PrintStream out3 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f1e4.txt"));

            double r = 0.0;

            for (int i = 0; i < 841372; i++) {

                if (index.get(user_u[i]) != null && index.get(user_v[i]) != null) {

                    r = f1e(user_u[i], user_v[i]);

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
        int[] count = new int[3];

        int i = 0;

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

                    count[2]++;

                } else if (i == 3) {

                } else if (i == 4) {

                } else if (i == 5) {

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

                v = ru_avg(users[y]);

                index4.put(users[y], v);

            }

        }

        output();
     /*
       System.out.println(users[0]);
      System.out.println(users[131827]);
       System.out.println(user_u[0]);
        System.out.println(user_v[0]);
         System.out.println(trust[0]);
         
         System.out.println(user_u[841371]);
        System.out.println(user_v[841371]);
         System.out.println(trust[841371]);
       */
    }

}
