package org.bedu.dependencias

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RandomModule {

    @Singleton
    @Provides
    @Named("random")
    fun numRandom() = (0..100).random().toString()


}
