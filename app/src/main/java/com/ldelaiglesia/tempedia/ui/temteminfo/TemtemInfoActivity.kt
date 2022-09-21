package com.ldelaiglesia.tempedia.ui.temteminfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ldelaiglesia.tempedia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_temteminfo.*

class TemtemInfoActivity : AppCompatActivity() {

    lateinit var viewModel: TemtemInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temteminfo)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(TemtemInfoViewModel::class.java)

        initUI()
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        val number = intent.extras?.get("number") as Int

        viewModel.getTemtemInfo(number)

        viewModel.temtemInfo.observe(this, Observer { temtem ->
            nameTextView.text = temtem.name
            numberTextView.text = "Number: ${temtem.number}"
            typeTextView.text = "Types: ${
                temtem.types.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(", ", " - ")
            }"

            Picasso.get().load(temtem.portrait).into(imageView)
        })

    }


}