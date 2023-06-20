package com.example.dfs

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray


class RenewWeight : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frm_weight_graph)

        val textView = findViewById<TextView>(R.id.stateText)
        val editText = findViewById<EditText>(R.id.weightInput)
        val buttonSave = findViewById<Button>(R.id.ButtonSave)

        buttonSave.setOnClickListener {
            // エディットテキストのテキストを取得
            val text = editText.text.toString()
            // 読み込み
            val arrayList: ArrayList<String> = loadArrayList("weight")
            arrayList.add(text)
            // 保存
            saveArrayList("weight", arrayList)
            if (text.isEmpty()) textView.text = "no_text"
            else textView.text = "saved"
        }

        buttonSave.setOnClickListener {
            val arrayList: ArrayList<String> = loadArrayList("weight")
            val str = arrayList[arrayList.lastIndex]
            if (str != null) {
                textView.text = str
            } else {
                textView.text = "read_error"
            }
        }
    }

    // リストの保存
    private fun saveArrayList(key: String, arrayList: ArrayList<String>) {

        val shardPreferences = this.getPreferences(Context.MODE_PRIVATE)
        val shardPrefEditor = shardPreferences.edit()

        val jsonArray = JSONArray(arrayList)
        shardPrefEditor.putString(key, jsonArray.toString())
        shardPrefEditor.apply()
    }

    // リストの読み込み
    private fun loadArrayList(key: String): ArrayList<String> {

        val shardPreferences = this.getPreferences(Context.MODE_PRIVATE)

        val jsonArray = JSONArray(shardPreferences.getString(key, "[]"));

        val arrayList: ArrayList<String> = ArrayList()

        for (i in 0 until jsonArray.length()) {
            arrayList.add(jsonArray.get(i) as String)
        }
        return arrayList
    }
}
