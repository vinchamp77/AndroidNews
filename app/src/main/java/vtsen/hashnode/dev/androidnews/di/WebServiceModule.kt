package vtsen.hashnode.dev.androidnews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {

    @Provides
    @Singleton
    fun provideWebService(): WebService {
        return WebService()
    }
}