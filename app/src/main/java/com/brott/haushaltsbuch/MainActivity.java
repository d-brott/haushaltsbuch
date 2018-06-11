package com.brott.haushaltsbuch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.brott.haushaltsbuch.R;
import com.brott.haushaltsbuch.account.AccountFragment;
import com.brott.haushaltsbuch.transaction.TransactionFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    FragmentManager fragmentManager;
    AccountFragment accountFragment;
    TransactionFragment transactionFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            AccountFragment firstFragment = new AccountFragment();
            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().
                    add(R.id.fragment_container, firstFragment).commit();
        }

        bottomNavigation = findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_accounts:
                        accountFragment = new AccountFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, accountFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        break;
                    case R.id.action_transactions:
                        transactionFragment = new TransactionFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, transactionFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        break;
                    case R.id.action_statistics:
                        //TODO
                        break;
                }

                return true;
            }
        });


    }
}
