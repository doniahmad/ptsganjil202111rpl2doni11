package com.example.ptsganjil202111rpl2doni11.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ptsganjil202111rpl2doni11.menu.FavoriteMenu;
import com.example.ptsganjil202111rpl2doni11.menu.ListMenu;
import com.example.ptsganjil202111rpl2doni11.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button favmenu_btn, listmenu_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favmenu_btn = findViewById(R.id.favmenu_btn);
        listmenu_btn = findViewById(R.id.listmenu_btn);

        favmenu_btn.setOnClickListener(this);
        listmenu_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.listmenu_btn) {
            Intent intent = new Intent(this, ListMenu.class);
            startActivity(intent);
        } else if (view.getId() == R.id.favmenu_btn) {
            Intent intent = new Intent(this, FavoriteMenu.class);
            startActivity(intent);
        }
    }
}