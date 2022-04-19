package com.crudalchemy.codeclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.crudalchemy.codeclicker.activity.UpgradeMenuActivity;
import com.crudalchemy.codeclicker.utility.LargeNumbers;

public class MainActivity extends AppCompatActivity {

    private double linePerSecond = 234230.0;
    private int perClick = 1;
    private double currentLineCount = 0;
    private int counter = 0;
    double partsOfASecond = 0.0;
    GameLoop gameLoop;
    TextView tickerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        tickerTextView = findViewById(R.id.text_view_main_activity_counter);
        setupClick();
        setupUpgradeButton();
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
                                currentLineCount += linePerSecond;
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
                currentLineCount += perClick;
            });
        });
    }

    private void setupUpgradeButton()
    {
        Button button = findViewById(R.id.button_main_activity_upgrades);
        button.setOnClickListener(view ->
        {
            Intent goToUpgrades = new Intent(MainActivity.this, UpgradeMenuActivity.class);
            startActivity(goToUpgrades);
        });
    }

}