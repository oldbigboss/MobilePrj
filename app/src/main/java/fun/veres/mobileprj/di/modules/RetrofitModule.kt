package `fun`.veres.mobileprj.di.modules

import `fun`.veres.mobileprj.BuildConfig
import `fun`.veres.mobileprj.data.api.DestinationService
import `fun`.veres.mobileprj.di.qualifiers.RestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Класс для создания сервисов с целью дальнейшей передачи ApiController'у
 * */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    @RestClient
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .apply {
                if (BuildConfig.DEBUG)
                    addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideDestinationApi(@RestClient httpClient: OkHttpClient): DestinationService {
        return getDestinationRetrofit(httpClient).create(DestinationService::class.java)
    }

    private fun getDestinationRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL_DESTINATION)
            .build()
    }

}