package com.crudalchemy.codeclicker.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Generator
{
    @PrimaryKey(autoGenerate = true)
    public Long id;
    String name;
    int image;
    String description;
    int count = 0;
    int baseCost;
    double priceGrowthRate;
    double productivityBase;
    int multiplier = 1;
    double currentProductivity;
    boolean isVisible;
    boolean isPurchasable;

    public Generator(String name, int image, String description, int baseCost, double priceGrowthRate,
                     double productivityBase)
    {
        this.name = name;
        this.image = image;
        this.description = description;
        this.baseCost = baseCost;
        this.priceGrowthRate = priceGrowthRate;
        this.productivityBase = productivityBase;
        currentProductivity = productivityBase;
    }

    public int getNextPrice()
    {
        return (int) (baseCost * Math.pow(priceGrowthRate,count + 1));
    }

    public double getNextProductivity()
    {
        return productivityBase * (count + 1) * multiplier;
    }

    public double getRateIncrease(){
        //return (productivityBase * (count + 1) * multiplier) - (productivityBase * count * multiplier);
        if (count < 1)
            return getNextProductivity();
        return getNextProductivity() - currentProductivity;
    }

    public void increment()
    {
        currentProductivity = getNextProductivity();
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public double getPriceGrowthRate() {
        return priceGrowthRate;
    }

    public void setPriceGrowthRate(double priceGrowthRate) {
        this.priceGrowthRate = priceGrowthRate;
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

    public double getCurrentProductivity() {
        return currentProductivity;
    }

    public void setCurrentProductivity(double currentProductivity) {
        this.currentProductivity = currentProductivity;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isPurchasable() {
        return isPurchasable;
    }

    public void setPurchasable(boolean purchasable) {
        isPurchasable = purchasable;
    }
}
