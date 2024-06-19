package com.tahirdev.practiceapplc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainViewModel
    // lateinit var textC : TextView

    private val quoteTxt : TextView
        get() = findViewById(R.id.quoteTxt)

    private val authorTxt : TextView
        get() = findViewById(R.id.quoteAuTxt)

    lateinit var prevB : Button
    lateinit var nextB : Button
    lateinit var share : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        window.navigationBarColor = getColor(R.color.rose_400)

        prevB = findViewById(R.id.previous)
        nextB = findViewById(R.id.next)
        share = findViewById(R.id.shareFloating)

        mViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)

        setQuote(mViewModel.getQuote())

        prevB.setOnClickListener(){

            setQuote(mViewModel.previousQuote())
        }
        nextB.setOnClickListener(){
            setQuote(mViewModel.nextQuote())
        }

       share.setOnClickListener(){
           val intent = Intent(Intent.ACTION_SEND)
           intent.setType("text/plain")
        //   intent.putExtra(Intent.EXTRA_TEXT,mViewModel.getQuote().text)
           val quote = mViewModel.getQuote()
           val shareText = "\"${quote.text}\" - ${quote.author}"
           intent.putExtra(Intent.EXTRA_TEXT,shareText)
           startActivity(intent)
       }

     //   mViewModel = ViewModelProvider(this,MainViewModelFactory(10))[MainViewModel::class.java]

      //  textC = findViewById(R.id.upText)

    //    setText()

      //  lifecycle.addObserver(ObserverLC())
      //  Log.d("TAG","Activity OnCreate")
    }

 /*   private fun setText(){
        textC.text = mViewModel.count.toString()
    }*/

   /* fun increment(v:View){
        mViewModel.increment()
        setText()
    }*/

    fun setQuote(quote: Quote){
        quoteTxt.text = quote.text
        authorTxt.text = quote.author
    }

}

