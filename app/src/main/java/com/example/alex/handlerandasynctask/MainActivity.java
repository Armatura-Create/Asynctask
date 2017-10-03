package com.example.alex.handlerandasynctask;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ImageView IvImageOne;
    private ImageView IvImageTwo;
    private ImageView IvImageThree;
    private ImageView IvImageFour;
    private ImageView IvImageFive;


    private Button mBtAsync;
    private Button mBtThread;

    private ProgressBar prBar;
    private Handler mHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFindId();
        mHandler = new Handler();

        setOnClickListners();

    }

    private void setOnClickListners() {
        mBtAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBtThread.setEnabled(false);

                MyTask myTask_1 = new MyTask();
                MyTask myTask_2 = new MyTask();
                MyTask myTask_3 = new MyTask();
                MyTask myTask_4 = new MyTask();
                MyTask myTask_5 = new MyTask();

                myTask_1.execute(1);
                myTask_2.execute(2);
                myTask_3.execute(3);
                myTask_4.execute(4);
                myTask_5.execute(5);

                mBtThread.setEnabled(true);

            }
        });

        mBtThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtAsync.setEnabled(false);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        IvImageOne.setImageResource(R.drawable.weapons_1);
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        IvImageTwo.setImageResource(R.drawable.weapons_2);
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        IvImageThree.setImageResource(R.drawable.weapons_3);
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        IvImageFour.setImageResource(R.drawable.weapons_4);
                    }
                });

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        IvImageFive.setImageResource(R.drawable.weapons_5);
                    }
                });

                mBtAsync.setEnabled(true);

            }
        });
    }

    private void setFindId() {
        IvImageOne = (ImageView) findViewById(R.id.iv_image_one);
        IvImageTwo = (ImageView) findViewById(R.id.iv_image_two);
        IvImageThree = (ImageView) findViewById(R.id.iv_image_three);
        IvImageFour = (ImageView) findViewById(R.id.iv_image_four);
        IvImageFive = (ImageView) findViewById(R.id.iv_image_five);
        prBar = (ProgressBar) findViewById(R.id.prBar_load);

        prBar.setVisibility(View.INVISIBLE);

        mBtAsync = (Button) findViewById(R.id.bt_asynctask);
        mBtThread = (Button) findViewById(R.id.bt_thread);
    }

    public void setImageToImageView(int number) {
        switch (number) {
            case 1:
                IvImageFive.setImageResource(R.drawable.weapons_1);
                break;

            case 2:
                IvImageFour.setImageResource(R.drawable.weapons_2);
                break;

            case 3:
                IvImageThree.setImageResource(R.drawable.weapons_3);
                break;

            case 4:
                IvImageTwo.setImageResource(R.drawable.weapons_4);
                break;

            case 5:
                IvImageOne.setImageResource(R.drawable.weapons_5);
                break;

        }
    }

    private class MyTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            prBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);

            setImageToImageView(aVoid);

            if (prBar.getVisibility() == View.INVISIBLE)
                prBar.setVisibility(View.VISIBLE);
            else
                prBar.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            prBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Integer doInBackground(Integer... voids) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return voids[0];
        }
    }
}