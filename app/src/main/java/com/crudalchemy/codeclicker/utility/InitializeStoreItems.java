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

        // GENERATORS

        game.getGeneratorList().add(new Generator("Coffee", R.drawable.coffee_mug,"A productivity boost", 15, 1.10, 1));
        game.getGeneratorList().add(new Generator("Monitor", R.drawable.monitor_1,"You can always use an extra monitor", 300, 1.15, 5));
        game.getGeneratorList().add(new Generator("RAM", R.drawable.ram, "More Stack Overflow tabs at once", 720, 1.14, 15));
        game.getGeneratorList().add(new Generator("Storage", R.drawable.hard_drive_hdd, "Hard drive is full -- time for a new hard drive", 8640, 1.13, 360));
        game.getGeneratorList().add(new Generator("Server", R.drawable.server_rack,"Stack em' up like pancakes", 103680, 1.12, 2160));
        game.getGeneratorList().add(new Generator("AI Assistant", R.drawable.robot_binary,"\"NULL POINTER AT: FIRST DIRECTIVE\"", 803680, 1.11, 25160));

        // KEY PRESS UPGRADES
        game.getUpgradeList().add(new Upgrade("Mechanical Keyboard", R.drawable.keycaps, "If it clacks like a duck...", 75, CLICK_EFFICIENCY));
        game.getUpgradeList().add(new Upgrade("Lumbar Support", R.drawable.chair_1, "Code with your knees, not your back", 75, CLICK_EFFICIENCY));
        game.getUpgradeList().add(new Upgrade("ABS Keys", R.drawable.key_caps_3, "Shiny!", 200, CLICK_EFFICIENCY));
        game.getUpgradeList().add(new Upgrade("Head rest", R.drawable.chair_2, "Like a pillow", 75, CLICK_EFFICIENCY));
        game.getUpgradeList().add(new Upgrade("RGB backlight", R.drawable.keyboard_color_rgb, "A different color for every IDE", 500, CLICK_EFFICIENCY));
        game.getUpgradeList().add(new Upgrade("Doubleshot Keycaps", R.drawable.key_caps_2, "Stand the test of time", 1000, CLICK_EFFICIENCY));
        game.getUpgradeList().add(new Upgrade("\"Coding\" chair", R.drawable.chair_3, "For 1337 coding", 1500, CLICK_EFFICIENCY));

        //COFFEE UPGRADES 0
        game.getUpgradeList().add(new Upgrade("Flavor Crystals", R.drawable.vending_machine_retro, "It's morning in your cup!", 55, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        game.getUpgradeList().add(new Upgrade("Worlds Best Boss Mug", R.drawable.coffee_mug, "2 for $5", 750, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        game.getUpgradeList().add(new Upgrade("Organic Beans", R.drawable.coffee_mug_2, "Well-travelled", 1250, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        game.getUpgradeList().add(new Upgrade("Espresso Machine", R.drawable.coffee_machine, "Sounds like a business-casual jackhammer", 4125, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        game.getUpgradeList().add(new Upgrade("Triple Shot", R.drawable.monitor_refresh_60, "TIME TO WAKE UP", 1235, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        game.getUpgradeList().add(new Upgrade("Non-Dairy Creamer", R.drawable.bottles, "As smooth as secretion", 750, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));
        game.getUpgradeList().add(new Upgrade("Agave Syrup", R.drawable.plant, "\"Natural\" means you can put in twice as much", 750, GENERATOR_EFFICIENCY, game.getGeneratorList().get(0)));

        //MONITOR UPGRADES 1
        game.getUpgradeList().add(new Upgrade("60hz Refresh Rate", R.drawable.monitor_refresh_60, "Can you really tell anything faster?", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        game.getUpgradeList().add(new Upgrade("16:9 Monitor", R.drawable.monitor_widescreen_16_to_9, "It wider", 500, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        game.getUpgradeList().add(new Upgrade("120hz Refresh Rate", R.drawable.monitor_refresh_120, "You can really see the difference", 2500, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        game.getUpgradeList().add(new Upgrade("1080p Resolution", R.drawable.resolution_res_1080, "It's better than 720p", 10000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        game.getUpgradeList().add(new Upgrade("21:9 Monitor", R.drawable.monitor_widescreen_21_to_9, "Don't strain your neck", 50000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        game.getUpgradeList().add(new Upgrade("Rotatable Screen", R.drawable.monitor_rotate, "For a change of perspective", 150000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));
        game.getUpgradeList().add(new Upgrade("4K Resolution", R.drawable.resolution_res_4k, "So many pixels!", 1000000, GENERATOR_EFFICIENCY, game.getGeneratorList().get(1)));

        //RAM UPGRADES 2
        game.getUpgradeList().add(new Upgrade("DDR3 800MHz", R.drawable.ddr3, "Perfect for coding!", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        game.getUpgradeList().add(new Upgrade("DDR4 1600MHz", R.drawable.ddr4_ram, "Perfect for coding!", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        game.getUpgradeList().add(new Upgrade("RGB Lights", R.drawable.rgb, "It's like a party in my motherboard -- and everyone's invited!", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        game.getUpgradeList().add(new Upgrade("DDR5 8400MHz ", R.drawable.ddr5, "The fins make it go faster", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        game.getUpgradeList().add(new Upgrade("Water Cooling", R.drawable.water, "Glub glub", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        game.getUpgradeList().add(new Upgrade("DDR6 1200GHz", R.drawable.ddr6_ram, "Perfect for coding!", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));
        game.getUpgradeList().add(new Upgrade("Quantum DIMMs", R.drawable.quantum_ram, "Play dice with the universe", 8888888, GENERATOR_EFFICIENCY, game.getGeneratorList().get(2)));

        //STORAGE UPGRADES 3
        game.getUpgradeList().add(new Upgrade("7200 RMP", R.drawable.hdd_7200_hard, "Spin to win", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        game.getUpgradeList().add(new Upgrade("Flash Drive", R.drawable.flash_drive_2, "Easy to carry, easy to lose", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        game.getUpgradeList().add(new Upgrade("SSD", R.drawable.hard_disk_drive_solid_state_drive_ssd_hdd_3, "A bit cramped, but sleek, quiet, and drop-resistant", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        game.getUpgradeList().add(new Upgrade("M.2 SSD", R.drawable.m2_ssd, "A little roomier", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        game.getUpgradeList().add(new Upgrade("NVMe SSD", R.drawable.nvme, "Efficient, reliable, wallet-sized", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));
        game.getUpgradeList().add(new Upgrade("Water-Cooled Drive", R.drawable.water_smile, "Stay cool", 50, GENERATOR_EFFICIENCY, game.getGeneratorList().get(3)));

        //SERVER UPGRADES 4

        //AI ASSISTANT UPGRADES 5

        //GLOBAL 1%
        game.getUpgradeList().add(new Upgrade("Learn HTML", R.drawable.file_type_javascript_js, "Hello World Wide Web!", 1000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Learn CSS", R.drawable.file_type_javascript_js, "You know how to throw something into a div", 1000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Learn XML", R.drawable.file_type_xml, "..Wait, it doesn't stand for \"Extreme?\"", 25000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Learn JavaScript", R.drawable.file_type_javascript_js, "You know how to print to the console", 1000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Business Casual", R.drawable.polo_shirt, "No tie, no belt, no problem", 100000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Learn SQL", R.drawable.file_type_sql, "You have excellent table manners", 10000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Learn C++", R.drawable.file_type_c_plus_plus, "You know how to patch the memory leak you tore", 5000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Casual dress", R.drawable.hoodie_2, "Comfy coding", 100000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Desk Plant", R.drawable.cactus, "A good sounding board", 100000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));
        game.getUpgradeList().add(new Upgrade("Learn Java", R.drawable.file_type_jar_java, "You're foreman of the AbstractFactoryFactory", 100000, GLOBAL_LINE_PRODUCTION_MULTIPLIER));

    }
}
