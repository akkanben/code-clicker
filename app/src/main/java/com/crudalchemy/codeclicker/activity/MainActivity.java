package com.crudalchemy.codeclicker.activity;

import static com.crudalchemy.codeclicker.utility.InitializeStoreItems.hardCodedStoreItems;
import static com.crudalchemy.codeclicker.utility.SaveIO.readFromFile;
import static com.crudalchemy.codeclicker.utility.SaveIO.writeToFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.adapter.GeneratorMenuRecyclerViewAdapter;
import com.crudalchemy.codeclicker.utility.LargeNumbers;

import java.io.FileNotFoundException;


public class MainActivity extends AppCompatActivity {
    Game game;
    GameLoop gameLoop;
    TextView tickerTextView;
    TextView linesPerSecondTextView;
    GeneratorMenuRecyclerViewAdapter generatorMenuRecyclerViewAdapter;

    String helloWorldCodeStr = "class Greeting{ \n   public static void main(String args[]){";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        getSupportActionBar().hide();
        tickerTextView = findViewById(R.id.text_view_main_activity_counter);
        linesPerSecondTextView = findViewById(R.id.text_view_main_activity_lines_per_second);
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
        setupGeneratorButton();
        setupUpgradeItemRecyclerView();
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
                                if (generatorMenuRecyclerViewAdapter != null) {
                                    generatorMenuRecyclerViewAdapter.notifyDataSetChanged();
                                }
                            }
                            double temp = game.currentLineCount + (game.linePerSecond * game.partsOfASecond);
                            tickerTextView.setText(LargeNumbers.convert(temp));
                            linesPerSecondTextView.setText(Double.toString(game.linePerSecond) + " lines/second");
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


    private void setupGeneratorButton()
    {
        Button button = findViewById(R.id.button_main_activity_generators);
        button.setOnClickListener(view ->
        {
            RecyclerView upgradeItemListRecyclerView = (RecyclerView) findViewById(R.id.upgrade_menu_list_recycler_view);
            Button enterButton = findViewById(R.id.button_main_activity_click);
            if (upgradeItemListRecyclerView.getVisibility() == View.VISIBLE) {
                enterButton.setVisibility(View.VISIBLE);
                upgradeItemListRecyclerView.setVisibility(View.INVISIBLE);
            }
            else {
                enterButton.setVisibility(View.INVISIBLE);
                upgradeItemListRecyclerView.setVisibility(View.VISIBLE);
            }
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
        generatorMenuRecyclerViewAdapter = new GeneratorMenuRecyclerViewAdapter(game, this);
        upgradeItemListRecyclerView.setAdapter(generatorMenuRecyclerViewAdapter);
    }

}