package com.crudalchemy.codeclicker.utility;

import static com.crudalchemy.codeclicker.models.UpgradeType.CLICK_EFFICIENCY;
import static com.crudalchemy.codeclicker.models.UpgradeType.GENERATOR_EFFICIENCY;
import static com.crudalchemy.codeclicker.models.UpgradeType.GLOBAL_LINE_PRODUCTION_MULTIPLIER;

import com.crudalchemy.codeclicker.activity.Game;
import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;

public class InitializeStoreItems {

    public static void hardCodedStoreItems(Game game) {
        game.getGeneratorList().add(new Generator("Extra Monitor", R.drawable.imgtest,"Extra Monitor Description", 4, 1.07, 1.67));
        game.getGeneratorList().add(new Generator("Firewall", R.drawable.img502304, "Firewall Description", 60, 1.15, 20));
        game.getGeneratorList().add(new Generator("Database", R.drawable.img647908, "Database Description", 720, 1.14, 90));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.img550504,"Vending Machine Description", 8640, 1.13, 360));
        Upgrade higherRes = new Upgrade("Higher Resolution", R.drawable.img689338, "Higher Resolution Description", 1000, GENERATOR_EFFICIENCY);
        Upgrade moreBricks = new Upgrade("More Bricks", R.drawable.img916023, "More Bricks Description", 5000, GENERATOR_EFFICIENCY);
        Upgrade javascript = new Upgrade("JavaScript", R.drawable.img924514, "JavaScript Description", 7000, GLOBAL_LINE_PRODUCTION_MULTIPLIER);
        Upgrade newKeyCaps = new Upgrade("New Keycaps", R.drawable.img874928, "Keycaps Description", 750, CLICK_EFFICIENCY);
        higherRes.setGenerator(game.getGeneratorList().get(0));
        moreBricks.setGenerator(game.getGeneratorList().get(1));
        game.getUpgradeList().add(higherRes);
        game.getUpgradeList().add(moreBricks);
        game.getUpgradeList().add(javascript);
        game.getUpgradeList().add(newKeyCaps);
    }
}
