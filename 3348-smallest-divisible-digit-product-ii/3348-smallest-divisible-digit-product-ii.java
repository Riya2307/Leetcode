import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t into 2, 3, 5, 7
        long tempT = t;
        int count2 = 0, count3 = 0, count5 = 0, count7 = 0;
        
        while (tempT % 2 == 0) { count2++; tempT /= 2; }
        while (tempT % 3 == 0) { count3++; tempT /= 3; }
        while (tempT % 5 == 0) { count5++; tempT /= 5; }
        while (tempT % 7 == 0) { count7++; tempT /= 7; }
        
        // If t has prime factors other than 2, 3, 5, 7, it's impossible
        if (tempT > 1) return "-1";

        int n = num.length();
        int[] numDigits = new int[n];
        for (int i = 0; i < n; i++) {
            numDigits[i] = num.charAt(i) - '0';
        }

        // Prefixes factor count tracking
        // Check if num itself is valid (contains no 0 and digit product is divisible by t)
        boolean zeroFree = true;
        int cur2 = 0, cur3 = 0, cur5 = 0, cur7 = 0;
        for (int i = 0; i < n; i++) {
            if (numDigits[i] == 0) {
                zeroFree = false;
                break;
            }
            addFactors(numDigits[i], 1, curCounts(cur2, cur3, cur5, cur7));
            cur2 += factor2(numDigits[i]);
            cur3 += factor3(numDigits[i]);
            cur5 += (numDigits[i] == 5 ? 1 : 0);
            cur7 += (numDigits[i] == 7 ? 1 : 0);
        }

        if (zeroFree && cur2 >= count2 && cur3 >= count3 && cur5 >= count5 && cur7 >= count7) {
            return num;
        }

        // Prefix factor counts before index i
        int[] pref2 = new int[n + 1];
        int[] pref3 = new int[n + 1];
        int[] pref5 = new int[n + 1];
        int[] pref7 = new int[n + 1];
        int firstZeroIdx = n;

        for (int i = 0; i < n; i++) {
            pref2[i + 1] = pref2[i] + factor2(numDigits[i]);
            pref3[i + 1] = pref3[i] + factor3(numDigits[i]);
            pref5[i + 1] = pref5[i] + (numDigits[i] == 5 ? 1 : 0);
            pref7[i + 1] = pref7[i] + (numDigits[i] == 7 ? 1 : 0);
            if (numDigits[i] == 0 && firstZeroIdx == n) {
                firstZeroIdx = i;
            }
        }

        // Step 2: Try matching prefix of length L (from n-1 down to 0)
        for (int L = n - 1; L >= 0; L--) {
            // If prefix contains a '0', we cannot match this length or any longer length
            if (firstZeroIdx < L) continue;

            int startDigit = numDigits[L] + 1;
            for (int d = startDigit; d <= 9; d++) {
                int req2 = Math.max(0, count2 - pref2[L] - factor2(d));
                int req3 = Math.max(0, count3 - pref3[L] - factor3(d));
                int req5 = Math.max(0, count5 - pref5[L] - (d == 5 ? 1 : 0));
                int req7 = Math.max(0, count7 - pref7[L] - (d == 7 ? 1 : 0));

                List<Integer> reqDigits = getMinDigits(req2, req3, req5, req7);
                int remLen = n - 1 - L;

                if (reqDigits.size() <= remLen) {
                    // Valid match found! Construct answer
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < L; i++) sb.append(numDigits[i]);
                    sb.append(d);
                    
                    int ones = remLen - reqDigits.size();
                    for (int i = 0; i < ones; i++) sb.append('1');
                    for (int digit : reqDigits) sb.append(digit);
                    
                    return sb.toString();
                }
            }
        }

        // Step 3: If no length n works, construct length n + 1 (or minimum required length)
        List<Integer> reqDigits = getMinDigits(count2, count3, count5, count7);
        int targetLen = Math.max(n + 1, reqDigits.size());
        
        StringBuilder sb = new StringBuilder();
        int ones = targetLen - reqDigits.size();
        for (int i = 0; i < ones; i++) sb.append('1');
        for (int digit : reqDigits) sb.append(digit);

        return sb.toString();
    }

    private static List<Integer> getMinDigits(int c2, int c3, int c5, int c7) {
        List<Integer> digits = new ArrayList<>();
        
        // Add 7s and 5s
        for (int i = 0; i < c7; i++) digits.add(7);
        for (int i = 0; i < c5; i++) digits.add(5);

        // Pack 3s into 9s
        int nines = c3 / 2;
        int rem3 = c3 % 2;
        for (int i = 0; i < nines; i++) digits.add(9);

        // Pack 2s into 8s
        int eights = c2 / 3;
        int rem2 = c2 % 3;
        for (int i = 0; i < eights; i++) digits.add(8);

        // Handle leftovers of 2s and 3s
        if (rem2 == 1 && rem3 == 0) digits.add(2);
        else if (rem2 == 2 && rem3 == 0) digits.add(4);
        else if (rem2 == 0 && rem3 == 1) digits.add(3);
        else if (rem2 == 1 && rem3 == 1) digits.add(6);
        else if (rem2 == 2 && rem3 == 1) { digits.add(2); digits.add(6); }

        Collections.sort(digits);
        return digits;
    }

    private static int factor2(int d) {
        if (d == 2 || d == 6) return 1;
        if (d == 4) return 2;
        if (d == 8) return 3;
        return 0;
    }

    private static int factor3(int d) {
        if (d == 3 || d == 6) return 1;
        if (d == 9) return 2;
        return 0;
    }

    private static void addFactors(int d, int mult, int[] counts) {}
    private static int[] curCounts(int a, int b, int c, int d) { return null; }
}