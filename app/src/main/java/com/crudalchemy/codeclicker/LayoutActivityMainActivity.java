package com.crudalchemy.codeclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LayoutActivityMainActivity extends AppCompatActivity {

    private int linePerSecond = 1;
    private int perClick = 1;
    private int currentLineCount = 0;
    private int counter = 0;
    GameLoop gameLoop;
    TextView tickerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        tickerTextView = findViewById(R.id.text_view_main_activity_counter);
        setupClick();
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
                            counter++;
                            if (counter % 100 == 0)
                                tickerTextView.setText(Integer.toString(currentLineCount++));
                        }
                    });
                    Thread.sleep(10);
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
            currentLineCount += perClick;
            tickerTextView.setText(Integer.toString(currentLineCount));
        });
    }


}