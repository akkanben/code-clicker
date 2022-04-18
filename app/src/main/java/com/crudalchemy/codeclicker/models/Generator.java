package com.crudalchemy.codeclicker.models;

public class Generator
{
    String name;



    String image;
    String description;
    int count = 1;
    int cost;
    double growthRate;
    double productivityBase;
    int multiplier = 1;
    double appearThreshold;
    double currentProductivity = productivityBase;

    public Generator(String name, String image, String description, int cost, double growthRate,
                     double productivityBase, double appearThreshold)
    {
        this.name = name;
        this.image = image;
        this.description = description;
        this.cost = cost;
        this.growthRate = growthRate;
        this.productivityBase = productivityBase;
        this.appearThreshold = appearThreshold;
    }

    public int getNextPrice()
    {
        return (int) (growthRate * Math.pow(cost,count));
    }

    public double getNextProductivity()
    {
        return productivityBase * count * multiplier;
    }

    public void add()
    {
        count++;
        currentProductivity = getNextProductivity();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    public double getProductivityBase() {
        return productivityBase;
    }

    public void setProductivityBase(double productivityBase) {
        this.productivityBase = productivityBase;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public double getAppearThreshold() {
        return appearThreshold;
    }

    public void setAppearThreshold(double appearThreshold) {
        this.appearThreshold = appearThreshold;
    }

    public double getCurrentProductivity() {
        return currentProductivity;
    }

    public void setCurrentProductivity(double currentProductivity) {
        this.currentProductivity = currentProductivity;
    }

}
