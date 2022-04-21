package com.crudalchemy.codeclicker.activity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.crudalchemy.codeclicker.adapter.GeneratorMenuRecyclerViewAdapter;
import com.crudalchemy.codeclicker.adapter.UpgradeMenuRecyclerViewAdapter;
import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @PrimaryKey(autoGenerate = true)
    public Long id;
    double lifetimeLineCount;
    double linePerSecond;
    double currentLineCount;
    int linesPerClick;
    double partsOfASecond;
    @Ignore
    List<Generator> generatorList;
    @Ignore
    List<Upgrade> upgradeList;

    //int parcelData;

    public Game() {
        lifetimeLineCount = 0.0;
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
                upgrade.increaseMultiplier();
                break;
            case GLOBAL_LINE_PRODUCTION_MULTIPLIER:
                linePerSecond *= 0.01;
                break;
            case CLICK_EFFICIENCY:
                linesPerClick *= 2;
                break;
        }
        upgrade.setPurchased(true);
        upgradeList.remove(upgrade);
        currentLineCount -= upgrade.getCost();
    }

    public void updateItemLists(GeneratorMenuRecyclerViewAdapter generatorAdapter, UpgradeMenuRecyclerViewAdapter upgradeAdapter) {
        for (Generator generator : generatorList) {
            if (generator.getNextPrice() <= lifetimeLineCount) {
                generator.setVisible(true);
            }
            if (!generator.isPurchasable() && generator.getNextPrice() <= currentLineCount) {
                generator.setPurchasable(true);
                if (generatorAdapter != null)
                    generatorAdapter.notifyItemChanged(generatorList.indexOf(generator));
            } else if (generator.isPurchasable() && generator.getNextPrice() > currentLineCount) {
                generator.setPurchasable(false);
                if (generatorAdapter != null)
                    generatorAdapter.notifyItemChanged(generatorList.indexOf(generator));
            }
        }
        for (Upgrade upgrade : upgradeList) {
            if (upgrade.getCost() <= lifetimeLineCount) {
                upgrade.setVisible(true);
            }
            if (!upgrade.isPurchasable() && upgrade.getCost() <= currentLineCount) {
                upgrade.setPurchasable(true);
                if (upgradeAdapter != null)
                    upgradeAdapter.notifyItemChanged(upgradeList.indexOf(upgrade));
            } else if (upgrade.isPurchasable() && upgrade.getCost() > currentLineCount) {
                upgrade.setPurchasable(false);
                if (upgradeAdapter != null)
                    upgradeAdapter.notifyItemChanged((upgradeList.indexOf(upgrade)));
            }
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

    public double getLifetimeLineCount() {
        return lifetimeLineCount;
    }

    public void setLifetimeLineCount(double lifetimeLineCount) {
        this.lifetimeLineCount = lifetimeLineCount;
    }

    public double getLinePerSecond() {
        return linePerSecond;
    }

    public void setLinePerSecond(double linePerSecond) {
        this.linePerSecond = linePerSecond;
    }

    public void setCurrentLineCount(double currentLineCount) {
        this.currentLineCount = currentLineCount;
    }

    public int getLinesPerClick() {
        return linesPerClick;
    }

    public void setLinesPerClick(int linesPerClick) {
        this.linesPerClick = linesPerClick;
    }

    public double getPartsOfASecond() {
        return partsOfASecond;
    }

    public void setPartsOfASecond(double partsOfASecond) {
        this.partsOfASecond = partsOfASecond;
    }
}
