package com.example.ptsganjil202111rpl2doni11.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsganjil202111rpl2doni11.activity.DetailActivity;
import com.example.ptsganjil202111rpl2doni11.R;
import com.example.ptsganjil202111rpl2doni11.RealmHelper;
import com.example.ptsganjil202111rpl2doni11.model.DatabaseModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ListViewHolder> {

    private List<DatabaseModel> dataList;
    private Context mContext;
    RealmHelper realmHelper;
    Realm realm;

    public DatabaseAdapter(Context mContext, List<DatabaseModel> dataList, RealmHelper realmHelper, Realm realm) {
        this.mContext = mContext;
        this.dataList = dataList;
        this.realmHelper = realmHelper;
        this.realm = realm;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        final DatabaseModel model = dataList.get(position);

        holder.tv_name.setText(model.getTeamName());
        holder.tv_country.setText(model.getTeamCountry());
        Picasso.get()
                .load(dataList.get(position).getTeamBadge())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.img_list);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("badge", model.getTeamBadge());
                intent.putExtra("name", model.getTeamName());
                intent.putExtra("stadiumImg", model.getTeamStadiumImg());
                intent.putExtra("country", model.getTeamCountry());
                intent.putExtra("desc", model.getTeamDesc());
                intent.putExtra("id", model.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_country;
        private ImageView img_list;

        public ListViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_title_list);
            tv_country = itemView.findViewById(R.id.tv_country_list);
            img_list = itemView.findViewById(R.id.img_list);
        }
    }
}
