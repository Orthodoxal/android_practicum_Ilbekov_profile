package com.example.androidpracticumprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidpracticumprofile.databinding.ActivityMainBinding


/**
 * Пару слов
 * В задании A смотрим на HWUI rendering
 * В задании B смотрим на GPU overdraw меняем картинки для списка
 * В задании C смотрим на память
 */
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val itemListData = buildList {
            repeat(200) {
                add(ImageInfo(
                    // Устанавливаем легкое изображение large_web (Задание A)
                    // Устанавливаем тяжелое изображение night для списка (Задание B)
                    R.drawable.night,
                    R.drawable.dinosaur_large_real,
                    "My text - $it")
                )
            }
        }
        val adapter = ItemAdapter().apply {
            itemList = itemListData
        }
        binding.recyclerView.adapter = adapter
        setContentView(binding.root)
    }
}