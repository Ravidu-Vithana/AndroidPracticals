package com.ryvk.sampleprojectday4.community

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryvk.sampleprojectday4.R


class CommunityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_community)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val relativeLayout: RelativeLayout = findViewById(R.id.editTextRelativeLayout)
        val searchEditText: EditText = findViewById(R.id.editTextText5)
        relativeLayout.setOnClickListener{
            searchEditText.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)
        }

        val scale: Float = getResources().getDisplayMetrics().density
        val pixels: Int = (40 * scale + 0.5f).toInt()

        searchEditText.setOnFocusChangeListener { v, hasFocus ->
            run {
                if (hasFocus) {
                    searchEditText.layoutParams = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        pixels
                    )
                }else{
                    searchEditText.layoutParams = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        pixels
                    )
                }
            }
        }

        var forumList = listOf(
            ForumCard(
                "How to Start Investing in uStock",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "link//:to_join"
            ),
            ForumCard(
                "How to Predict the Candlestick",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "link//:to_join"
            ),
            ForumCard(
                "Is Trading Safe for Newbie Engineer",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "link//:to_join"
            ),
        )

        val forumAdapter = ForumAdapter(forumList)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@CommunityActivity)
        recyclerView.adapter = forumAdapter

    }
}