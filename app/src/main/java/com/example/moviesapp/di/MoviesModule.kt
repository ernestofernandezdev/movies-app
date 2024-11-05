package com.example.moviesapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.moviesapp.ddl.data.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Provides
    @MoviesSharedPreference
    fun provideSharedPreferences(
        @ApplicationContext
        context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("movies", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0N2NiYTc2OTFiN2RmMWNiNzhiMzQzMDdjYmVmZGE4NiIsIm5iZiI6MTczMDMxMDY5NS4xODY5OSwic3ViIjoiNjcyMjZmZGFkOWE4YTc3YjVkYTQ1ZjA3Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.88ZQu1Pndi3GaQL35PXUWaElz36C08rBIQD0abuhh9w")
                .build()
            chain.proceed(newRequest)
        }.build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieApi(
        retrofit: Retrofit
    ): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

}