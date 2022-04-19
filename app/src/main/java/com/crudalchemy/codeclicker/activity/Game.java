package com.crudalchemy.codeclicker.activity;

import android.os.Parcel;
import android.os.Parcelable;

import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class Game {

    double lifetimeLineCount;
    double linePerSecond;
    double currentLineCount;
    int linesPerClick;
    double partsOfASecond;
    List<Generator> generatorList;
    List<Upgrade> upgradeList;



    //int parcelData;

    public Game() {
        lifetimeLineCount = 1000.0;
        linePerSecond = 0.0;
        linesPerClick = 1;
        currentLineCount = 0.0;
        partsOfASecond = 0.0;
        generatorList = new ArrayList<>();
        upgradeList = new ArrayList<>();
    }

    public void buyGenerator(Generator generator) {
        currentLineCount -= generator.getNextPrice();
        linePerSecond += generator.getNextProductivity();
        generator.add();
    }

    public void buyUpgrade(Upgrade upgrade) {
        switch (upgrade.getType()) {
            case GENERATOR_EFFICIENCY:
                currentLineCount -= upgrade.getCost();
                upgrade.increaseMultiplier();
                break;
            case GLOBAL_LINE_PRODUCTION_MULTIPLIER:
                linePerSecond *= 0.01;
                break;
            case CLICK_EFFICIENCY:
                linesPerClick *= 2;
                break;
        }
        upgradeList.remove(upgrade);
    }

    public void checkForVisibilityToggle() {
        for (Generator generator : generatorList) {
            if (!generator.isVisible() && generator.getCost() <= lifetimeLineCount)
                generator.setVisible(true);
        }
        for (Upgrade upgrade : upgradeList) {
            if (upgrade.getCost() <= lifetimeLineCount)
                upgrade.setVisible(true);
        }
    }

    public List<Generator> getGeneratorList() {
        return generatorList;
    }

    public void setGeneratorList(List<Generator> generatorList) {
        this.generatorList = generatorList;
    }

    public List<Upgrade> getUpgradeList() {
        return upgradeList;
    }

    public void setUpgradeList(List<Upgrade> upgradeList) {
        this.upgradeList = upgradeList;
    }

    public double getCurrentLineCount() {
        return currentLineCount;
    }
}
