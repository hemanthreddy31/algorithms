package leetcode.arrays;

/**
 * LeetCode 121: Best Time to Buy and Sell Stock
 * 
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a 
 * different day in the future to sell that stock.
 * 
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any 
 * profit, return 0.
 * 
 * Example:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 */
public class BestTimeToBuyAndSellStock {
    
    /**
     * Approach 1: Brute Force
     * Time Complexity: O(n²) - Check every possible pair of buy and sell days
     * Space Complexity: O(1) - Only using constant extra space
     * 
     * Algorithm:
     * 1. For each day i, consider it as the buy day
     * 2. For each day j > i, consider it as the sell day
     * 3. Calculate profit = prices[j] - prices[i]
     * 4. Keep track of the maximum profit
     */
    public int maxProfitBruteForce(int[] prices) {
        int maxProfit = 0;
        
        // Try every possible buy day
        for (int buyDay = 0; buyDay < prices.length - 1; buyDay++) {
            // For each buy day, try every possible sell day after it
            for (int sellDay = buyDay + 1; sellDay < prices.length; sellDay++) {
                // Calculate profit for this buy-sell pair
                int profit = prices[sellDay] - prices[buyDay];
                // Update max profit if current profit is higher
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        
        return maxProfit;
    }
    
    /**
     * Approach 2: One Pass (Optimal) - Kadane's Algorithm Variant
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(1) - Only using two variables
     * 
     * Key Insight: We need to find the largest difference between two numbers where the 
     * smaller number comes before the larger number.
     * 
     * Algorithm:
     * 1. Keep track of the minimum price seen so far
     * 2. For each price, calculate profit if we sell at current price
     * 3. Update maximum profit if current profit is higher
     * 4. Update minimum price if current price is lower
     */
    public int maxProfitOptimal(int[] prices) {
        // Edge case: empty or single element array
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int minPrice = prices[0];  // Minimum price seen so far
        int maxProfit = 0;         // Maximum profit achievable
        
        // Iterate through all prices starting from day 1
        for (int i = 1; i < prices.length; i++) {
            // Calculate profit if we sell at current price
            int currentProfit = prices[i] - minPrice;
            
            // Update max profit if current profit is better
            maxProfit = Math.max(maxProfit, currentProfit);
            
            // Update min price for future transactions
            minPrice = Math.min(minPrice, prices[i]);
        }
        
        return maxProfit;
    }
    
    /**
     * Approach 3: Dynamic Programming
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(1) - Only using constant extra space
     * 
     * DP State: maxProfit[i] = maximum profit achievable by day i
     * DP Transition: maxProfit[i] = max(maxProfit[i-1], prices[i] - minPrice)
     * 
     * This approach explicitly shows the DP nature of the problem
     */
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int maxProfitSoFar = 0;    // dp[i-1]
        int minPriceSoFar = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            // Two choices at each day:
            // 1. Don't sell today: profit remains same as yesterday
            // 2. Sell today: profit = today's price - minimum price so far
            maxProfitSoFar = Math.max(
                maxProfitSoFar,                    // Don't sell
                prices[i] - minPriceSoFar          // Sell today
            );
            
            // Update minimum price for future days
            minPriceSoFar = Math.min(minPriceSoFar, prices[i]);
        }
        
        return maxProfitSoFar;
    }
    
    /**
     * Approach 4: Track Daily Changes
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(1) - Only using constant extra space
     * 
     * This approach converts the problem to finding maximum subarray sum
     * where array elements are daily price changes
     */
    public int maxProfitDailyChanges(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int maxProfit = 0;
        int maxEndingHere = 0;
        
        // Process daily changes
        for (int i = 1; i < prices.length; i++) {
            int dailyChange = prices[i] - prices[i - 1];
            
            // Kadane's algorithm on daily changes
            maxEndingHere = Math.max(0, maxEndingHere + dailyChange);
            maxProfit = Math.max(maxProfit, maxEndingHere);
        }
        
        return maxProfit;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();
        
        // Test case 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test Case 1: prices = [7,1,5,3,6,4]");
        System.out.println("Brute Force: " + solution.maxProfitBruteForce(prices1));
        System.out.println("Optimal: " + solution.maxProfitOptimal(prices1));
        System.out.println("DP: " + solution.maxProfitDP(prices1));
        System.out.println("Daily Changes: " + solution.maxProfitDailyChanges(prices1));
        
        // Test case 2: No profit possible
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("\nTest Case 2: prices = [7,6,4,3,1]");
        System.out.println("Brute Force: " + solution.maxProfitBruteForce(prices2));
        System.out.println("Optimal: " + solution.maxProfitOptimal(prices2));
        System.out.println("DP: " + solution.maxProfitDP(prices2));
        System.out.println("Daily Changes: " + solution.maxProfitDailyChanges(prices2));
        
        // Test case 3: Single element
        int[] prices3 = {5};
        System.out.println("\nTest Case 3: prices = [5]");
        System.out.println("Optimal: " + solution.maxProfitOptimal(prices3));
    }
} 