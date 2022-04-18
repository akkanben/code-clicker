package com.crudalchemy.codeclicker.models;


public class Upgrade
{
    Generator generator;
    String name;
    String description;
    double appearThreshold;


    public Upgrade(Generator generator, String name, String description, double appearThreshold)
    {
        this.generator = generator;
        this.name = name;
        this.description = description;
        this.appearThreshold = appearThreshold;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAppearThreshold() {
        return appearThreshold;
    }

    public void setAppearThreshold(double appearThreshold) {
        this.appearThreshold = appearThreshold;
    }
}
