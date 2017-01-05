package com.challenge.bennho.a30days.helpers;

import android.content.Context;

import com.challenge.bennho.a30days.models.HistoryRecord;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by sionglengho on 2/1/17.
 */

public class RealmHelper {

    private Context context;
    private Realm realm;
    private ArrayList<RealmResults> realmResultsArr;

    public RealmHelper(Context context) {
        this.context = context;

        reinit(context);
    }

    public void reinit(Context context){
        if(realm == null || realm.isClosed()){
            // Initialize Realm
            Realm.init(context);

            // Get a Realm instance for this thread
            realm = Realm.getDefaultInstance();

            realmResultsArr = new ArrayList();
        }
    }

    public void insertHistoryRecord(final HistoryRecord historyRecord){
        RealmAsyncTask transaction = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealm(historyRecord);
            }
        }, null, null);
    }

    public void getAllHistoryRecordsByDateDesc(RealmChangeListener realmChangeListener){
        RealmResults<HistoryRecord> result = realm.where(HistoryRecord.class)
                .findAllSortedAsync("recordUnixTime", Sort.DESCENDING);
        realmResultsArr.add(result);
        result.addChangeListener(realmChangeListener);
    }

    public void deleteHistoryRecord(final HistoryRecord historyRecord){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                historyRecord.deleteFromRealm();
            }
        });
    }


    public void dispose(){
        if(!realm.isClosed()){
            for(RealmResults realmResults : realmResultsArr){
                realmResults.removeChangeListeners();
            }
        }

        realm.close();
    }





}
