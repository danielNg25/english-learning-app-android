package com.ndtr.mylearningenglish.chatbot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.activities.WordsListActivity;
import com.ndtr.mylearningenglish.adapters.WordAdapter;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.models.Topic;
import com.ndtr.mylearningenglish.models.Word;
import com.ndtr.mylearningenglish.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/* getMessageType():    0 for not found
                        1 for topic type
                        2 for word type
                        3 for example type
                        4 for exercise type
 */
public class ChatBot {
    private static List<String> topicsName;
    public static Topic topic;
    public static String TOPICMESSAGE = "chu de";
    public static String EXAMPLEMESSAGE ="vi du";
    public static String WORDMESSAGE = "tu";
    public static String EXERCISEMESSAGE = "bai tap";

    public static List<Word> wordList = new ArrayList<>();

    private static int getMessageType(String message){

        if (message.contains(TOPICMESSAGE)) return 1;
        else if (message.contains(WORDMESSAGE)) return 2;
        else if (message.contains(EXERCISEMESSAGE)) return 4;
        else if(message.contains(EXAMPLEMESSAGE)) return 3;
        else return 0;
    }

    public static String getMessage(String message){
        topicsName = FirebaseAuth.topicsName;
        String changedMessage = Utils.removeAccent(message).toLowerCase();
        int type = getMessageType(changedMessage);
        String returnMessage;
        switch(type){
            case 1:
                if (getTopic(changedMessage)){
                    returnMessage = "Ban thac mac van de gi ve chu de " + topic.getTopicName();
                }
                else returnMessage = "Khong the tim thay chu de";
                break;
            case 2:
                if (ChatBot.topic == null)  returnMessage = "Ban chua chon chu de";
                else{
                    Word word = getWord(changedMessage);
                    if (word == null)   returnMessage = "Khong the tim thay tu da nhap";
                    else returnMessage = "Tu: " + word.getWordName() +"; Y nghia: " + word.getMeaning();
                }
                break;
            case 3:
                if (ChatBot.topic == null)  returnMessage = "Ban chua chon chu de";
                else{
                    returnMessage = "Vi du";
                }
                break;
            case 4:
                if (ChatBot.topic == null)  returnMessage = "Ban chua chon chu de";
                else{
                    returnMessage = "Bai tap";
                }
                break;
            default:
                returnMessage = "Khong tim duoc yeu cau";
        }
        return returnMessage;
    }

    public static boolean getTopic(String message){
        boolean founded = false;
        for (String topicName : topicsName){

            if (message.contains(topicName.toLowerCase())){
                founded = true;
                FirebaseAuth.getTopicByName(topicName, new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ChatBot.topic = dataSnapshot.getValue(Topic.class);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                List<String> wordsIDList = ChatBot.topic.getWordList();
                for (String wordID: wordsIDList){
                    FirebaseAuth.getWordsFromID(wordID, new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getValue() != null){

                                Word newWord = dataSnapshot.getValue(Word.class);
                                ChatBot.wordList.add(newWord);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        }
        return founded;
    }

    public static Word getWord(String message){
        for (Word word: ChatBot.wordList){
            if (message.contains(word.getWordName().toLowerCase())){
                return word;
            }
        }
        return null;
    }
}
