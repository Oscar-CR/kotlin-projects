package org.bedu.dependencias

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object RandomMainModule {

    @ActivityScoped
    @Provides
    @Named("randomMain")
    fun numRandom() = (0..100).random().toString()
}
