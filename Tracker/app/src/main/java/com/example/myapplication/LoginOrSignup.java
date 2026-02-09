package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginOrSignup extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_signup);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new LoginSignupPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(position == 0 ? "Login" : "Signup");
        }).attach();
    }
    public void enableTabSwitch(boolean enabled)
    {
        viewPager.setUserInputEnabled(enabled);
        for (int i = 0; i < tabLayout.getTabCount(); i++)
        {
            tabLayout.getTabAt(i).view.setEnabled(enabled);
        }
    }
}
