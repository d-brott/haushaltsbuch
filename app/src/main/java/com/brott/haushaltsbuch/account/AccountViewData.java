package com.brott.haushaltsbuch.account;

public class AccountViewData {

    private double totalAssets;
    private double totalSavings;
    private double totalLiquidAssets;

    private double moneyAmount;
    private double checkAccountAmount;
    private double savingsAmount;
    private double portfolioAmount;


    public AccountViewData(double totalAssets, double totalSavings, double totalLiquidAssets, double moneyAmount, double checkAccountAmount, double savingsAmount, double portfolioAmount) {
        this.totalAssets = totalAssets;
        this.totalSavings = totalSavings;
        this.totalLiquidAssets = totalLiquidAssets;
        this.moneyAmount = moneyAmount;
        this.checkAccountAmount = checkAccountAmount;
        this.savingsAmount = savingsAmount;
        this.portfolioAmount = portfolioAmount;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public double getTotalSavings() {
        return totalSavings;
    }

    public double getTotalLiquidAssets() {
        return totalLiquidAssets;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public double getCheckAccountAmount() {
        return checkAccountAmount;
    }

    public double getSavingsAmount() {
        return savingsAmount;
    }

    public double getPortfolioAmount() {
        return portfolioAmount;
    }
}
