package com.crudalchemy.codeclicker.activity;

import static com.crudalchemy.codeclicker.utility.InitializeStoreItems.hardCodedStoreItems;
import static com.crudalchemy.codeclicker.utility.SaveIO.readFromFile;
import static com.crudalchemy.codeclicker.utility.SaveIO.writeToFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioAttributes;
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
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Game game;
    GameLoop gameLoop;
    TextView tickerTextView;
    TextView linesPerSecondTextView;
    SoundPool soundPool;
    int[] keyPressesArray;
    GeneratorMenuRecyclerViewAdapter generatorMenuRecyclerViewAdapter;
    UpgradeMenuRecyclerViewAdapter upgradeMenuRecyclerViewAdapter;

    ArrayList<String> codeTextStringList = new ArrayList<>();
    String currentCodeTextStr;
    String helloWorldCodeStr = "class Greeting{ \n\tpublic static void main(String args[]){\n\t\tSystem.out.println(\"Hello World!\");\n\t}\n}";
    String recursiveRemoveCodeStr = "rm -rf *";
    String infiniteOkayCodeStr = "while(true){\n\tSystem.out.println(\"EVERYTHING IS FINE\");\n}";
    int codeTextStrIndex = 0;
    int codeTextStringListIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        codeTextStringList.add(helloWorldCodeStr);
        codeTextStringList.add(recursiveRemoveCodeStr);
        codeTextStringList.add(infiniteOkayCodeStr);
        /*codeTextStringList.add();
        codeTextStringList.add();
        codeTextStringList.add();
        codeTextStringList.add();
        codeTextStringList.add();*/

        currentCodeTextStr = codeTextStringList.get(0);

        getSupportActionBar().hide();
        tickerTextView = findViewById(R.id.text_view_main_activity_counter);
        linesPerSecondTextView = findViewById(R.id.text_view_main_activity_lines_per_second);
        setupClick();
        setupKeyboardSounds();
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
                playRandomKeyboardPressSound();
                game.lifetimeLineCount += game.linesPerClick;
                game.currentLineCount += game.linesPerClick;
                animateKeyPress();
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

    private void animateKeyPress(){

        if(codeTextStrIndex >= currentCodeTextStr.length()){
            codeTextStrIndex = 0;
            if(codeTextStringListIndex >= codeTextStringList.size() - 1){
                currentCodeTextStr = codeTextStringList.get(0);
            } else {
                currentCodeTextStr = codeTextStringList.get(codeTextStringListIndex + 1);
                codeTextStringListIndex++;
            }

        }
        String cursor = "█";
        String codeStrSubstr = currentCodeTextStr.substring(0, codeTextStrIndex) + cursor;
        TextView typedCodeTextView = (TextView) findViewById(R.id.main_typed_text_text_view);
        typedCodeTextView.setText(codeStrSubstr);

        codeTextStrIndex++;
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

    public void playRandomKeyboardPressSound() {
        Random rand = new Random();
        soundPool.play(keyPressesArray[rand.nextInt(5)], (float) 0.65, (float) 0.65,1,0, 1);
    }

    private void setupKeyboardSounds() {
        AudioAttributes audioAttributes = new AudioAttributes
                .Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool
                .Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();
        int keyA = soundPool.load(this, R.raw.new_key01, 1);
        int keyB = soundPool.load(this, R.raw.new_key02, 1);
        int keyC = soundPool.load(this, R.raw.new_key03, 1);
        int keyD = soundPool.load(this, R.raw.new_key04, 1);
        int keyE = soundPool.load(this, R.raw.new_key05, 1);
        keyPressesArray = new int[]{keyA, keyB, keyC, keyD, keyE};
        // Workaround for laggy sound
        soundPool.play(keyPressesArray[0], 0, 0, 1, -1, 1f);
    }

}