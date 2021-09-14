package com.example.ptsganjil202111rpl2doni11;

import com.example.ptsganjil202111rpl2doni11.model.DatabaseModel;
import com.example.ptsganjil202111rpl2doni11.model.TeamModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final DatabaseModel dbModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DatabaseModel model = realm.copyToRealm(dbModel);
            }
        });
    }

    // untuk memanggil semua data
    public List<DatabaseModel> getAllDatabase(){
        RealmResults<DatabaseModel> results = realm.where(DatabaseModel.class).findAll();
        return results;
    }

    // untuk menghapus data
    public void delete(Integer id){
        final RealmResults<DatabaseModel> model = realm.where(DatabaseModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}
