class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), ans);
        return ans;
    }
    private void backtrack(int start, int k, int target, List<Integer> list, List<List<Integer>> ans) {
        if (target == 0 && list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if (target < 0 || list.size() == k) return;
        for (int i = start; i <= 9; i++) {
            list.add(i);
            backtrack(i + 1, k, target - i, list, ans);
            list.remove(list.size() - 1);
        }
    }
}