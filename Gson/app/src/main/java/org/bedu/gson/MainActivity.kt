package org.bedu.gson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okio.IOException
import org.bedu.gson.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val url = "https://swapi.dev/api/people/1/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)



        binding.btnJedi.setOnClickListener{
            llamarALaFuerza()
        }

        binding.btnSith.setOnClickListener{
            llamarALaFuerza(true)
        }
        setContentView(binding.root)
    }

    private  fun llamarALaFuerza(isSith: Boolean = false){

        //instanciando al cliente
        val okHttpClient = OkHttpClient()

        //El objeto Request contiene todos los parámetros de la petición (headers, url, body etc)
        val request = Request.Builder()
            .url(url)
            .build()

        val clientBuilder = okHttpClient.newBuilder()

        clientBuilder.build()
            .newCall(request)
            .enqueue(object : Callback {

                //el callback a ejecutar cuando hubo un error
                override fun onFailure(call: Call, e: IOException) {

                }

                //el callback a ejectutar cuando obtuvimos una respuesta
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    try {
                        val jedi = Gson().fromJson(body, Jedi::class.java)

                        runOnUiThread{
                            binding.tvName.text = jedi.name.toString()
                            binding.tvHeight.text = jedi.height.toString()
                            binding.tvWeight.text = jedi.mass.toString()
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
    }

}