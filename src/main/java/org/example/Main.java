package org.example;

class Main {
    public static long[] dp = new long[1000000];
    public static long clc(int n) {
        if (dp[n] > 0) return dp[n];
        if (n <= 3) return dp[n] = n;
        return dp[n] = clc(n - 1) + clc(n - 2);
    }
    public static void main(String[] args) {
        long mx = 50000000, r = 0, sum = 0;
        int ans = 3;
        while (r < mx) {
            r = clc(ans++);
        }

        System.out.printf("%d:\n%d > %d ? %b\n", --ans, dp[ans], mx, dp[ans] > mx);
        System.out.printf("%d:\n%d > %d ? %b\n", ans - 1, dp[ans - 1], mx, dp[ans - 1] > mx);

        for (int i = 0; i < ans; i++)
            if (dp[i] % 2 == 0) sum += dp[i];
        System.out.printf("sum : %d\n", sum);
    }
}
