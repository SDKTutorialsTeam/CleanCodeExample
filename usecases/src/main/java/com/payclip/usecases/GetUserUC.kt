package com.payclip.usecases

import com.payclip.data.repository.UserRepository

class GetUserUC(private val userRepository: UserRepository) {

    operator fun invoke() =
        userRepository.getUser()

}
