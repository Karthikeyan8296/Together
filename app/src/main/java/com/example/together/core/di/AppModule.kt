package com.example.together.core.di

import com.example.together.core.network.AuthHeaderInterceptor
import com.example.together.core.network.TokenAuthenticator
import com.example.together.data.remote.api.AuthAPI
import com.example.together.data.remote.api.TokenAPI
import com.example.together.data.repository.AuthRepositoryImpl
import com.example.together.data.repository.OnboardingRepositoryImpl
import com.example.together.domain.repository.AuthRepository
import com.example.together.domain.repository.OnBoardingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Token-only Retrofit WITHOUT interceptor
    @Provides
    @Singleton
    @Named("token")
    fun provideTokenRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.7:5500/api/v1/")
//            .baseUrl("https://together-server-mvkc.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTokenAPI(@Named("token") retrofit: Retrofit): TokenAPI =
        retrofit.create(TokenAPI::class.java)

    //Main OkHttp WITH Auth + Refresh
    @Provides
    @Singleton
    fun provideOkHttpClient(
        authHeaderInterceptor: AuthHeaderInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authHeaderInterceptor)
            .authenticator(tokenAuthenticator)
            .build()
    }

    //Main Retrofit WITH OkHttp
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.7:5500/api/v1/")
//            .baseUrl("https://together-server-mvkc.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI = retrofit.create(AuthAPI::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindOnBoardingRepository(
        impl: OnboardingRepositoryImpl
    ): OnBoardingRepository
}