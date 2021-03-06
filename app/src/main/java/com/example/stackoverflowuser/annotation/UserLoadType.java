package com.example.stackoverflowuser.annotation;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.stackoverflowuser.annotation.UserLoadType.ALL_USER;
import static com.example.stackoverflowuser.annotation.UserLoadType.BOOKMARKED_USER;

@StringDef({ALL_USER, BOOKMARKED_USER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoadType {
    String ALL_USER = "all_of_user";
    String BOOKMARKED_USER = "bookmarked_user";
}
