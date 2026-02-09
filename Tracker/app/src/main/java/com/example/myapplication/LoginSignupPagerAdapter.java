package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginSignupPagerAdapter extends FragmentStateAdapter
{
    public LoginSignupPagerAdapter(@NonNull FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        if (position == 0)
        {
            return new LoginFragment();
        }
        return new SignupFragment();
    }

    @Override
    public int getItemCount()
    {
        return 2;
    }
}
