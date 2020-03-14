package com.example.stackoverflowuser.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.common.UserLoadType;
import com.example.stackoverflowuser.model.UserPagedListResult;
import com.example.stackoverflowuser.model.BookmarkedOption;
import com.example.stackoverflowuser.repository.UserRepository;
import com.example.stackoverflowuser.repository.UserRepositoryImpl;
import com.example.stackoverflowuser.data.local.AppDatabase;
import com.example.stackoverflowuser.data.local.entity.UserEntity;

public class UserViewModel extends AndroidViewModel {
    private Context context;
    private UserPagedListResult allUserPagedListResult; // list for show all of users
    private UserPagedListResult bookmarkedUserPagedListResult;  // list for show only bookmarked user
    private UserRepository userRepository;
    private MutableLiveData<BookmarkedOption> bookmarkedOptionLiveData = new MutableLiveData<>();
    private BookmarkedOption bookmarkedOption = new BookmarkedOption(false);

    public UserViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        userRepository = new UserRepositoryImpl(AppDatabase.getInstance(context).userDao());
        allUserPagedListResult = userRepository.loadUsers(UserLoadType.ALL_USER);
        bookmarkedUserPagedListResult = userRepository.loadUsers(UserLoadType.BOOKMARKED_USER);
    }

    public LiveData<PagedList<UserEntity>> getUserPagedListLiveData(@UserLoadType String loadType) {
        switch (loadType) {
            case UserLoadType.ALL_USER:
                return allUserPagedListResult.getUserPagedListLiveData();
            case UserLoadType.BOOKMARKED_USER:
                return bookmarkedUserPagedListResult.getUserPagedListLiveData();
                default:
                    break;
        }
        return allUserPagedListResult.getUserPagedListLiveData();
    }

    public LiveData<String> getNetworkStateLiveData () {
        return allUserPagedListResult.getNetworkStateLiveData();
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
