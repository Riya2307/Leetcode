class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int totalLength = m + n;
        
        // We only need to iterate up to the middle element
        int targetIndex = totalLength / 2;
        
        int p1 = 0; // Pointer for nums1
        int p2 = 0; // Pointer for nums2
        
        int current = 0;
        int last = 0;
        
        // Simulating the merge process up to the median point
        for (int i = 0; i <= targetIndex; i++) {
            last = current; // Keep track of the previous element (needed for even totals)
            
            if (p1 < m && (p2 >= n || nums1[p1] <= nums2[p2])) {
                current = nums1[p1];
                p1++;
            } else {
                current = nums2[p2];
                p2++;
            }
        }
        
        // If total length is even, return the average of the two middle elements
        if (totalLength % 2 == 0) {
            return (last + current) / 2.0;
        } else {
            // If total length is odd, return the middle element
            return current;
        }
    }
}