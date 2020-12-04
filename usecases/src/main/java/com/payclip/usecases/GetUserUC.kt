package com.payclip.usecases

import com.payclip.data.repository.UserRepository

class GetUserUC(private val userRepository: UserRepository) {

    suspend operator fun invoke() =
        userRepository.getUser()

}
