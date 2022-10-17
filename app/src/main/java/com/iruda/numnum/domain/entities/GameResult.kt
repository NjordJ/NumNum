package com.iruda.numnum.domain.entities

import java.io.Serializable

data class GameResult(
    val isWin: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
) : Serializable