class Solution {
    public int divide(int dividend, int divisor) {
        // Handle overflow edge case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine output sign
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert both to negative integers to avoid overflow when taking abs
        int a = dividend < 0 ? dividend : -dividend;
        int b = divisor < 0 ? divisor : -divisor;

        int quotient = 0;

        while (a <= b) {
            int tempDivisor = b;
            int count = 1;

            // Double the divisor using bitwise shift until it exceeds 'a'
            while (tempDivisor >= (Integer.MIN_VALUE >> 1) && a <= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                count <<= 1;
            }

            a -= tempDivisor;
            quotient += count;
        }

        return negative ? -quotient : quotient;
    }
}