package com.azrxr.test_suitmedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.azrxr.test_suitmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var edtName: EditText
    private lateinit var edtPalindrome: EditText
    private lateinit var btnCheck: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtName = binding.name
        edtPalindrome = binding.palindrome
        btnCheck = binding.check
        btnNext = binding.nex
        btnNext.setOnClickListener(this)
        btnCheck.setOnClickListener(this)

    }

    private fun checkPalindrome() {
        val palindrome = binding.palindrome.text.toString().replace("\\s".toRegex(), "").lowercase()
        val isPalindrome = palindrome == palindrome.reversed()

        if (isPalindrome) {
            showToast("Palindrome")
        } else {
            showToast("Not Palindrome")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.check -> checkPalindrome()
            R.id.nex -> screen2()
        }
    }

    private fun screen2() {
        val name = binding.name.text.toString()
        val intent = Intent(this, Screen2::class.java)
        intent.putExtra("USER_NAME", name)
        startActivity(intent)
    }
}