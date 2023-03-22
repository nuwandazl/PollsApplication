package com.example.pollsapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.ViewHolder> {

    private List<Poll> pollList;

    public PollAdapter(List<Poll> polls){
        pollList=polls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);

        View pollView=inflater.inflate(R.layout.poll_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(pollView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poll poll=pollList.get(position);
        holder.textView.setText(poll.question);
        holder.b1.setText(poll.c1);
        holder.b2.setText(poll.c2);
        holder.b3.setText(poll.c3);
        holder.b4.setText(poll.c4);
    }

    @Override
    public int getItemCount() {
        return pollList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private Button b1, b2,b3,b4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.txtView);
            b1 = itemView.findViewById(R.id.btn1);
            b2 = itemView.findViewById(R.id.btn2);
            b3 = itemView.findViewById(R.id.btn3);
            b4 = itemView.findViewById(R.id.btn4);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b1.setBackgroundColor(Color.parseColor("#00FF00"));
                    b1.setEnabled(false);
                    b2.setEnabled(false);
                    b2.setEnabled(false);
                    b4.setEnabled(false);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b2.setBackgroundColor(Color.parseColor("#00FF00"));
                    b1.setEnabled(false);
                    b2.setEnabled(false);
                    b2.setEnabled(false);
                    b4.setEnabled(false);
                }
            });

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b3.setBackgroundColor(Color.parseColor("#00FF00"));
                    b1.setEnabled(false);
                    b2.setEnabled(false);
                    b2.setEnabled(false);
                    b4.setEnabled(false);
                }
            });

            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b4.setBackgroundColor(Color.parseColor("#00FF00"));
                    b1.setEnabled(false);
                    b2.setEnabled(false);
                    b2.setEnabled(false);
                    b4.setEnabled(false);
                }
            });

        }
    }
}
