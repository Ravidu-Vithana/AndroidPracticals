package com.ryvk.sampleprojectday5.product

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryvk.sampleprojectday5.R


class ProductActivity : AppCompatActivity() {
    private lateinit var descriptionText: TextView
    private lateinit var descriptionTextContent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)

        descriptionTextContent = "Enjoy the crisp crunch of a classic Greek salad, tossed with juicy tomato, cucumber, olives, and feta cheese all piled high on a perfectly grilled beef patty, nestled in a toasted bun, and finished with a tangy tzatziki sauce for a Mediterranean twist on your favorite burger."

        descriptionText = findViewById(R.id.textView19)
        descriptionText.text = descriptionTextContent
        changeDescriptionText(shorten = true)

        val productExtrasList = listOf<ProductExtras>(
            ProductExtras("Spicy","$0.10"),
            ProductExtras("Mayonaise","$0.60")
        )

        val productExtrasAdapter = ProductExtrasAdapter(productExtrasList)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@ProductActivity)
        recyclerView.adapter = productExtrasAdapter

    }

    fun changeDescriptionText(shorten: Boolean){
        Log.d("TAG", "changeDescriptionText: received from read more")
        if(shorten){

            var sampleDescriptionShortened : String;

            if(descriptionTextContent.length > 100){
                sampleDescriptionShortened = descriptionTextContent.substring(0,90)+".... Read More"
                val ss = SpannableString(sampleDescriptionShortened)

                // creating clickable span to be implemented as a link
                val clickableSpan1: ClickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Log.d("TAG", "changeDescriptionText: received from read more 2")
                        changeDescriptionText(false)
                    }
                }
                ss.setSpan(clickableSpan1, 95, 104, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                descriptionText.text =ss
            }
        }else{
            val descriptionTextContentFull: String = descriptionTextContent.plus(" Show Less")
            val ss = SpannableString(descriptionTextContentFull)

            val clickableSpan1: ClickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    changeDescriptionText(true)
                }
            }

            ss.setSpan(clickableSpan1, descriptionTextContent.length+ 1, descriptionTextContent.length+ 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            descriptionText.text = ss
        }
        descriptionText.movementMethod = LinkMovementMethod.getInstance();
    }
}