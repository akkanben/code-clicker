package com.crudalchemy.codeclicker;

import static com.crudalchemy.codeclicker.models.UpgradeType.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;
import com.crudalchemy.codeclicker.models.UpgradeType;
import com.crudalchemy.codeclicker.utility.LargeNumbers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private double globalLineCount = 0;
    private double linePerSecond = 234230.0;
    private int perClick = 1;
    private double currentLineCount = 0;
    private int counter = 0;
    double partsOfASecond = 0.0;
    List<Generator> generatorList;
    List<Upgrade> upgradeList;
    GameLoop gameLoop;
    TextView tickerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        getSupportActionBar().hide();
        tickerTextView = findViewById(R.id.text_view_main_activity_counter);
        setupClick();

        // TESTING
        generatorList = new ArrayList<>();
        upgradeList = new ArrayList<>();
        hardCodedStoreItems();

        gameLoop = new GameLoop("game");
        gameLoop.start();
    }

    class GameLoop implements Runnable {
        private Thread thread;
        private String threadName;
        private volatile boolean running;

        public GameLoop(String threadName) {
            this.threadName = threadName;
            running = true;
        }

        @Override
        public void run() {
            try {
                while (running) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (partsOfASecond < 0.01) {
                                globalLineCount += linePerSecond;
                                currentLineCount += linePerSecond;
                                checkForVisibilityToggle();
                            }
                            double temp = currentLineCount + (linePerSecond * partsOfASecond);
                            tickerTextView.setText(LargeNumbers.convert(temp));
                        }
                    });
                    Thread.sleep(100);
                    partsOfASecond += 0.10;
                    if (partsOfASecond > 1)
                        partsOfASecond = 0.00001;
                }
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }

        public void start() {
            if (thread == null) {
                thread = new Thread(this, threadName);
                thread.start();
            }
        }
    }

    private void setupClick() {
        Button button = findViewById(R.id.button_main_activity_click);
        button.setOnClickListener(view -> {
            runOnUiThread(() -> {
                globalLineCount += perClick;
                currentLineCount += perClick;
            });
        });
    }

    private void hardCodedStoreItems() {
        generatorList.add(new Generator("Extra Monitor", "", "Extra Monitor Description", 4, 1.07, 1.67));
        generatorList.add(new Generator("Firewall", "", "Firewall Description", 60, 1.15, 20));
        generatorList.add(new Generator("Database", "", "Database Description", 720, 1.14, 90));
        generatorList.add(new Generator("Vending Machine", "", "Vending Machine Description", 8640, 1.13, 360));
        Upgrade higherRes = new Upgrade("Higher Resolution", "", "Higher Resolution Description", 1000, GENERATOR_EFFICIENCY);
        Upgrade moreBricks = new Upgrade("More Bricks", "", "More Bricks Description", 5000, GENERATOR_EFFICIENCY);
        Upgrade javascript = new Upgrade("JavaScript", "", "JavaScript Description", 7000, GLOBAL_LINE_PRODUCTION_MULTIPLIER);
        Upgrade newKeyCaps = new Upgrade("New Keycaps", "", "Keycaps Description", 750, CLICK_EFFICIENCY);
        higherRes.setGenerator(generatorList.get(0));
        moreBricks.setGenerator(generatorList.get(1));
        upgradeList.add(higherRes);
        upgradeList.add(moreBricks);
        upgradeList.add(javascript);
        upgradeList.add(newKeyCaps);
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
                perClick *= 2;
                break;
        }
        upgradeList.remove(upgrade);
    }

    private void checkForVisibilityToggle() {
        for (Generator generator : generatorList) {
            if (!generator.isVisible() && generator.getCost() <= globalLineCount)
                generator.setVisible(true);
        }
        for (Upgrade upgrade : upgradeList) {
            if (upgrade.getCost() <= globalLineCount)
                upgrade.setVisible(true);
        }
    }

}