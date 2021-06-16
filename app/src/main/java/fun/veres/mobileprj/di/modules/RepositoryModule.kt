package `fun`.veres.mobileprj.di.modules

import `fun`.veres.mobileprj.data.api.ApiController
import `fun`.veres.mobileprj.data.destination.DestinationRepository
import `fun`.veres.mobileprj.data.destination.DestinationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * При добавлении кэширования добавляется DbModule::class
 * */
@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDestinationRepository(
        apiController: ApiController
    ): DestinationRepository = DestinationRepositoryImpl(apiController)

}
