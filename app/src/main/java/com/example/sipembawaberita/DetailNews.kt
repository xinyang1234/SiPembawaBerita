package com.example.sipembawaberita

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailNews : AppCompatActivity() {

    companion object{
        const val EXTRA_NEWS = "extra_news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        findViewById<ImageView>(R.id.arrow_back_detail_news).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<ImageView>(R.id.open_detail_news).setOnClickListener{
            val news = intent.getParcelableExtra<News>(EXTRA_NEWS)
            val url = news?.link
            val urlIntent = Intent(Intent.ACTION_VIEW)
            urlIntent.data = Uri.parse(url)
            startActivity(urlIntent)

        }

        val judulNews: TextView = findViewById(R.id.Judul_news)
        val DescriptionNewsText: TextView = findViewById(R.id.Description_News)
        val photoNews: ImageView = findViewById(R.id.image_news)

        val news = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<News>(EXTRA_NEWS, News::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<News>(EXTRA_NEWS)
        }

        if (news != null) {
            val judul =
                "${news.name}"
            judulNews.text = judul
            val description =
                "${news.description}"
            DescriptionNewsText.text = description
            val photoUrl = news.photo
            Glide.with(this)
                .load(photoUrl)
                .into(photoNews)


        } else {

        }
        }
    }
