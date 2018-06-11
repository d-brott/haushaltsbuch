package com.brott.haushaltsbuch.account;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.brott.haushaltsbuch.data.AccountInfo;
import com.brott.haushaltsbuch.data.AppRepository;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {

    private AppRepository repository;
    private LiveData<List<AccountInfo>> amounts;

    public AccountViewModel(Application application) {
        super(application);
        repository = new AppRepository(application);
        amounts = repository.getAmount();
    }

    LiveData<List<AccountInfo>> getAmounts() {
        return amounts;
    }


}
