package com.ndtr.mylearningenglish.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.activities.TopicActivity;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.models.Topic;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicHolder> {

    private Context context;

    private List<String> topics;
    private List<String> shortDescription;

    public TopicAdapter(Context context, List<String> topics, List<String> shortDescription){
        this.context = context;
        this.topics = topics;
        this.shortDescription = shortDescription;
    }

    @NonNull
    @Override
    public TopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TopicHolder(LayoutInflater.from(context).inflate(R.layout.topic_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicHolder holder, int position) {
        String topic = topics.get(position);
        holder.topicNameTextView.setText(topic);
        holder.topicDescriptionTextView.setText(shortDescription.get(position));

        holder.topicFragLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getTopicByName(topic, new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null){
                            Toast.makeText(context, "Topic không tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            FirebaseAuth.topic = dataSnapshot.getValue(Topic.class);
                            context.startActivity(new Intent(context, TopicActivity.class));

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }
}
