package com.ndtr.mylearningenglish.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ndtr.mylearningenglish.R;
import com.ndtr.mylearningenglish.adapters.PagerAdapter;

public class MainScreenActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(new PagerAdapter(this));

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
                    default:{
                        tab.setText("Tài khoản");
                        tab.setIcon(R.drawable.ic_account);
                        break;
                    }

                }
            }
        });
        tabLayoutMediator.attach();
    }

    @Override
    public void onBackPressed() {

    }
}