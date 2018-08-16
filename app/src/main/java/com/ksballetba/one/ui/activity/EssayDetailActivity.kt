package com.ksballetba.one.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import com.ksballetba.one.R
import com.ksballetba.one.tools.network.NetworkManager
import kotlinx.android.synthetic.main.activity_essay_detail.*
import kotlinx.android.synthetic.main.layout_bottom_card.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.dip
import org.jetbrains.anko.toast
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import org.w3c.dom.Document
import java.util.stream.Stream

class EssayDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_essay_detail)
        init()
    }

    private fun init(){
        setSupportActionBar(essay_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val essayId = intent.getStringExtra("essay_id")
        val serailId = intent.getStringExtra("serial_id")
        val questionId = intent.getStringExtra("question_id")
        if(essayId=="null"&&serailId=="null"){
            reading_type.text = "问答"
            showQuestionDetail(questionId)

        }
        if(essayId=="null"&&questionId=="null"){
            reading_type.text  = "连载"
            showSerialDetail(serailId)

        }
        if(serailId=="null"&&questionId=="null"){
            reading_type.text  = "阅读"
            showEssayDetail(essayId)

        }
    }

    private fun showEssayDetail(id:String){
        NetworkManager.getEssayDetail(id){essayDetail, error ->
            serial_button.visibility = View.GONE
            essay_title.text = essayDetail?.data?.hpTitle
            essay_author.text = "文/${essayDetail?.data!!.author[0].userName}"
            essay_content.settings.defaultTextEncodingName ="utf-8"
            essay_content.backgroundColor = 0
            essay_content.background.alpha = 0
            essay_content.loadDataWithBaseURL(null,essayDetail.data.hpContent,"text/html","UTF-8",null)
            essay_favcount.text = essayDetail.data.praisenum.toString()
            essay_commentcount.text = essayDetail.data.commentnum.toString()
        }
    }

    private fun showSerialDetail(id:String){
        NetworkManager.getSerialDetail(id){serialDetail, error ->
            if(error==null){
                essay_title.text = serialDetail?.data?.title
                essay_author.text = "文/${serialDetail?.data?.author?.userName}"
                essay_content.settings.defaultTextEncodingName ="utf-8"
                essay_content.backgroundColor = 0
                essay_content.background.alpha = 0
                essay_content.loadDataWithBaseURL(null,serialDetail?.data?.content,"text/html","UTF-8",null)
                essay_favcount.text = serialDetail?.data?.praisenum.toString()
                essay_commentcount.text = serialDetail?.data?.commentnum.toString()
            }
        }
    }

    private fun showQuestionDetail(id:String){
        NetworkManager.getQuestionDetail(id){question, error ->
            essay_title.text = question?.data?.questionTitle
            essay_author.setTextSize(TypedValue.COMPLEX_UNIT_SP,13f)
            essay_author.text = question?.data?.questionContent
            essay_content.settings.defaultTextEncodingName ="utf-8"
            essay_content.backgroundColor = 0
            essay_content.background.alpha = 0
            essay_content.loadDataWithBaseURL(null,question?.data?.answerContent,"text/html","UTF-8",null)
            essay_favcount.text = question?.data?.praisenum.toString()
            essay_commentcount.text = question?.data?.commentnum.toString()
        }
    }

    private fun setSerialClick(){
        serial_button.setOnClickListener {

        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
