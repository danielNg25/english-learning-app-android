package com.ndtr.mylearningenglish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.activities.TopicActivity;
import com.ndtr.mylearningenglish.adapters.TopicAdapter;
import com.ndtr.mylearningenglish.firebase.FirebaseQuery;

import java.util.ArrayList;
import java.util.List;

public class TopicFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<String> topicsName = new ArrayList<String>();
    private List<String> topicsDescription = new ArrayList<>();
    private TopicAdapter topicAdapter;

    public TopicFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_page, container, false);

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.topic_rv);
        FirebaseQuery.getAllTopicName(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshotChild: dataSnapshot.getChildren()){
                    topicsName.add(dataSnapshotChild.getKey());
                    topicsDescription.add(dataSnapshotChild.child("shortDescription").getValue().toString());

                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                topicAdapter = new TopicAdapter(getActivity(), topicsName, topicsDescription);
                recyclerView.setAdapter(topicAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
}
