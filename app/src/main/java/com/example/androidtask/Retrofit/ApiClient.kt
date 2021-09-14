import com.example.weatherapp.Retrofit.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Create a singleton instance of RadissonAPI to interact with the REST API
 * Initialise it at very first entry point of the application e.g. app class
 */

object ApiClient {

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var service: ApiInterface

    fun init(baseUrl: String) {
        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

        okHttpClient = builder.build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        service = retrofit.create(ApiInterface::class.java)
    }
    fun getService(): ApiInterface {
        return service
    }
}