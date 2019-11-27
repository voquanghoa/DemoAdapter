package com.quanghoa.testcontent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_layout.*

class TestAdapter(private val questions: List<Question>) : RecyclerView.Adapter<TestAdapter.QuestionViewHolder>() {

    val selection = IntArray(questions.size){-1}

    inner class QuestionViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{

        private val radioButtons = listOf(answer_a, answer_b, answer_c, answer_d)

        var index = 0
        get() = field
        set(value){
            field = value
            if(field >= 0){
                val question = questions[field]

                radioButtons.forEachIndexed { i, radioButton ->
                    if(i >= question.answers.size){
                        radioButton.visibility = View.GONE
                    }else{
                        radioButton.visibility = View.VISIBLE
                        radioButton.text = question.answers[i]
                    }
                }

                question_title.text = question.question


                if(selection[field] >= 0){
                    answers.check(radioButtons[selection[field]].id)
                }else{
                    answers.clearCheck()
                }
            }
        }

        init {
            answers.setOnCheckedChangeListener { _, id ->
                selection[index] = radioButtons.indexOfFirst { it.id == id }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return QuestionViewHolder((view))
    }


    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.index = position
    }
}