package com.iruda.numnum.presentation.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.iruda.numnum.R
import com.iruda.numnum.domain.entities.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("rightAnswers")
fun bindRightAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentAnswers")
fun bindRequiredPercentAnswers(textView: TextView, percent: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percent
    )
}

@BindingAdapter("percentRightAnswers")
fun bindPercentRightAnswers(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("isWinner")
fun bindIsWinner(imageView: ImageView, isWin: Boolean) {
    imageView.setImageResource(getImageResId(isWin))
}

private fun getImageResId(isWin: Boolean): Int {
    return if (isWin) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}