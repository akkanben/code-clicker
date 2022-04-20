package com.crudalchemy.codeclicker.activity;

import static com.crudalchemy.codeclicker.utility.SaveIO.readFromFile;
import static com.crudalchemy.codeclicker.utility.SaveIO.writeToFile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.utility.LargeNumbers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static Game game;
    GameLoop gameLoop;
    TextView tickerTextView;

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
        setupClick();

        game = new Game();
//        hardCodedStoreItems(game);
//        writeToFile(game, this);
//        try {
//            game = readFromFile(this);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
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
                animateKeyPress();
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

}