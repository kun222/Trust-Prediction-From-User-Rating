/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kun Ye
 */
import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;

public class project {

    static long[] item = new long[13668105];
    static long[] user = new long[13668105];
    static int[] rating = new int[13668105];
    static int[] status = new int[13668105];
    static long[] type = new long[13668105];
    static long[] reviewer = new long[13668105];
    static Map<Long, Integer> index = new <Long, Integer> HashMap();

    //for category2.txt/////
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

//***************Features**************//
//Iu the set of items rated by u
    public static Set<Long> Iu(long u) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index.get(u);

        while (u == user[i]) {

            set1.add(item[i]);

            i++;

        }
        return set1;

    }

// Iu,r the set of items rated r (where r 2 f1; 2; 3; 4; 5g) by u def Iur(user,r):
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

    public static Set<Long> Cu(long u) {
        Set<Long> set1 = new HashSet<Long>();

        int i = index.get(u);

        while (u == user[i]) {

            set1.add(type[i]);

            i++;
        }

        return set1;

    }

    public static Set<Long> Iuc(long u, long c) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index.get(u);

        while (u == user[i]) {

            if (type[i] == c) {

                set1.add(item[i]);

            }

            i++;

        }

        return set1;

    }

    public static Set<Long> Du(long u) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index1.get(u);

        while (u == reviewer1[i]) {

            set1.add(type1[i]);

            i++;

        }

        return set1;

    }

    public static Set<Long> Yu(long u) {
        Set<Long> set1 = new HashSet<Long>();

        int i = index.get(u);

        while (u == user[i]) {

            set1.add(reviewer[i]);

            i++;
        }

        return set1;

    }

    public static Set<Long> Iuy(long u, long y) {

        Set<Long> set1 = new HashSet<Long>();

        int i = index1.get(y);

        while (y == reviewer1[i]) {

            if (user1[i] == u) {

                set1.add(item1[i]);

            }

            i++;

        }

        return set1;

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
//Cu the set or multiset (depending on the feature) of categories of the items in Iu

    /**
     * ********************************************************************
     * ____________________________Help functions____________________________
     * ********************************************************************
     * @param a
     */
    public static int rui(long u, long i) {

        int k = index.get(u);

        while (u == user[k]) {

            if (item[k] == i) {

                return rating[k];
            }
            k++;
        }

        return -1;
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

    public static double ruc(long u, long c) {

        double s1 = 0.0;
        double s = 0.0;

        Set<Long> i_set = new HashSet<Long>();
        i_set = Iuc(u, c);

        if (i_set.isEmpty()) {

            return 0;
        }

        for (Long i : i_set) {

            s1 = s1 + rui(u, i);

        }
        s = s1 / (double) i_set.size();

        return s;
    }

    public static double ru_hat(long u) {
        Set<Long> c_set = new HashSet<Long>();
        c_set = Cu(u);
        double s1 = 0.0;
        double s = 0.0;

        if (c_set.isEmpty()) {

            return 0;
        }

        for (Long c : c_set) {
            s1 = s1 + ruc(u, c);

        }
        s = s1 / (double) c_set.size();

        return s;
    }

    public static double ruy(long u, long y) {

        double s1 = 0.0;
        double s = 0.0;

        Set<Long> i_set = new HashSet<Long>();
        i_set = Iuy(u, y);

        if (i_set.isEmpty()) {

            return 0;
        }

        for (Long i : i_set) {

            s1 = s1 + rui(u, i);

        }
        s = s1 / (double) i_set.size();

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
        s = s1 / (double) y_set.size();

        return s;
    }

    /**
     * ********************************************************************
     **********************************************************************
     ************************Parameter Calculation*************************
     * *********************************************************************
     * ********************************************************************
     */
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

        for (Long i : intersect) {

            s1 = s1 + (rui(u, i) - ru_avg(u)) * (rui(v, i) - ru_avg(v));
            s2 = s2 + Math.pow((rui(u, i) - ru_avg(u)), 2);
            s3 = s3 + Math.pow((rui(v, i) - ru_avg(v)), 2);

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
        /*
        System.out.println(upu_set.size() + "\n");
        System.out.println(upv_set.size() + "\n");
        System.out.println(intersect.size() + "\n");
        System.out.println(union.size() + "\n");
         */
        if (union.isEmpty()) {
            return -1;
        }
        s = (double) intersect.size() / (double) union.size();
        //System.out.println(s+ "\n");
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

        if (union.isEmpty()) {
            return -1;
        }

        s = (double) intersect.size() / (double) union.size();

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

        if (union.isEmpty()) {
            return -1;
        }
        s = (double) intersect.size() / (double) union.size();
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

        if (union.isEmpty()) {
            return -1;
        }

        s = (double) intersect.size() / (double) union.size();
        return s;
    }

    public static double f2a(long u, long v) {
        double s = 0.0;
        Set<Long> u_set = new HashSet<Long>();
        Set<Long> v_set = new HashSet<Long>();

        u_set = Cu(u);
        v_set = Cu(v);

        Set<Long> intersect = new HashSet<Long>(u_set);
        Set<Long> union = new HashSet<Long>(u_set);

        intersect.retainAll(v_set);
        union.addAll(v_set);

        if (union.isEmpty()) {
            return -1;
        }

        s = (double) intersect.size() / (double) union.size();
        return s;

    }

    public static double f2b(long u, long v) {

        Set<Long> u_set = new HashSet<Long>();
        Set<Long> v_set = new HashSet<Long>();
        u_set = Cu(u);
        v_set = Cu(v);
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

        for (Long c : intersect) {
            a = ((double) Iuc(u, c).size() - ((double) Iu(u).size() / (double) Cu(u).size()));
            b = ((double) Iuc(v, c).size() - ((double) Iu(v).size() / (double) Cu(v).size()));

            s1 = s1 + a * b;
            s2 = s2 + (a * a);
            s3 = s3 + (b * b);

        }

        s = s1 / (Math.sqrt(s2) * Math.sqrt(s3));

        return s;

    }

    public static double f2c(long u, long v) {
        Set<Long> u_set = new HashSet<Long>();
        Set<Long> v_set = new HashSet<Long>();
        u_set = Cu(u);
        v_set = Cu(v);

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

        for (Long c : intersect) {

            a = (ruc(u, c) - ru_hat(u));
            b = (ruc(v, c) - ru_hat(v));

            s1 = s1 + (a * b);
            s2 = s2 + (a * a);
            s3 = s3 + (b * b);

        }

        s = s1 / (Math.sqrt(s2) * Math.sqrt(s3));

        return s;

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

        if (union.isEmpty()) {
            return -1;
        }

        s = (double) intersect.size() / (double) union.size();

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

        for (Long y : intersect) {

            a = (ruy(u, y) - ru_downhat(u));
            b = (ruy(v, y) - ru_downhat(v));

            s1 = s1 + (a * b);
            s2 = s2 + (a * a);
            s3 = s3 + (b * b);

        }

        s = s1 / (Math.sqrt(s2) * Math.sqrt(s3));

        return s;

    }

    public static double f5a(long u, long v) {
        double s = 0.0;
        double r = ruy(u, v);

        if (r == 0) {

            return -1;
        }
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

        s = (double) intersect.size() / (double) upu_set.size();

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

        s = (double) intersect.size() / (double) downu_set.size();

        return s;
    }
    
    
    
    
    /*
    public static long search(long i) {
        
        for (int k = 0; k < 1559803; k++) {
            if (i == item1[k]) {
                
                return user1[k];
                
            }
            
        }
        
        return -1;
        
    }
    
    public static void output() {
        
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\p.txt"));
            
            long output = 0;
            
            for (int i = 0; i < 13668320; i++) {
                output = search(item[i]);
                out.println(output);
            }
            
            out.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
     */
    public static void output1() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f2b1.txt"));
            PrintStream out1 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f2b2.txt"));
            PrintStream out2 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f2b3.txt"));
            PrintStream out3 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f2b4.txt"));

            double r = 0.0;

            for (int i = 0; i < 841372; i++) {

                if (index.get(user_u[i]) != null && index.get(user_v[i]) != null) {

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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void output2() {
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void output3() {
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        String filename = "C:\\Users\\s6770\\Desktop\\project1\\sources\\rating4.txt";
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

                    status[count[3]] = (int) data;
                    count[3]++;
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

        ///////////////start reading category3.txt///////////////
        String filename1 = "C:\\Users\\s6770\\Desktop\\project1\\sources\\category3.txt";
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
                }

                j++;

                if (j > 4) {
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

        long id1 = 199781;
        long id2 = 236296;
        long ite = 7575168;
        /*
        System.out.println(item1[13668103]);
        System.out.println(user1[13668103]);
        System.out.println(type1[13668103]);
        System.out.println(reviewer1[13668103]);
         System.out.println(index1.get(id1));
            System.out.println(rating1[13668103]);
         */

        output3();
        //System.out.println(f5a(id1,id2));

    }

}
