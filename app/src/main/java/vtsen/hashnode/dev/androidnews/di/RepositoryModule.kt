package vtsen.hashnode.dev.androidnews.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vtsen.hashnode.dev.androidnews.data.repository.SqlArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindArticlesRepository(impl: SqlArticlesRepository): ArticlesRepository
}