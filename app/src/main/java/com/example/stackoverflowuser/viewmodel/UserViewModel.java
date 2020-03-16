package com.example.stackoverflowuser.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.annotation.UserLoadType;
import com.example.stackoverflowuser.model.PagedListResult;
import com.example.stackoverflowuser.model.BookmarkedOption;
import com.example.stackoverflowuser.repository.UserRepository;
import com.example.stackoverflowuser.repository.UserRepositoryImpl;
import com.example.stackoverflowuser.data.local.AppDatabase;
import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.repository.datasource.UserPagedListBoundaryCallback;
import com.example.stackoverflowuser.util.LastUserPagedIndexSharedRefUtil;

public class UserViewModel extends AndroidViewModel {
    private Context context;
    private PagedListResult<UserEntity> allUserPagedListResult; // list for show all of users
    private PagedListResult<UserEntity> bookmarkedUserPagedListResult;  // list for show only bookmarked user
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
                return allUserPagedListResult.getPagedListLiveData();
            case UserLoadType.BOOKMARKED_USER:
                return bookmarkedUserPagedListResult.getPagedListLiveData();
                default:
                    break;
        }
        return allUserPagedListResult.getPagedListLiveData();
    }

    public LiveData<String> getUserPagedListNetworkStateLiveData () {
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

    /**
     * Action: save index of userList loaded page to shared references
     *
     */
    public void onSaveLastPageIndex() {
        LastUserPagedIndexSharedRefUtil
                .savePagedIndex(context, UserPagedListBoundaryCallback.lastRequestedPage);
    }

    /**
     * Action: load index of userList loaded page from shared references
     *
     */
    public void onLoadLastPageIndex() {
        UserPagedListBoundaryCallback.lastRequestedPage = LastUserPagedIndexSharedRefUtil.loadPagedIndex(context);
    }
}
