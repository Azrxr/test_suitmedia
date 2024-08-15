package com.azrxr.test_suitmedia

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.azrxr.test_suitmedia.databinding.ActivityScreen3Binding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class Screen3 : AppCompatActivity() {

    companion object {
        private val TAG = Screen3::class.java.simpleName
    }

    private lateinit var binding: ActivityScreen3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val layoutManager = LinearLayoutManager(this)
        binding.listUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listUsers.addItemDecoration(itemDecoration)

        binding.back.setOnClickListener{
            finish()
        }
        getListUsers()
    }

    private fun getListUsers() {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://reqres.in/api/users?page=1&per_page=10"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                // Jika koneksi berhasil
                binding.progressBar.visibility = View.INVISIBLE
                val listUsers = ArrayList<User>()

                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val jsonArray = jsonObject.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        val userObject = jsonArray.getJSONObject(i)
                        val name = userObject.getString("first_name") + " " + userObject.getString("last_name")
                        val email = userObject.getString("email")
                        val avatar = userObject.getString("avatar")
                        listUsers.add(User(name, email, avatar))
                    }
                    val adapter = UsersAdapter(listUsers)
                    binding.listUsers.adapter = adapter
                } catch (e: Exception) {
                    Toast.makeText(this@Screen3, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                // Jika koneksi gagal
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@Screen3, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}