package com.example.carrosel

import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var carousel: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carousel = findViewById(R.id.carousel)

        // Gera URLs aleatórias das imagens
        val imageUrls = generateRandomImageUrls(10) // Gere 10 URLs de imagens aleatórias

        // Adiciona imagens ao carousel
        for (url in imageUrls) {
            val imageView = ImageView(this)
            val layoutParams = LinearLayout.LayoutParams(500, 600) // Tamanho desejado
            layoutParams.setMargins(16, 0, 16, 0)
            imageView.layoutParams = layoutParams
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP

            // Carrega a imagem usando Glide
            Glide.with(this)
                .load(url)
                .into(imageView)

            carousel.addView(imageView)
        }

        // Botões de navegação
        findViewById<ImageButton>(R.id.btn_left).setOnClickListener {
            scrollLeft()
        }
        findViewById<ImageButton>(R.id.btn_right).setOnClickListener {
            scrollRight()
        }
    }

    private fun scrollLeft() {
        val scrollView = findViewById<HorizontalScrollView>(R.id.scrollView)
        scrollView.smoothScrollBy(-200, 0)
    }

    private fun scrollRight() {
        val scrollView = findViewById<HorizontalScrollView>(R.id.scrollView)
        scrollView.smoothScrollBy(200, 0)
    }

    private fun generateRandomImageUrls(count: Int): List<String> {
        val urls = mutableListOf<String>()
        for (i in 0 until count) {
            // Gera um número aleatório para cada URL
            val randomId = Random.nextInt(10000)
            val url = "https://picsum.photos/500/600?$randomId"
            urls.add(url)
        }
        return urls
    }
}