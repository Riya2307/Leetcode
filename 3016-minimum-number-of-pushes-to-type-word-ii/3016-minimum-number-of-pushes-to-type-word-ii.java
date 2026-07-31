import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count frequency of each letter
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in ascending order
        Arrays.sort(freq);
        
        // Step 3: Process highest frequencies first
        int totalPushes = 0;
        for (int i = 0; i < 26; i++) {
            // Processing from largest frequency to smallest
            int count = freq[25 - i];
            if (count == 0) break;
            
            int pushesPerChar = (i / 8) + 1;
            totalPushes += count * pushesPerChar;
        }
        
        return totalPushes;
    }
}