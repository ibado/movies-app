package com.bado.ignacio.movies_app.di

import com.bado.ignacio.movies_app.BuildConfig
import com.bado.ignacio.movies_app.data.remote.MovieService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun provideOkHTTPClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            var request = chain.request()
            val url = request.url()
                .newBuilder()
                .addQueryParameter("api_key", "ca6251fbfb38940310977ff827b8dc3e")
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }.build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}