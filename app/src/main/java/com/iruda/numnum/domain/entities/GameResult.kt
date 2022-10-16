package com.iruda.numnum.domain.entities

data class GameResult(
    val isWin: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)