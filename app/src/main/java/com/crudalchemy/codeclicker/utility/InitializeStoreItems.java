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
        game.getGeneratorList().add(new Generator("Extra Monitor", R.drawable.monitor_1,"Extra Monitor Description", 10, 1.07, 1));
        game.getGeneratorList().add(new Generator("Firewall", R.drawable.ethernet_switch_hub, "Firewall Description", 300, 1.15, 5));
        game.getGeneratorList().add(new Generator("Database", R.drawable.databases, "Database Description", 720, 1.14, 15));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
       /* game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Vending Machine", R.drawable.vending_machine_2,"Vending Machine Description", 8640, 1.13, 360));
        */
        Upgrade newKeyCaps = new Upgrade("New Keycaps", R.drawable.keycaps, "Keycaps Description", 1, CLICK_EFFICIENCY);
        Upgrade newKeyCaps2 = new Upgrade("Newer Keycaps", R.drawable.keycaps, "Keycaps Description", 2, CLICK_EFFICIENCY);
        Upgrade higherRes = new Upgrade("60hz Refresh Rate", R.drawable.monitor_refresh_60, "Can you really tell anything faster?", 50, GENERATOR_EFFICIENCY);
        Upgrade moreBricks = new Upgrade("More Bricks", R.drawable.internet_www, "More Bricks Description", 50, GENERATOR_EFFICIENCY);
        Upgrade javascript = new Upgrade("SQL Schemas", R.drawable.file_type_sql, "JavaScript Description", 75, GLOBAL_LINE_PRODUCTION_MULTIPLIER);
        higherRes.setGenerator(game.getGeneratorList().get(0));
        moreBricks.setGenerator(game.getGeneratorList().get(1));
        game.getUpgradeList().add(newKeyCaps);
        game.getUpgradeList().add(newKeyCaps2);
        game.getUpgradeList().add(higherRes);
        game.getUpgradeList().add(moreBricks);
        game.getUpgradeList().add(javascript);
    }
}
