package com.ndtr.mylearningenglish.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.activities.MainScreenActivity;
import com.ndtr.mylearningenglish.chatbot.ChatBot;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {
    FloatingActionButton sendButton;
    EditText inputMessageEditText;
    ListView messageListView;
    List<String> messageList = new ArrayList<>();
    public ChatFragment(){}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendButton = view.findViewById(R.id.sendMessageButton);
        inputMessageEditText = view.findViewById(R.id.messageInputText);
        messageListView = view.findViewById(R.id.messageListView);

        messageList.add("Chao mung den voi Chatbot 1.0, ban co cau hoi gi khong?");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_expandable_list_item_1, messageList);

        messageListView.setAdapter(arrayAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = inputMessageEditText.getText().toString();
                if(message != ""){
                    messageList.add("Bạn: " +message);
                    String returnMessage = ChatBot.getMessage(message);
                    messageList.add(returnMessage);
                }
                inputMessageEditText.setText("");
                messageListView.smoothScrollToPosition(arrayAdapter.getCount()-1);
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}