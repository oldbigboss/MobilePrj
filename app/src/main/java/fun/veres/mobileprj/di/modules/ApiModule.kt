package `fun`.veres.mobileprj.di.modules

import `fun`.veres.mobileprj.data.api.ApiController
import `fun`.veres.mobileprj.data.api.DestinationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiController(
        destinationService: DestinationService
    ): ApiController = ApiController(destinationService)

}