package com.ndtr.mylearningenglish.fragment;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.adapters.WordAdapter;
import com.ndtr.mylearningenglish.firebase.FirebaseQuery;
import com.ndtr.mylearningenglish.models.Word;

import java.util.ArrayList;
import java.util.List;


public class NoteBookFragment extends Fragment {

    private RecyclerView recyclerView;
    public static WordAdapter wordAdapter;
    private TextView blankTextView;
    public static List<Word> wordList;

    public NoteBookFragment(){}



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        blankTextView = view.findViewById(R.id.blankWordListTV);
        recyclerView = view.findViewById(R.id.noteBookRV);
        wordList = new ArrayList<>();

        List <String> wordIDList = FirebaseQuery.user.getWordList();

        if (wordIDList == null){
            recyclerView.setVisibility(View.GONE);
            blankTextView.setVisibility(View.VISIBLE);
        }
        else{
            recyclerView.setVisibility(View.VISIBLE);
            blankTextView.setVisibility(View.GONE);
            for (String wordID: wordIDList){
                FirebaseQuery.getWordsFromID(wordID, new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null){
                            Word newWord = dataSnapshot.getValue(Word.class);
                            wordList.add(newWord);
                        }
                        if (wordID == wordIDList.get(wordIDList.size()-1)){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            wordAdapter = new WordAdapter(getContext() , wordList);
                            recyclerView.setAdapter(wordAdapter);
                            recyclerView.setLayoutManager(linearLayoutManager);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_note_book, container, false);
    }
}