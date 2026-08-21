class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        long high = minCoin * (long) k;

        while (low < high) {
            long mid = low + (high - low) / 2;
            if (countAmounts(coins, mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private long countAmounts(int[] coins, long target) {
        int n = coins.length;
        long totalCount = 0;

        // Iterate over all subsets using bitmasking
        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int subsetSize = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subsetSize++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if (subsetSize % 2 == 1) {
                totalCount += target / currentLcm;
            } else {
                totalCount -= target / currentLcm;
            }
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}