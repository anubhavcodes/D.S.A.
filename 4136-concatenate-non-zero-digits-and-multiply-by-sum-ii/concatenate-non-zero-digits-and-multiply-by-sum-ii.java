class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        int[] digitSum = new int[n + 1];
        int[] nonZeroCnt = new int[n + 1];
        long[] prefixNum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            digitSum[i + 1] = digitSum[i] + d;
            nonZeroCnt[i + 1] = nonZeroCnt[i];
            prefixNum[i + 1] = prefixNum[i];
            if (d != 0) {
                nonZeroCnt[i + 1]++;
                prefixNum[i + 1] = (prefixNum[i] * 10 + d) % MOD;
            }
        }
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int cnt = nonZeroCnt[r + 1] - nonZeroCnt[l];
            int sum = digitSum[r + 1] - digitSum[l];
            long x = (prefixNum[r + 1] - (prefixNum[l] * pow10[cnt]) % MOD + MOD) % MOD;
            ans[i] = (int) ((x * sum) % MOD);
        }
        return ans;
    }
}