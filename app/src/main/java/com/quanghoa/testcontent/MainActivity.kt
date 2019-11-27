package com.quanghoa.testcontent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private fun randInt(min: Int, max: Int): Int{
        return Random().nextInt(max - min) + min
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questions =
            (0 .. randInt(40, 100))
                .map { Question("Question $it", (1 .. randInt(2, 4)).map { x-> "Answer ${'A' + x}" })}

        recycler_view.adapter = TestAdapter(questions)

        bt_check.setOnClickListener {
            check()
        }

    }

    fun check(){
        val adapter = recycler_view.adapter as TestAdapter
        val answers = adapter.selection.take(10)
            .joinToString("\n") { if (it == -1) "None" else "Answer ${'A' + it}" }


        AlertDialog.Builder(this).setMessage(answers)
            .setTitle("First 10 answers")
            .show()

    }
}
