package com.payclip.usecases

import com.payclip.data.repository.UserRepository

class ExistUserUC(private val userRepository: UserRepository) {

    suspend operator fun invoke() =
        userRepository.existUser()

}