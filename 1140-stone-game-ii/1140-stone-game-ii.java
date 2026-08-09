class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // suffixSum[i] holds total stones from index i to n - 1
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // dp[i][M] stores max stones player can get starting from index i with parameter M
        int[][] dp = new int[n][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int M = 1; M <= n; M++) {
                // If the player can take all remaining piles
                if (i + 2 * M >= n) {
                    dp[i][M] = suffixSum[i];
                } else {
                    // Try taking X piles (1 <= X <= 2 * M)
                    for (int X = 1; X <= 2 * M; X++) {
                        int nextM = Math.max(M, X);
                        dp[i][M] = Math.max(dp[i][M], suffixSum[i] - dp[i + X][nextM]);
                    }
                }
            }
        }

        return dp[0][1];
    }
}