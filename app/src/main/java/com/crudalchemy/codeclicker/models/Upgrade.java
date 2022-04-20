package com.crudalchemy.codeclicker.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity
public class Upgrade
{
    @PrimaryKey(autoGenerate = true)
    public Long id;
    @Ignore
    Generator generator;
    String generatorName;
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

    public Upgrade(String name, int image, String description, int cost, UpgradeType type, Generator generator)
    {
        this.name = name;
        this.image = image;
        this.cost = cost;
        this.description = description;
        this.type = type;
        this.generator = generator;
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
        generatorName = generator.getName();
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

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }
}
