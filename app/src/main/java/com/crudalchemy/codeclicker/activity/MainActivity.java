package com.crudalchemy.codeclicker.activity;

import static com.crudalchemy.codeclicker.utility.InitializeStoreItems.hardCodedStoreItems;
import static com.crudalchemy.codeclicker.utility.SaveIO.readFromFile;
import static com.crudalchemy.codeclicker.utility.SaveIO.writeToFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.adapter.GeneratorMenuRecyclerViewAdapter;
import com.crudalchemy.codeclicker.adapter.UpgradeMenuRecyclerViewAdapter;
import com.crudalchemy.codeclicker.utility.LargeNumbers;

import java.io.FileNotFoundException;


public class MainActivity extends AppCompatActivity {
    Game game;
    GameLoop gameLoop;
    TextView tickerTextView;
    TextView linesPerSecondTextView;
    SoundPool soundPool;
    int keyA, keyB, keyC;
    GeneratorMenuRecyclerViewAdapter generatorMenuRecyclerViewAdapter;
    UpgradeMenuRecyclerViewAdapter upgradeMenuRecyclerViewAdapter;

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

        AudioAttributes audioAttributes = new AudioAttributes
                .Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool
                .Builder()
                .setMaxStreams(3)
                .setAudioAttributes(audioAttributes)
                .build();

        keyA = soundPool.load(this, R.raw.key2, 1);
        keyB= soundPool.load(this, R.raw.key2, 1);
        keyC = soundPool.load(this, R.raw.key2, 1);


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
                                if (upgradeMenuRecyclerViewAdapter != null) {
                                    upgradeMenuRecyclerViewAdapter.notifyDataSetChanged();
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
                soundPool.play(keyA, 1,1,1,0,1);
                game.lifetimeLineCount += game.linesPerClick;
                game.currentLineCount += game.linesPerClick;
            });

        });
    }


    private void setupGeneratorButton()
    {
        Button generatorButton = findViewById(R.id.button_main_activity_generators);
        Button upgradeButton = findViewById(R.id.button_main_activity_upgrades);
        Button enterButton = findViewById(R.id.button_main_activity_click);
        RecyclerView upgradeItemListRecyclerView = (RecyclerView) findViewById(R.id.upgrade_menu_list_recycler_view_upgrades);
        RecyclerView generatorItemListRecyclerView = (RecyclerView) findViewById(R.id.upgrade_menu_list_recycler_view_generators);

        generatorButton.setOnClickListener(view ->
        {
            if (generatorItemListRecyclerView.getVisibility() == View.VISIBLE) {
                enterButton.setVisibility(View.VISIBLE);
                generatorItemListRecyclerView.setVisibility(View.INVISIBLE);
                upgradeItemListRecyclerView.setVisibility(View.INVISIBLE);
            }
            else {
                enterButton.setVisibility(View.INVISIBLE);
                generatorItemListRecyclerView.setVisibility(View.VISIBLE);
                upgradeItemListRecyclerView.setVisibility(View.INVISIBLE);
            }
        });

        upgradeButton.setOnClickListener(view -> {
            if (upgradeItemListRecyclerView.getVisibility() == View.VISIBLE) {
                enterButton.setVisibility(View.VISIBLE);
                upgradeItemListRecyclerView.setVisibility(View.INVISIBLE);
                generatorItemListRecyclerView.setVisibility(View.INVISIBLE);
            }
            else {
                enterButton.setVisibility(View.INVISIBLE);
                upgradeItemListRecyclerView.setVisibility(View.VISIBLE);
                generatorItemListRecyclerView.setVisibility(View.INVISIBLE);
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
        //UPGRADES
        RecyclerView upgradeItemListRecyclerView = (RecyclerView) findViewById(R.id.upgrade_menu_list_recycler_view_upgrades);
        RecyclerView.LayoutManager upgradeLayoutManager = new LinearLayoutManager(this);
        upgradeItemListRecyclerView.setLayoutManager(upgradeLayoutManager);
        upgradeMenuRecyclerViewAdapter = new UpgradeMenuRecyclerViewAdapter(game, this);
        upgradeItemListRecyclerView.setAdapter(upgradeMenuRecyclerViewAdapter);

        //GENERATORS
        RecyclerView generatorItemListRecyclerView = (RecyclerView) findViewById(R.id.upgrade_menu_list_recycler_view_generators);
        RecyclerView.LayoutManager generatorLayoutManager = new LinearLayoutManager(this);
        generatorItemListRecyclerView.setLayoutManager(generatorLayoutManager);
        generatorMenuRecyclerViewAdapter = new GeneratorMenuRecyclerViewAdapter(game, this);
        generatorItemListRecyclerView.setAdapter(generatorMenuRecyclerViewAdapter);
    }

    public void playKeyboardPressSound(Context context)
    {

    }

}