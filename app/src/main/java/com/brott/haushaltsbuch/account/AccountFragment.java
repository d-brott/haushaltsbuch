package com.brott.haushaltsbuch.account;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brott.haushaltsbuch.BR;
import com.brott.haushaltsbuch.databinding.FragmentAccountBinding;
import com.brott.haushaltsbuch.R;
import com.brott.haushaltsbuch.data.AccountInfo;

import java.util.List;
import java.util.logging.Logger;

public class AccountFragment extends Fragment {
    private static final Logger LOGGER = Logger.getLogger(AccountFragment.class.getName());

    private AccountViewModel mAccountViewModel;
    private AccountViewData mAccountViewData;

    private double moneyAmount = 0.0d;
    private double checkAccountAmount = 0.0d;
    private double savingsAmount = 0.0d;
    private double portfolioAmount = 0.0d;

    public AccountFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentAccountBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        View root = binding.getRoot();

        mAccountViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AccountViewModel.class);
        mAccountViewModel.getAmounts().observe(this, new Observer<List<AccountInfo>>() {
            @Override
            public void onChanged(@Nullable final List<AccountInfo> accountInfoList) {

                if (accountInfoList != null && accountInfoList.size() >= 0) {
                    setAccounts(accountInfoList);

                    mAccountViewData = new AccountViewData(
                            calculateTotalAssets(accountInfoList),
                            calculateSavings(accountInfoList),
                            calculateLiquidAssets(accountInfoList),
                            moneyAmount,
                            checkAccountAmount,
                            savingsAmount,
                            portfolioAmount);

                    binding.setVariable(BR.accountViewData, mAccountViewData);

                }
            }
        });

        return root;
    }

    private double calculateTotalAssets(List<AccountInfo> accountInfoList) {
        double sum = 0.0d;

        for (AccountInfo accountInfo : accountInfoList) {
            sum += accountInfo.getAmount();
        }

        return sum;
    }

    private double calculateSavings(List<AccountInfo> accountInfoList) {
        double sum = 0.0d;

        for (AccountInfo accountInfo : accountInfoList) {
            if (accountInfo.getAccountType().equals(AccountTypeEnum.SAVINGS_ACCOUNT.toString())) {

                sum += accountInfo.getAmount();
            }
        }

        return sum;
    }

    private double calculateLiquidAssets(List<AccountInfo> accountInfoList) {
        double sum = 0.0d;

        for (AccountInfo accountInfo : accountInfoList) {
            if (accountInfo.getAccountType().equals(AccountTypeEnum.ACCOUNT_FOR_PAYMENTS.toString())) {

                sum += accountInfo.getAmount();
            }
        }

        return sum;
    }

    private void setAccounts(List<AccountInfo> accountInfoList) {
        for (AccountInfo aI : accountInfoList) {
            if (aI.getAccountName().equals(AccountNameEnum.MONEY.toString())) {
                moneyAmount = aI.getAmount();
            } else if (aI.getAccountName().equals(AccountNameEnum.CHECK_ACCOUNT.toString())) {
                checkAccountAmount = aI.getAmount();
            } else if (aI.getAccountName().equals(AccountNameEnum.SAVINGS_ACCOUNT.toString())) {
                savingsAmount = aI.getAmount();
            } else if (aI.getAccountName().equals(AccountNameEnum.PORTFOLIO.toString())) {
                portfolioAmount = aI.getAmount();
            }
        }
    }

}
