package me.mvvm.com.myapplication.data.network

import android.content.Context
import me.mvvm.com.myapplication.utils.Utils
import okhttp3.*
import java.io.IOException




/**
 * Created by Alexander Karpenko on 12.09.18.
 * java.karpenko@gmail.com
 */
class FakeInterceptor (private val mContext: Context?) : Interceptor {



    val MEDIA_JSON = MediaType.parse("application/json")

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val path = request.url().encodedPath()
        val stream = mContext?.assets?.open("fake_user.json" )
        val json = Utils.parseStream(stream)

        return Response.Builder()
                .message(stream.toString())
                .body(ResponseBody.create(MEDIA_JSON, json))
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(200)
                .build()
    }




}
