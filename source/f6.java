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

public class f6 {

    static long[] item = new long[13668105];
    static long[] user = new long[13668105];
    static int[] rating = new int[13668105];
    static int[] status = new int[13668105];
    static long[] type = new long[13668105];
    static long[] reviewer = new long[13668105];
    static Map<Long, Integer> index = new <Long, Integer> HashMap();

    //for category.txt/////
    static long[] item1 = new long[13668105];
    static long[] user1 = new long[13668105];
    static long[] type1 = new long[13668105];
    static long[] reviewer1 = new long[13668105];
    static int[] rating1 = new int[13668105];
    static int[] status1 = new int[13668105];
    static Map<Long, Integer> index1 = new <Long, Integer> HashMap();
    // user_rating.txt
    static long[] user_u = new long[841372];
    static long[] user_v = new long[841372];
    static int[] trust = new int[841372];
    //help parameter
    static double all_avg;
    static int R = 13668104;
    static int an_R;
    static int r_up;
    static int r_down;
    static int an_rup;
    static int an_rdown;

    public static int Iuv(long u, long v) {

        int cnt = 0;

        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (user1[i] == u) {

                cnt++;

            }

            i++;

        }

        return cnt;

    }

    public static int an_Iuv(long u, long v) {

        int cnt = 0;

        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (user1[i] == u) {

                if (status1[i] == 1) {

                    cnt++;
                }

            }

            i++;

        }

        return cnt;

    }

    public static int rv(long v) {
        int cnt = 0;
        int i = index1.get(v);

        while (v == reviewer1[i]) {

            cnt++;

            i++;

        }
        return cnt;
    }

    public static int an_rv(long v) {
        int cnt = 0;
        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (status1[i] == 1) {

                cnt++;

            }

            i++;

        }
        return cnt;

    }

    public static int ru(long u) {
        int cnt = 0;
        int i = index.get(u);

        while (u == user[i]) {
            cnt++;
            i++;

        }
        return cnt;
    }

    public static int an_ru(long u) {
        int cnt = 0;
        int i = index.get(u);

        while (u == user[i]) {
            if (status[i] == 1) {

                cnt++;
            }

            i++;

        }
        return cnt;

    }

    public static double f6a(long u, long v) {

        double s;

        int a = rv(v);
        int b = ru(u);
        int c = Iuv(u, v);

        double part1 = 0;
        double part2 = 0;
        double part3 = 0;

        if (a != 0) {
            part1 = (double) an_rv(v) / a;

        }

        if (b != 0) {
            part2 = (double) an_ru(u) / b;

        }
        if (c != 0) {

            part3 = (double) an_Iuv(u, v) / c;

        }

        s = ((double) an_R / R) + part1 + part2 + part3;

        return s;

    }

    public static int Iuv_up(long u, long v) {

        int cnt = 0;

        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (user1[i] == u) {

                if (rating1[i] == 4 || rating1[i] == 5) {

                    cnt++;
                }

            }

            i++;

        }

        return cnt;

    }

    public static int an_Iuv_up(long u, long v) {

        int cnt = 0;

        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (user1[i] == u) {

                if (status1[i] == 1) {

                    if (rating1[i] == 4 || rating1[i] == 5) {

                        cnt++;

                    }
                }

            }

            i++;

        }

        return cnt;

    }

    public static int rv_up(long v) {
        int cnt = 0;
        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (rating1[i] == 4 || rating1[i] == 5) {
                cnt++;

            }

            i++;

        }
        return cnt;
    }

    public static int an_rv_up(long v) {
        int cnt = 0;
        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (status1[i] == 1) {

                if (rating1[i] == 4 || rating1[i] == 5) {
                    cnt++;

                }

            }

            i++;

        }
        return cnt;

    }

    public static int ru_up(long u) {
        int cnt = 0;
        int i = index.get(u);

        while (u == user[i]) {

            if (rating[i] == 4 || rating[i] == 5) {
                cnt++;

            }

            i++;

        }
        return cnt;
    }

    public static int an_ru_up(long u) {
        int cnt = 0;
        int i = index.get(u);

        while (u == user[i]) {
            if (status[i] == 1) {

                if (rating[i] == 4 || rating[i] == 5) {
                    cnt++;

                }

            }

            i++;

        }
        return cnt;

    }

    public static double f6b(long u, long v) {

        double s;

        int a = rv_up(v);
        int b = ru_up(u);
        int c = Iuv_up(u, v);

        double part1 = 0;
        double part2 = 0;
        double part3 = 0;

        if (a != 0) {
            part1 = (double) an_rv_up(v) / a;

        }

        if (b != 0) {
            part2 = (double) an_ru_up(u) / b;

        }
        if (c != 0) {

            part3 = (double) an_Iuv_up(u, v) / c;

        }

        s = ((double) an_rup / r_up) + part1 + part2 + part3;

        return s;

    }

    public static int Iuv_down(long u, long v) {

        int cnt = 0;

        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (user1[i] == u) {

                if (rating1[i] == 1 || rating1[i] == 2) {

                    cnt++;
                }

            }

            i++;

        }

        return cnt;

    }

    public static int an_Iuv_down(long u, long v) {

        int cnt = 0;

        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (user1[i] == u) {

                if (status1[i] == 1) {

                    if (rating1[i] == 1 || rating1[i] == 2) {

                        cnt++;

                    }
                }

            }

            i++;

        }

        return cnt;

    }

    public static int rv_down(long v) {
        int cnt = 0;
        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (rating1[i] == 1 || rating1[i] == 2) {
                cnt++;

            }

            i++;

        }
        return cnt;
    }

    public static int an_rv_down(long v) {
        int cnt = 0;
        int i = index1.get(v);

        while (v == reviewer1[i]) {

            if (status1[i] == 1) {

                if (rating1[i] == 1 || rating1[i] == 2) {
                    cnt++;

                }

            }

            i++;

        }
        return cnt;

    }

    public static int ru_down(long u) {
        int cnt = 0;
        int i = index.get(u);

        while (u == user[i]) {

            if (rating[i] == 1 || rating[i] == 2) {
                cnt++;

            }

            i++;

        }
        return cnt;
    }

    public static int an_ru_down(long u) {
        int cnt = 0;
        int i = index.get(u);

        while (u == user[i]) {
            if (status[i] == 1) {

                if (rating[i] == 1 || rating[i] == 2) {
                    cnt++;

                }

            }

            i++;

        }
        return cnt;

    }

    public static double f6c(long u, long v) {

        double s;

        int a = rv_down(v);
        int b = ru_down(u);
        int c = Iuv_down(u, v);

        double part1 = 0;
        double part2 = 0;
        double part3 = 0;

        if (a != 0) {
            part1 = (double) an_rv_down(v) / a;

        }

        if (b != 0) {
            part2 = (double) an_ru_down(u) / b;

        }
        if (c != 0) {

            part3 = (double) an_Iuv_down(u, v) / c;

        }

        s = ((double) an_rdown / r_down) + part1 + part2 + part3;

        return s;

    }

    public static void output() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f6c1.txt"));
            PrintStream out1 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f6c2.txt"));
            PrintStream out2 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f6c3.txt"));
            PrintStream out3 = new PrintStream(new FileOutputStream(
                    "C:\\Users\\s6770\\Desktop\\project1\\sources\\f6c4.txt"));

            double r = 0.0;

            for (int i = 0; i < 841372; i++) {

                if (index.get(user_u[i]) != null && index1.get(user_v[i]) != null) {

                    r = f6c(user_u[i], user_v[i]);
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

        ///////////////start reading category.txt///////////////
        String filename1 = "C:\\Users\\s6770\\Desktop\\project1\\sources\\category.txt";
        File file1 = new File(filename1);
        int[] count1 = new int[6];

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
                    if ((int) data == 4 || (int) data == 5) {

                        r_up++;

                    }

                    if ((int) data == 1 || (int) data == 2) {

                        r_down++;

                    }

                    count1[4]++;
                } else if (j == 5) {
                    status1[count1[5]] = (int) data;

                    if ((int) data == 1) {

                        an_R++;
                        if (rating1[count1[4] - 1] == 4 || rating1[count1[4] - 1] == 5) {

                            an_rup++;
                        }

                        if (rating1[count1[4] - 1] == 1 || rating1[count1[4] - 1] == 2) {

                            an_rdown++;

                        }

                    }

                    count1[5]++;

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
