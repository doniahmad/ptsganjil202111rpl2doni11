package com.example.ptsganjil202111rpl2doni11.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptsganjil202111rpl2doni11.R;
import com.example.ptsganjil202111rpl2doni11.RealmHelper;
import com.example.ptsganjil202111rpl2doni11.model.DatabaseModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    MaterialToolbar topappbar;
    Realm realm;
    RealmHelper realmHelper;
    DatabaseModel dbModel;
    String name,desc,badge,stadiumImg,country;
    int id;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        realm.init(this);
        realm = Realm.getDefaultInstance();

        TextView tv_Name, tv_Country, tv_Desc;
        ImageView img_stadiumImg, img_Badge;

        topappbar = findViewById(R.id.topAppBar);
        setSupportActionBar(topappbar);

        tv_Name = findViewById(R.id.tv_Name_desc);
        tv_Country = findViewById(R.id.tv_country_desc);
        tv_Desc = findViewById(R.id.tv_descrip_desc);
        img_stadiumImg = findViewById(R.id.img_cover);
        img_Badge = findViewById(R.id.badge_img);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
            name = bundle.getString("name");
            desc = bundle.getString("desc");
            badge = bundle.getString("badge");
            stadiumImg = bundle.getString("stadiumImg");
            country = bundle.getString("country");

            topappbar.setTitle(name);
            tv_Name.setText(name);
            tv_Country.setText(country);
            tv_Desc.setText(desc);
            Picasso.get().load(badge)
                    .resize(400, 400)
                    .into(img_Badge);
            Picasso.get()
                    .load(stadiumImg)
                    .fit()
                    .centerCrop()
                    .into(img_stadiumImg);
        }
        topappbar.setOnMenuItemClickListener(this);
        topappbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_app_bar, menu);
        onFavorite(menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.favorite){
            dbModel = new DatabaseModel();
            DatabaseModel databaseModel = realm.where(DatabaseModel.class).equalTo("id",id).findFirst();
            realmHelper = new RealmHelper(realm);

            dbModel.setId(id);
            dbModel.setTeamName(name);
            dbModel.setTeamCountry(country);
            dbModel.setTeamDesc(desc);
            dbModel.setTeamBadge(badge);
            dbModel.setTeamStadiumImg(stadiumImg);

            if (databaseModel != null){
               realmHelper.delete(dbModel.getId());
               Toast.makeText(this,"Data Deleted from Favorite",Toast.LENGTH_SHORT).show();
               item.setIcon(R.drawable.ic_baseline_favorite_24);
            } else {
               realmHelper.save(dbModel);
                item.setIcon(R.drawable.ic_baseline_favorite_24_red);
               Toast.makeText(this,"Data Added into Favorite",Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    public void onFavorite(Menu menu) {
        MenuItem favorite = menu.findItem(R.id.favorite);
        DatabaseModel databaseModel = realm.where(DatabaseModel.class).equalTo("id",id).findFirst();
        if (databaseModel != null){
            favorite.setIcon(R.drawable.ic_baseline_favorite_24_red);
        } else {
            favorite.setIcon(R.drawable.ic_baseline_favorite_24);
        }
    }
}

