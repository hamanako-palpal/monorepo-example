package com.pal2hmnk.example.gateway.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.pal2hmnk.example.gateway.domains.usecaes.SignUp
import com.pal2hmnk.example.generated.graphql.types.User

@DgsComponent
class UserMutations(
    private val scenario: SignUp,
) {
    @DgsMutation
    fun signUp(
        @InputArgument userName: String,
        @InputArgument password: String,
        @InputArgument email: String,
    ): User =
        scenario.exec(userName, password, email).let {
            User(it.id.value, it.name)
        }
}
