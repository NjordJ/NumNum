package com.iruda.numnum.domain.repositories

import com.iruda.numnum.domain.entities.GameSettings
import com.iruda.numnum.domain.entities.Level
import com.iruda.numnum.domain.entities.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}