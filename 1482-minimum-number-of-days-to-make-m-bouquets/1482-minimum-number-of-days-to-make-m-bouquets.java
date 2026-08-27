class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        
        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }

        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            

            if (canMakeBouquets(bloomDay, m, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;  

            }
        }

        return ans;
    }

    private boolean canMakeBouquets(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0;
        int flowers = 0;

        for (int bDay : bloomDay) {
            if (bDay <= day) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0; 
                }
            } else {
                
                flowers=0;
            }
        }

        return bouquets >= m;
    }
}