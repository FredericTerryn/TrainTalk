package com.personal.frederic.TrainTalk.injection

import com.personal.frederic.TrainTalk.network.NmbsApi
import com.personal.frederic.TrainTalk.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies for the network.
 *
 */
@Module
object NetworkModule {


    /**
     * Provides the StationInfo Service implemenation
     * Builds the base url + the requested city for the right api.
     */
    @Provides
    internal fun provideNmbsApi(retrofit: Retrofit): NmbsApi {
        return retrofit.create(NmbsApi::class.java)
    }


    /**
     * Return the Retrofit object.
     */
    @Provides
    internal fun provideRetrofitInterface(): Retrofit {

        //To debug Retrofit/OkHttp we can intercept the calls and log them.
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}
