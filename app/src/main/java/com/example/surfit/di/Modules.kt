package com.example.surfit.di

import android.content.Context
import com.example.surfit.data.datasource.database.CarsDatabase
import com.example.surfit.data.repo.HomeRepo
import com.example.surfit.domain.repo.IHomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContactDatabase(
        @ApplicationContext appContext: Context,
    ): CarsDatabase {
        return CarsDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideCarsDao(database: CarsDatabase) = database.carsDao()

    @Singleton
    @Provides
    fun provideIdsDao(database: CarsDatabase) = database.idsDao()
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideHomeRepo(database: CarsDatabase, @ApplicationContext context: Context): IHomeRepo {
        return HomeRepo(database, context)
    }
}