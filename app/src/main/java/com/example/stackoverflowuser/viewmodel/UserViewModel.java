package com.example.stackoverflowuser.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.model.UserPagedListResult;
import com.example.stackoverflowuser.model.business.BookmarkedOption;
import com.example.stackoverflowuser.repository.UserRepository;
import com.example.stackoverflowuser.repository.UserRepositoryImpl;
import com.example.stackoverflowuser.data.local.AppDatabase;
import com.example.stackoverflowuser.data.local.entity.UserEntity;

public class UserViewModel extends AndroidViewModel {
    private Context context;
    private UserPagedListResult userPagedListResult;
    private UserRepository userRepository;
    private MutableLiveData<BookmarkedOption> bookmarkedOptionLiveData = new MutableLiveData<>();
    private BookmarkedOption bookmarkedOption = new BookmarkedOption(false);

    public UserViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        userRepository = new UserRepositoryImpl(AppDatabase.getInstance(context).userDao());
        userPagedListResult = userRepository.loadUsers();
    }

    public LiveData<PagedList<UserEntity>> getUserPagedListLiveData() {
        return userPagedListResult.getUserPagedListLiveData();
    }

    public LiveData<String> getNetworkStateLiveData () {
        return userPagedListResult.getNetworkStateLiveData();
    }

    public LiveData<BookmarkedOption> getBookmarkedOptionLiveData () {
        return bookmarkedOptionLiveData;
    }

    public boolean getBookmarkerOptionValue () {
        return bookmarkedOption.isBookmarked();
    }

    /**
     * Action: update user Entity to Room db
     * @param userEntity
     */
    public void onUpdateUser(UserEntity userEntity) {
        userRepository.updateUser(userEntity);
    }

    /**
     * Action: Change config of bookmarked option
     * @param value: value of Bookmarked Option
     */
    public void onBookmarkedOptionChange (boolean value) {
        bookmarkedOption.setBookmarked(value);
        bookmarkedOptionLiveData.postValue(bookmarkedOption);
    }

    /**
     * Action: load bookmark option from shared references
     * @return: value of bookmarked option
     */
    public boolean onLoadBookmarkedOption() {
        bookmarkedOption.load(context);
        return bookmarkedOption.isBookmarked();
    }

    /**
     * Action: save Bookmarked Option to shared references
     *
     */
    public void onSaveBookmarkedOption() {
        bookmarkedOption.save(context);
    }
}
