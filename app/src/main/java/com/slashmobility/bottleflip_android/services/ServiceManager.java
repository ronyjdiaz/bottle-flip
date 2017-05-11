package com.slashmobility.bottleflip_android.services;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.slashmobility.bottleflip_android.Constants;
import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.adapters.ChallengeAdapter;
import com.slashmobility.bottleflip_android.adapters.RankingAdapter;
import com.slashmobility.bottleflip_android.fragments.RegisterOptionsFragment;
import com.slashmobility.bottleflip_android.model.Challenge;
import com.slashmobility.bottleflip_android.model.Score;
import com.slashmobility.bottleflip_android.model.User;
import com.slashmobility.bottleflip_android.services.callbacks.CallbackBoolean;
import com.slashmobility.bottleflip_android.services.callbacks.CallbackChallenge;
import com.slashmobility.bottleflip_android.services.callbacks.CallbackRanking;
import com.slashmobility.bottleflip_android.singleton.SingletonSession;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rony on 11/5/2017.
 */

public class ServiceManager {

    public static void getRanking(final CallbackRanking callbackRanking) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("scores").orderByChild("score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList scores = new ArrayList<>();
                for (DataSnapshot scoreDataSnapshot : dataSnapshot.getChildren()) {
                    Score score = scoreDataSnapshot.getValue(Score.class);
                    scores.add(score);
                }
                Collections.reverse(scores);
                callbackRanking.onSuccess(scores);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callbackRanking.onError(1, "Connection failed, please try again");
            }

        });

    }

    public static void getChallenges(final CallbackChallenge callbackChallenge) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child("challenges").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList challengesList = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Challenge challenge = noteDataSnapshot.getValue(Challenge.class);
                    challengesList.add(challenge);
                }
                callbackChallenge.onSuccess(challengesList);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callbackChallenge.onError(1, "Connection failed, please try again");
            }

        });
    }

    public static void createUser(User userObject, final CallbackBoolean callbackBoolean) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = database.child("users");
        Map<String, User> users = new HashMap<String, User>();
        usersRef.push().setValue(userObject);
        callbackBoolean.onSuccess(true);
    }

    public static void validateBottleCode(String code, final CallbackBoolean callbackBoolean) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child("bottleCodes").orderByChild("code").equalTo(code).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot codeDaSnapshot : dataSnapshot.getChildren()) {
                    if (codeDaSnapshot.exists()) {
                        callbackBoolean.onSuccess(true);
                        return;

                    } else {
                        callbackBoolean.onSuccess(false);

                    }
                }
                callbackBoolean.onSuccess(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callbackBoolean.onError(1, "Connection failed, please try again");
            }

        });


    }
}
