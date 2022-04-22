package com.crudalchemy.codeclicker.utility;

import static com.crudalchemy.codeclicker.models.UpgradeType.CLICK_EFFICIENCY;
import static com.crudalchemy.codeclicker.models.UpgradeType.DEMO_BOOST;
import static com.crudalchemy.codeclicker.models.UpgradeType.GENERATOR_EFFICIENCY;
import static com.crudalchemy.codeclicker.models.UpgradeType.GLOBAL_LINE_PRODUCTION_MULTIPLIER;
import static com.crudalchemy.codeclicker.models.UpgradeType.SINGULARITY;

import com.crudalchemy.codeclicker.activity.Game;
import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class InitializeStoreItems {

    public static void hardCodedStoreItems(Game game) {
        List<Generator> generatorList = game.getGeneratorList();
        List<Upgrade> upgradeList = game.getUpgradeList();


        // GENERATORS
        generatorList.add(new Generator((long) generatorList.size(), "Coffee", R.drawable.coffee_mug,"Liquid focus", 15, 1.15, 0.55));
        generatorList.add(new Generator((long) generatorList.size(), "Monitor", R.drawable.monitor_1,"If TWO are this useful...", 300, 1.15, 5));
        generatorList.add(new Generator((long) generatorList.size(), "RAM", R.drawable.ram, "More Stack Overflow tabs at once", 720, 1.14, 15));
        generatorList.add(new Generator((long) generatorList.size(), "Storage", R.drawable.hard_drive_hdd, "Hard drive is full -- time for a new hard drive", 8640, 1.13, 360));
        generatorList.add(new Generator((long) generatorList.size(), "Server", R.drawable.server_rack,"Stack em' up like pancakes", 103680, 1.12, 2160));
        generatorList.add(new Generator((long) generatorList.size(), "AI Assistant", R.drawable.robot_binary,"\"NULL POINTER AT: FIRST DIRECTIVE\"", 803680, 1.11, 25160));

        //DEMO
        upgradeList.add(new Upgrade((long) upgradeList.size(), "Demo Tool 1", R.drawable.cash_funding_loot_money_1, "Boost", 100, DEMO_BOOST));
        upgradeList.add(new Upgrade((long) upgradeList.size(), "Demo Tool 2", R.drawable.cash_funding_loot_money_2, "Boost", 1000, DEMO_BOOST));
        upgradeList.add(new Upgrade((long) upgradeList.size(), "Demo Tool 3", R.drawable.cash_funding_loot_money_3, "Boost", 100000, DEMO_BOOST));
        upgradeList.add(new Upgrade((long) upgradeList.size(), "Demo Tool 4", R.drawable.cash_funding_loot_money_3, "Boost", 1000000, DEMO_BOOST));
        upgradeList.add(new Upgrade((long) upgradeList.size(), "Singularity", R.drawable.singularity, "Boost", 10000000, SINGULARITY));

        // KEY PRESS UPGRADES
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Mechanical Keyboard", R.drawable.keycaps, "If it clacks like a duck...", 25, CLICK_EFFICIENCY));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Lumbar Support", R.drawable.chair_3, "Code with your knees, not your back", 50, CLICK_EFFICIENCY));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"ABS Keys", R.drawable.key_caps_3, "Shiny!", 1000, CLICK_EFFICIENCY));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Head rest", R.drawable.chair_2, "Like a pillow", 5500, CLICK_EFFICIENCY));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"RGB backlight", R.drawable.rgb_keycaps, "A different color for every IDE", 10000, CLICK_EFFICIENCY));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Doubleshot Keycaps", R.drawable.key_caps_2, "Stand the test of time", 50000, CLICK_EFFICIENCY));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"\"Coding\" chair", R.drawable.chair_1, "For 1337 coding", 150000, CLICK_EFFICIENCY));

        //COFFEE UPGRADES 0
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Flavor Crystals", R.drawable.vending_machine_retro, "It's morning in your cup!", 55, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Worlds Best Boss Mug", R.drawable.coffee_mug, "2 for $5", 750, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Non-Dairy Creamer", R.drawable.bottles, "As smooth as secretion", 2250, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Agave Syrup", R.drawable.plant, "\"Natural\" means you can put in twice as much", 4000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Triple Shot", R.drawable.monitor_refresh_60, "TIME TO WAKE UP", 11000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Organic Beans", R.drawable.coffee_mug_2, "Well-travelled", 34000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Espresso Machine", R.drawable.coffee_machine, "Sounds like a business-casual jackhammer", 98000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));

        //MONITOR UPGRADES 1
        upgradeList.add(new Upgrade((long) upgradeList.size(),"60hz Refresh Rate", R.drawable.monitor_refresh_60, "Can you really tell anything faster?", 500, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"16:9 Monitor", R.drawable.monitor_widescreen_16_to_9, "It wider", 5000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"120hz Refresh Rate", R.drawable.monitor_refresh_120, "You can really see the difference", 25000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"1080p Resolution", R.drawable.resolution_res_1080, "It's better than 720p", 100000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"21:9 Monitor", R.drawable.monitor_widescreen_21_to_9, "Don't strain your neck", 500000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Rotatable Screen", R.drawable.monitor_rotate, "For a change of perspective", 1500000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"4K Resolution", R.drawable.resolution_res_4k, "So many pixels!", 10000000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));

        //RAM UPGRADES 2
        upgradeList.add(new Upgrade((long) upgradeList.size(),"DDR3 800MHz", R.drawable.ddr3, "For procrastinating more concurrent tasks", 1280, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"DDR4 1600MHz", R.drawable.ddr4_ram, "A brighter DIMM", 10240, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"RGB Lights", R.drawable.rgb, "It's like a party in my motherboard -- and everyone's invited!", 80000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"DDR5 8400MHz ", R.drawable.ddr5, "The fins make it go faster", 150000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Water Cooling", R.drawable.water, "Glub glub", 400000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"DDR6 1200GHz", R.drawable.ddr6_ram, "For record-breaking browser tab counts", 1110000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Quantum DIMMs", R.drawable.quantum_ram, "Play dice with the universe", 88088888, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));

        //STORAGE UPGRADES 3
        upgradeList.add(new Upgrade((long) upgradeList.size(),"7200 RMP", R.drawable.hdd_7200_hard, "Spin to win", 50000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Flash Drive", R.drawable.flash_drive_2, "Easy to carry, easy to lose", 210000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"SSD", R.drawable.hard_disk_drive_solid_state_drive_ssd_hdd_3, "A bit cramped, but drop-resistant", 5000050, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"M.2 SSD", R.drawable.m2_ssd, "A lot roomier", 12000000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"NVMe SSD", R.drawable.nvme, "Efficient, reliable, wallet-sized", 41000000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Water-Cooled Drive", R.drawable.water_smile, "Stay cool", 125000000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));

        //SERVER UPGRADES 4
        upgradeList.add(new Upgrade((long) upgradeList.size(),"More Blinky Lights", R.drawable.water_smile, "Stay cool", 150000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Water Cooled", R.drawable.water_smile, "Stay cool", 500000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));

        //AI ASSISTANT UPGRADES 5
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Friendly Robot Pal", R.drawable.robot, "Stay cool", 1500000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Self Coding Power Gloves", R.drawable.haptic_power_glove, "Stay cool", 5000000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));

        //GLOBAL 1%
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Learn HTML", R.drawable.file_type_javascript_js, "Hello World Wide Web!", 5000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Learn CSS", R.drawable.file_type_javascript_js, "You know how to wrap something in a div", 15000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Desk Plant", R.drawable.cactus, "A good sounding board", 99000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Learn JavaScript", R.drawable.file_type_javascript_js, "You know how to print to the console", 150000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Learn XML", R.drawable.file_type_xml, "..Wait, it doesn't stand for \"Extreme?\"", 500000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Business Casual", R.drawable.polo_shirt, "No tie, no belt, no problem", 1000000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Learn SQL", R.drawable.file_type_sql, "You have excellent table manners", 5000000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Learn C++", R.drawable.file_type_c_plus_plus, "You know how to patch the memory leak you tore", 25000000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Casual dress", R.drawable.hoodie_2, "Comfy coding", 400000000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        upgradeList.add(new Upgrade((long) upgradeList.size(),"Learn Java", R.drawable.file_type_jar_java, "You're foreman of the AbstractFactoryFactory", 1000000000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));



    }
}
