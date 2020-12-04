package com.payclip.usecases

import com.payclip.data.repository.UserRepository
import com.payclip.domain.User

class UpdateUserUC(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User) =
        userRepository.updateUser(user)

}
