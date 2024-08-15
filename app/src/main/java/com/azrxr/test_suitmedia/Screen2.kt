package com.azrxr.test_suitmedia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.azrxr.test_suitmedia.databinding.ActivityScreen2Binding

class Screen2 : AppCompatActivity() {

    private lateinit var binding: ActivityScreen2Binding
    private lateinit var btnChoose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("USER_NAME")
        binding.name.text = name

        btnChoose = binding.btnChoose
        btnChoose.setOnClickListener {
            val intent = Intent(this, Screen3::class.java)
            startActivity(intent)
        }
        binding.back.setOnClickListener{
            finish()
        }

    }

}