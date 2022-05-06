package vtsen.hashnode.dev.androidnews.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vtsen.hashnode.dev.androidnews.data.local.ArticlesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ArticlesDatabase {

        return Room.databaseBuilder(
            appContext,
            ArticlesDatabase::class.java,
            "articles.db",
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}