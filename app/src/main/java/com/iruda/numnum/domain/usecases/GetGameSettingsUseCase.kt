package com.iruda.numnum.domain.usecases

import com.iruda.numnum.domain.entities.GameSettings
import com.iruda.numnum.domain.entities.Level
import com.iruda.numnum.domain.repositories.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}