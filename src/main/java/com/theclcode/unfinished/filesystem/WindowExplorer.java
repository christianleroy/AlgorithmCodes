package com.theclcode.unfinished.filesystem;

import java.util.Scanner;

public class WindowExplorer {

    /**************** START OF USER SOLUTION ****************/

    static void init() {

    }

    static int mkdir(int m_window, char name[]) {

        return 0;
    }

    static int chdir(int m_window, char name[]) {

        return 0;
    }

    static int rmdir(int m_window, char name[]) {

        return 0;
    }

    static int mvdir(int m_window, char name[]) {

        return 0;
    }

    /***************** END OF USER SOLUTION *****************/


    static int seed = 5;  // seed can be changed

    static int pseudo_rand()
    {
        seed = seed * 214013 + 2531011;
        return (seed >> 16) & 0x7FFF;
    }

    static void mstrcpy(char dst[], String src) {
        int c;
        for (c = 0; c < src.length(); c++)
            dst[c] = src.charAt(c);
        dst[c] = 0;
    }

    static void make_string(char str[], int alpha, int minlength, int maxlength)
    {
        int length = minlength + pseudo_rand() % (maxlength - minlength);
        for (int i = 0; i < length; ++i) {
            str[i] = (char)((int)'a' + (pseudo_rand() % alpha));
        }
        str[length] = 0;
    }

    static Scanner sc;

    static void run()
    {
        char str[] = new char[100];
        int n, alpha;
        int ratio[] = new int[4];

        seed = sc.nextInt();
        n = sc.nextInt();
        alpha = sc.nextInt();
        ratio[0] = sc.nextInt();
        ratio[1] = sc.nextInt();
        ratio[2] = sc.nextInt();
        ratio[3] = sc.nextInt();

        init();

        for (int i = 0; i < n; ++i)
        {
            int cmd = pseudo_rand() % 100 + 1;
            int ans = 0;

            if (cmd <= ratio[0])
            {
                make_string(str, alpha, 5, 10);
                int user_ans = mkdir(pseudo_rand() % 2, str);

                System.out.printf("%d\n", user_ans);
            }
            else if (cmd <= ratio[1])
            {
                int cmd2 = pseudo_rand() % 100 + 1;
                if (cmd2 <= 5)
                {
                    mstrcpy(str, "/");
                }
                else if (cmd2 <= 10)
                {
                    mstrcpy(str, "..");
                }
                else
                {
                    make_string(str, alpha, 1, 3);
                }
                int x = pseudo_rand() % 2;
                int user_ans = chdir(x, str);

                System.out.printf("%d\n", user_ans);
            }
            else if (cmd <= ratio[2])
            {
                make_string(str, alpha, 1, 3);

                int user_ans = rmdir(pseudo_rand() % 2, str);

                System.out.printf("%d\n", user_ans);
            }
            else
            {
                make_string(str, alpha, 1, 3);

                int user_ans = mvdir(pseudo_rand() % 2, str);

                System.out.printf("%d\n", user_ans);
            }
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            System.out.printf("Case #%d:\n", tc);
            run();
        }
    }

    static class Tree {

    }

    static class TreeNode {
        private TreeNode[] children = new TreeNode[26];

        public TreeNode[] getChildren() {
            return children;
        }

        public void setChildren(TreeNode[] children) {
            this.children = children;
        }
    }
}
