package com.crudalchemy.codeclicker.activity;

import static com.crudalchemy.codeclicker.models.UpgradeType.GENERATOR_EFFICIENCY;
import static com.crudalchemy.codeclicker.utility.InitializeStoreItems.hardCodedStoreItems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.adapter.GeneratorMenuRecyclerViewAdapter;
import com.crudalchemy.codeclicker.adapter.UpgradeMenuRecyclerViewAdapter;
import com.crudalchemy.codeclicker.models.Generator;
import com.crudalchemy.codeclicker.models.Upgrade;
import com.crudalchemy.codeclicker.room.CodeClickerDatabase;
import com.crudalchemy.codeclicker.utility.LargeNumbers;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {
    Game game;
    GameLoop gameLoop;
    TextView tickerTextView;
    TextView linesPerSecondTextView;
    TextView snackbar;
    SoundPool soundPool;
    int[] soundEffectsArray;
    CodeClickerDatabase codeClickerDatabase;
    GeneratorMenuRecyclerViewAdapter generatorAdapter;
    UpgradeMenuRecyclerViewAdapter upgradeAdapter;

    ArrayList<String> codeTextStringList = new ArrayList<>();
    String currentCodeTextStr;
    String helloWorldCodeStr = "class Greeting{ \n\tpublic static void main(String args[]){\n\t\tSystem.out.println(\"Hello World!\");\n\t}\n}";
    String recursiveRemoveCodeStr = "rm -rf *";
    String infiniteOkayCodeStr = "while(true){\n\tSystem.out.println(\"EVERYTHING IS FINE\");\n}";
    int codeTextStrIndex = 0;
    int codeTextStringListIndex = 0;

    Animation scaleUpKeyboard, scaleDownKeyboard, scaleUpGeneratorButton, scaleDownGeneratorButton, scaleUpUpgradeButton, scaleDownUpgradeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        setupButtonAnimations();
        snackbar = findViewById(R.id.main_activity_text_view_snackbar);
        snackbar.setText("Game Saved");

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
        setupSounds();

        // setting up room database
        codeClickerDatabase = Room.databaseBuilder(
                getApplicationContext(),
                CodeClickerDatabase.class,
                "codeClicker")
                .allowMainThreadQueries() // TODO: remove this at some point.
                .fallbackToDestructiveMigration()
                .build();

        game = new Game();
        hardCodedStoreItems(game);
        setUpSaveLoad();
        gameLoop = new GameLoop("game");
        gameLoop.start();

        setupPopupGeneratorButton();
        setupPopupUpgradesButton();
    }

    class GameLoop implements Runnable {
        private Thread thread;
        private final String threadName;
        private final boolean running;

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
                                game.saveTimer++;
                            }
                            if (game.getSaveTimer() > 10) {
                                saveGame();
                                snackbar.setVisibility(View.VISIBLE);
                                game.setSaveTimer(0);
                            }
                            if (game.getSaveTimer() == 1)
                                snackbar.setVisibility(View.INVISIBLE);

                            game.updateItemLists(generatorAdapter, upgradeAdapter);
                            double temp = game.currentLineCount + (game.linePerSecond * game.partsOfASecond);
                            tickerTextView.setText(LargeNumbers.convert(temp));
                            linesPerSecondTextView.setText(LargeNumbers.convertWithDecimals(game.linePerSecond) + "/second ");
                        }
                    });

                    Thread.sleep(100);
                    game.partsOfASecond += 0.10;
                    if (game.partsOfASecond > 1)
                        game.partsOfASecond = 0.00001;
                }
            } catch (InterruptedException e) {
                System.out.println(e);
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
        ImageView keyboardButton = findViewById(R.id.button_main_activity_click);
        keyboardButton.setOnClickListener(view -> {
            runOnUiThread(() -> {
                playRandomKeyboardPressSound();
                game.lifetimeLineCount += game.linesPerClick;
                game.currentLineCount += game.linesPerClick;
                animateKeyPress();
            });
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupButtonAnimations()
    {
        ImageView keyboardButton = findViewById(R.id.button_main_activity_click);
        scaleUpKeyboard = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDownKeyboard = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        keyboardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    keyboardButton.startAnimation(scaleUpKeyboard);
                    setupClick();
                    runOnUiThread(() -> {
                        playRandomKeyboardPressSound();
                        game.lifetimeLineCount += game.linesPerClick;
                        game.currentLineCount += game.linesPerClick;
                        animateKeyPress();
                    });
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    keyboardButton.startAnimation(scaleDownKeyboard);
                }
                return true;
            }
        });
    }

    private void saveGame() {
        codeClickerDatabase.dao().clearGame();
        codeClickerDatabase.dao().clearGenerator();
        codeClickerDatabase.dao().clearUpgrade();
        codeClickerDatabase.dao().insertGame(game);
        for (Generator generator : game.getGeneratorList()) {
            codeClickerDatabase.dao().insertGenerator(generator);
        }
        for (Upgrade upgrade : game.getUpgradeList()) {
            codeClickerDatabase.dao().insertUpgrade(upgrade);
        }
    }

        private void setUpSaveLoad() {
        Button saveButton = findViewById(R.id.save);
        Button loadButton = findViewById(R.id.load);
        saveButton.setOnClickListener(view -> {
            saveGame();
        });
        loadButton.setOnClickListener(view -> {
            List<Generator> generatorList = codeClickerDatabase.dao().findAllGenerators();
            List<Upgrade> upgradeList = codeClickerDatabase.dao().findAllUpgrades();
            for(Upgrade upgrade : upgradeList)
            {
                if(upgrade.getType().equals(GENERATOR_EFFICIENCY))
                {
                    String generatorName = upgrade.getGeneratorName();
                    List<Generator> generators = generatorList.stream().filter(element -> element.getName().equals(generatorName)).collect(Collectors.toList());
                    upgrade.setGenerator(generators.get(0));
                }
            }
            game = codeClickerDatabase.dao().find();
            game.setGeneratorList(generatorList);
            game.setUpgradeList(upgradeList);
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
        TextView typedCodeTextView = findViewById(R.id.main_typed_text_text_view);
        typedCodeTextView.setText(codeStrSubstr);
        codeTextStrIndex++;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setupPopupGeneratorButton()
    {
        Button button = findViewById(R.id.button_main_activity_generators);
        scaleUpGeneratorButton = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDownGeneratorButton = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    button.startAnimation(scaleUpGeneratorButton);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    showPopupGeneratorDialogBox();
                    button.startAnimation(scaleDownGeneratorButton);
                }
                return true;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setupPopupUpgradesButton()
    {
        Button button = findViewById(R.id.button_main_activity_upgrades);
        scaleUpUpgradeButton = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDownUpgradeButton = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    button.startAnimation(scaleUpUpgradeButton);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    showPopupUpgradesDialogBox();
                    button.startAnimation(scaleDownUpgradeButton);
                }
                return true;
            }
        });
    }

    public void showPopupGeneratorDialogBox()
    {
        int bgStreamId = soundPool.play(soundEffectsArray[5],0.50f,0.50f,1,-1,1);
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_generator);
        RecyclerView rv = dialog.findViewById(R.id.popup_generator_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        generatorAdapter = new GeneratorMenuRecyclerViewAdapter(game, this);
        rv.setAdapter(generatorAdapter);
        dialog.setOnDismissListener(d -> {
            soundPool.setVolume(bgStreamId, 0,0);
        });
        dialog.show();
    }

    public void showPopupUpgradesDialogBox()
    {
        int bgStreamId = soundPool.play(soundEffectsArray[5],0.50f,0.50f,1,-1,1);
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_upgrades);
        RecyclerView rv = dialog.findViewById(R.id.popup_upgrades_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        upgradeAdapter = new UpgradeMenuRecyclerViewAdapter(game, this);
        rv.setAdapter(upgradeAdapter);

        dialog.setOnDismissListener(d -> {
            soundPool.setVolume(bgStreamId, 0,0);
        });
        dialog.show();
    }

    public void playRandomKeyboardPressSound()
    {
        Random rand = new Random();
        soundPool.play(soundEffectsArray[rand.nextInt(5)], (float) 0.65, (float) 0.65,1,0, 1);
    }

    private void setupSounds() {
        AudioAttributes audioAttributes = new AudioAttributes
                .Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool
                .Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
        int keyA = soundPool.load(this, R.raw.new_key01, 1);
        int keyB = soundPool.load(this, R.raw.new_key02, 1);
        int keyC = soundPool.load(this, R.raw.new_key03, 1);
        int keyD = soundPool.load(this, R.raw.new_key04, 1);
        int keyE = soundPool.load(this, R.raw.new_key05, 1);
        int bgSong = soundPool.load(this,R.raw.electronicsoul,1);
        soundEffectsArray = new int[]{keyA, keyB, keyC, keyD, keyE, bgSong};


    }
}