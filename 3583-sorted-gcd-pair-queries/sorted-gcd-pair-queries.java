class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] freq = new int[mx + 1];
        for (int x : nums) {
            freq[x]++;
        }
        long[] cntGcd = new long[mx + 1];
        for (int g = mx; g >= 1; g--) {
            long cnt = 0;
            for (int multiple = g; multiple <= mx; multiple += g) {
                cnt += freq[multiple];
                cntGcd[g] -= cntGcd[multiple];
            }
            cntGcd[g] += cnt * (cnt - 1) / 2;
        }
        for (int i = 2; i <= mx; i++) {
            cntGcd[i] += cntGcd[i - 1];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int l = 1, r = mx;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (cntGcd[mid] > q) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = l;
        }
        return ans;
    }
}