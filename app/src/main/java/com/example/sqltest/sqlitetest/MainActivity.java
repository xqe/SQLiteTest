package com.example.sqltest.sqlitetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void add() {
        Log.e("MainActivity", "add: ");
        SQLiteManager.getInstance(getApplicationContext())
                .addDataToDB(new DataBean.Builder()
                        .name("xqe001")
                        .address("chongqing")
                        .age(26)
                        .build());
    }

    @OnClick(R.id.button4)
    public void query() {
        Log.e("MainActivity", "query: ");
        SQLiteManager.getInstance(getApplicationContext())
                .queryFromDBTest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
