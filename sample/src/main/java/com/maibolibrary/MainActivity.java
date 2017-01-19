package com.maibolibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.generallibrary.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list = new ArrayList<>();
        list.add("dddddddd");
        list.add("jajajaja");
        list.add("jkdsl");
        Logger.i(1, "dfsdf", 345, 345, 665, 787);
        Logger.i(21);
        Logger.i(1, "list", list);
        Logger.i(1, "hahahaah", 321111, list, list);
        setContentView(R.layout.activity_main);
    }
}
