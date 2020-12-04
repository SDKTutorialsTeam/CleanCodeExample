package com.payclip.usecases

import com.payclip.data.repository.UserRepository

class RemoveUserUC(private val userRepository: UserRepository) {

    suspend operator fun invoke() =
        userRepository.removeUser()

}
