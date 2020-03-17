package com.example.stackoverflowuser.common;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

import static android.os.Environment.isExternalStorageRemovable;

@GlideModule
public class AppGlideModulConfig extends AppGlideModule {
    private static final int DISK_CACHE_SIZE_BYTES = 1024 * 1024 * 200;  // 200 MB
    private static final int MEMORY_CACHE_SCREENS = 2;

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(MEMORY_CACHE_SCREENS)
                .setBitmapPoolScreens(MEMORY_CACHE_SCREENS)
                .build();
        boolean isExternalExsist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !isExternalStorageRemovable();
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()))
                .setDiskCache(isExternalExsist ? new ExternalPreferredCacheDiskCacheFactory(context, DISK_CACHE_SIZE_BYTES) :
                        new InternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE_BYTES));
    }
}
