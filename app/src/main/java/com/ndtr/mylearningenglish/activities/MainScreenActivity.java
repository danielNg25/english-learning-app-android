package com.ndtr.mylearningenglish.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.adapters.PagerAdapter;
import com.ndtr.mylearningenglish.adapters.RankingAdapter;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;
import com.ndtr.mylearningenglish.models.User;

import java.util.ArrayList;

public class MainScreenActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(new PagerAdapter(this));

        FirebaseAuth.getAllUser(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseAuth.userList = new ArrayList<>();
                for (DataSnapshot dataSnapshotChild: dataSnapshot.getChildren()){
                    User newUser = dataSnapshotChild.getValue(User.class);
                    FirebaseAuth.userList.add(newUser);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:{
                        tab.setText("Chủ đề");
                        tab.setIcon(R.drawable.ic_topic);
                        break;
                    }
                    case 1:{
                        tab.setText("Sổ từ");
                        tab.setIcon(R.drawable.ic_book);
                        break;
                    }
                    case 2:{
                        tab.setText("Chatbot");
                        tab.setIcon(R.drawable.ic_chat);
                        break;
                    }
                    case 3:{
                        tab.setText("Xếp hạng");
                        tab.setIcon(R.drawable.ic_ranking);
                        break;
                    }
                    default:{
                        tab.setText("Tài khoản");
                        tab.setIcon(R.drawable.ic_account);
                        break;
                    }

                }
            }
        });
        tabLayoutMediator.attach();

//        toolbar = findViewById(R.id.manScreenToolbar);
//        setActionBar(toolbar);
//
//        TextView noteBookFragmentTitle =  toolbar.findViewById(R.id.topicFragmentNumberTitleTV);
//        noteBookFragmentTitle.setVisibility(View.VISIBLE);
//        noteBookFragmentTitle.setText(((Integer)FirebaseAuth.user.getWordList().size()).toString());
//        ImageView noteBookFragmentTitleIM = toolbar.findViewById(R.id.topicFragmentTitleIM);
//        noteBookFragmentTitleIM.setVisibility(View.VISIBLE);
    }


    public void onAttachNoteBookFragment(){


    }
    @Override
    public void onBackPressed() {

    }
}