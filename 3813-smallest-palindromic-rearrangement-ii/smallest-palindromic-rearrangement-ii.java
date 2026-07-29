class Solution {
    private static final long LIMIT = 1000001L;
    private long comb(int n, int r) {
        if (r > n) return 0;
        r = Math.min(r, n - r);
        long ans = 1;
        for (int i = 1; i <= r; i++) {
            ans = ans * (n - r + i) / i;
            if (ans >= LIMIT) return LIMIT;
        }
        return ans;
    }
    private long countWays(int[] cnt) {
        int total = 0;
        for (int x : cnt) total += x;
        long ans = 1;
        int rem = total;
        for (int x : cnt) {
            if (x == 0) continue;
            ans *= comb(rem, x);
            if (ans >= LIMIT) return LIMIT;
            rem -= x;
        }
        return ans;
    }
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int[] half = new int[26];
        String mid = "";
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
        }
        if (countWays(half) < k) return "";
        StringBuilder left = new StringBuilder();
        int len = 0;
        for (int x : half) len += x;
        while (left.length() < len) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) continue;
                half[i]--;
                long ways = countWays(half);
                if (ways >= k) {
                    left.append((char) ('a' + i));
                    break;
                } else {
                    k -= ways;
                    half[i]++;
                }
            }
        }
        StringBuilder right = new StringBuilder(left).reverse();
        return left.toString() + mid + right.toString();
    }
}