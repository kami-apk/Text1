package com.kamiapk.text1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var Mozi = ""

    //連続タップに対するフラグ
    //フラグがtrueなら文字の更新可能
    private var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Mozi = editText.getText().toString()
            //文字列を引数に取る関数を呼ぶ。
            Ichi(Mozi)
            //一応出力をしたらeditTextから文字を消しておく。
            editText.setText("")
        }

    }


    fun Ichi(Mozi :String){

        //多重起動防止
        if(flag == true){
            //文字が更新中の間はfalseにしておく。
            flag = false
            //文字の長さを取得
            val L = Mozi.length
            //繰り返す用のi
            var i = 0
            //表示させるstring型でこれを更新することを考える
            var display_text = ""
            //文字が更新される直前にはtextViewを初期化しておく。
            textView.text = ""
            //
            Thread{
                while(i < L){
                    //Moziを一文字ずつ取り出す。
                    display_text += Mozi.get(i)
                    //サブスレッドからメインスレッドのUIを更新
                    textView.post{

                        textView.text = display_text
                    }
                    i += 1
                    //0.1秒止める。
                    Thread.sleep(100)
                }
                //文字の更新が終わったらフラグを変更する。
                //リストや配列に格納してる文字列を表示させたい場合、
                // ここにインデックスの操作をすると良さそう。
                flag = true
            }.start()
        }


    }

}
