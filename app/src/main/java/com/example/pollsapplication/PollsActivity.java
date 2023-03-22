package com.example.pollsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PollsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private PollAdapter adapter;
    private List<Poll> polls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);
        polls = new ArrayList<Poll>();
        adapter = new PollAdapter(polls);

       mDatabase = FirebaseDatabase.getInstance("https://pollsapplication-c98e5-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
       ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                polls.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Poll poll =ds.getValue(Poll.class);
                    polls.add(poll);
                }
                adapter.notifyItemRangeChanged(0,adapter.getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
       mDatabase.child("polls").addValueEventListener(postListener);
       /* Poll poll1=new Poll();

        poll1.question="Как делишки?";
        poll1.c1="Нормально";
        poll1.c2="Хорошо";
        poll1.c3="Плохо";
        poll1.c4="Отлично";
        polls.add(poll1);
        polls.add(poll1);*/
        recyclerView = findViewById(R.id.rec_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
