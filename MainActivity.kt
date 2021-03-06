package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastint : Boolean = false
    var lastdec : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view:View){
        tvInput.append((view as Button).text)
        lastint = true
    }
    fun onClear(view:View){
        tvInput.text = ""
        lastint = false
        lastdec = false
    }

    fun onOper(view: View){
        if(lastint and !isOperator(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastdec = false
            lastint = false
        }
    }
    fun onDecip(view: View) {
        if (lastint and !lastdec) {
            tvInput.append(".")
            lastint = false
            lastdec = true
        }

    }
    fun onEqual(view: View) {
        if(lastint){
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitStr = tvValue.split("-")
                    var a = splitStr[0]
                    var b = splitStr[1]

                    if(!prefix.isEmpty()){
                        a = prefix + a
                    }

                    tvInput.text = (a.toDouble() - b.toDouble()).toString()
                }

                if(tvValue.contains("+")){
                    val splitStr = tvValue.split("+")
                    var a = splitStr[0]
                    var b = splitStr[1]

                    tvInput.text = (a.toDouble()+b.toDouble()).toString()
                }

                if(tvValue.contains("*")){
                    val splitStr = tvValue.split("*")
                    var a = splitStr[0]
                    var b = splitStr[1]

                    tvInput.text = (a.toDouble() * b.toDouble()).toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun isOperator(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("+") || value.contains("*") || value.contains("-")
        }
    }
}