package com.example.sipembawaberita

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var WowNews: RecyclerView
    private val list = ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.about_page).setOnClickListener{
            val intent = Intent(this, DetailProfile::class.java)
            startActivity(intent)
        }

        WowNews = findViewById(R.id.rv_news)
        WowNews.setHasFixedSize(true)
        list.addAll(getListNews())
        showRecyclerList()
    }


    private fun showSelectedNews(news: News) {
//        Toast.makeText(this, "Kamu memilih " + news.photo, Toast.LENGTH_SHORT).show()
        val newsDetailIntent = Intent(this@MainActivity, DetailNews::class.java)
        newsDetailIntent.putExtra(DetailNews.EXTRA_NEWS, news)
        startActivity(newsDetailIntent)
    }

    private fun getListNews(): ArrayList<News> {
        val dataName = resources.getStringArray(R.array.data_judul)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataNewsLink = resources.getStringArray(R.array.link_news)
        val listNews = ArrayList<News>()
        for (i in dataName.indices) {
            val news = News(dataName[i], dataDescription[i], dataPhoto[i], dataNewsLink[i] )
            listNews.add(news)
        }
        return listNews
    }

    private fun showRecyclerList() {
        WowNews.layoutManager = LinearLayoutManager(this)
        val listNewsAdapter = ListNewsAdapter(list)
        WowNews.adapter = listNewsAdapter

        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: News) {
                showSelectedNews(data)
            }
        })
    }
}