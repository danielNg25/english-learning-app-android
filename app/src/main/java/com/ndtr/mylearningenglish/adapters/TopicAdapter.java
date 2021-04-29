package com.ndtr.mylearningenglish.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.activities.TopicActivity;
import com.ndtr.mylearningenglish.firebase.FirebaseQuery;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicHolder> {

    private Context context;

    private List<String> topics;

    public TopicAdapter(Context context, List<String> topics){
        this.context = context;
        this.topics = topics;
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

        holder.topicFragLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topicName = topics.get(position);
                FirebaseQuery.topic = FirebaseQuery.getTopicByName(topicName, context);
                if (FirebaseQuery.topic == null){
                    Toast.makeText(context, "Topic không tồn tại!", Toast.LENGTH_SHORT).show();
                }
                else{
                    context.startActivity(new Intent(context, TopicActivity.class));
                    Toast.makeText(context, FirebaseQuery.topic.getWordList().get(0), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }
}
