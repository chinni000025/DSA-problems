package DP;

public class BuyAndSellTheStock {
    public static void main(String[] args) {
        int prizes[] = { 7, 1, 5, 3, 6, 4 };
        System.out.println(stockMax(prizes));
    }
    //Time complexity:O(n).
    //space complexity:O(1).
    public static int stockMax(int prizes[]) {
        int buy = prizes[0];
        int profit = 0;

        for (int i = 1; i < prizes.length; i++) { 
            if (prizes[i] < buy) {
                buy = prizes[i];
            }
            profit = Math.max(profit, prizes[i]-buy);
        }
        return profit;
    }
}
