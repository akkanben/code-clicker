package com.crudalchemy.codeclicker.activity;

import static com.crudalchemy.codeclicker.utility.InitializeStoreItems.hardCodedStoreItems;
import static com.crudalchemy.codeclicker.utility.SaveIO.readFromFile;
import static com.crudalchemy.codeclicker.utility.SaveIO.writeToFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.adapter.UpgradeMenuRecyclerViewAdapter;
import com.crudalchemy.codeclicker.utility.LargeNumbers;

import java.io.FileNotFoundException;


public class MainActivity extends AppCompatActivity {
    Game game;
    GameLoop gameLoop;
    TextView tickerTextView;
    UpgradeMenuRecyclerViewAdapter upgradeMenuRecyclerViewAdapter;

    String helloWorldCodeStr = "class Greeting{ \n   public static void main(String args[]){";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        getSupportActionBar().hide();
        tickerTextView = findViewById(R.id.text_view_main_activity_counter);
        setupClick();
        game = new Game();

        hardCodedStoreItems(game);
       /* writeToFile(game, this);
        try {
            game = readFromFile(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
       }*/
        setUpSaveLoad();

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
                            if (game.partsOfASecond < 0.01) {
                                game.lifetimeLineCount += game.linePerSecond;
                                game.currentLineCount += game.linePerSecond;
                                game.checkForVisibilityToggle();
                            }
                            double temp = game.currentLineCount + (game.linePerSecond * game.partsOfASecond);
                            tickerTextView.setText(LargeNumbers.convert(temp));
                        }
                    });
                    Thread.sleep(100);
                    game.partsOfASecond += 0.10;
                    if (game.partsOfASecond > 1)
                        game.partsOfASecond = 0.00001;
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
                game.lifetimeLineCount += game.linesPerClick;
                game.currentLineCount += game.linesPerClick;
            });
        });
    }


    private void setupUpgradeButton()
    {
        Button button = findViewById(R.id.button_main_activity_upgrades);
        button.setOnClickListener(view ->
        {
           setupUpgradeItemRecyclerView();
        });
    }

    private void setUpSaveLoad()
    {
        Button saveButton = findViewById(R.id.save);
        Button loadButton = findViewById(R.id.load);

       saveButton.setOnClickListener(view -> {
           writeToFile(game, MainActivity.this);
       });

        loadButton.setOnClickListener(view -> {
            try {
                game = readFromFile(MainActivity.this);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void setupUpgradeItemRecyclerView()
    {
        RecyclerView upgradeItemListRecyclerView = (RecyclerView) findViewById(R.id.upgrade_menu_list_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        upgradeItemListRecyclerView.setLayoutManager(layoutManager);

        upgradeMenuRecyclerViewAdapter = new UpgradeMenuRecyclerViewAdapter(game.generatorList, this);
        upgradeItemListRecyclerView.setAdapter(upgradeMenuRecyclerViewAdapter);
    }

}