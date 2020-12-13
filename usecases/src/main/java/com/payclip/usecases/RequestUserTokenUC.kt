package com.payclip.usecases

import com.payclip.data.repository.UserRepository
import com.payclip.domain.Access
import kotlinx.coroutines.flow.Flow

class RequestUserTokenUC(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<Access> = userRepository.requestUserToken()
}
