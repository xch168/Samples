package com.github.xch168.samples.retrofitcoroutines

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.xch168.samples.R
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCoroutinesActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_coroutines)

        val logger = LoggingInterceptor.Builder()
            .setLevel(Level.BASIC)
            .build()
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubService = retrofit.create(GithubService::class.java)

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            val repoList = withContext(Dispatchers.IO) {
                githubService.listRepos("xch168")
            }
            var repos = "Repos:\n"
            for (repo in repoList)
            {
                Log.i("asdf", "repo: ${repo.name}")
                repos += "${repo.name}\n"
            }
            findViewById<TextView>(R.id.tv_repos).text = repos
        }
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, RetrofitCoroutinesActivity::class.java)
            context.startActivity(intent)
        }
    }
}