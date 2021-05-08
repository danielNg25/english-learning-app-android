package com.ndtr.mylearningenglish.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ndtr.mylearningenglish.fragment.AccountFragment;
import com.ndtr.mylearningenglish.fragment.NoteBookFragment;
import com.ndtr.mylearningenglish.fragment.TopicFragment;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TopicFragment();
            case 1:
                return new NoteBookFragment();
            default:
                return new AccountFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
