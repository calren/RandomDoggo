package com.caren.doggo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dog.ceo/api/"

// Added to print out network requests and responses in Logcat
// Available via the dependency added in build.gradle: com.squareup.okhttp3:logging-interceptor:4.7.2
private val networkLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * https://zenquotes.io/#docs
 */
interface DogApiService {

    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): RandomDogApiResponse

    // https://dog.ceo/api/breed/{hound}/images/random
    @GET("breed/{breed}/images/random")
    suspend fun getRandomDogImageByBreed(@Path("breed") breed: String): RandomDogApiResponse

}

object DogApi {
    val retrofitService: DogApiService by lazy { retrofit.create(DogApiService::class.java) }
}