package com.crudalchemy.codeclicker.models;


public class Upgrade
{
    Generator generator;
    String name;
    int image;
    String description;
    int cost;
    UpgradeType type;
    boolean isVisible;

    boolean purchased;

    public Upgrade(String name, int image, String description, int cost, UpgradeType type)
    {
        this.name = name;
        this.image = image;
        this.cost = cost;
        this.description = description;
        this.type = type;
    }

    public void increaseMultiplier()
    {
        int newMultiplier = generator.getMultiplier();
        generator.setMultiplier(newMultiplier * 2);
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UpgradeType getType() {
        return type;
    }

    public void setType(UpgradeType type) {
        this.type = type;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
