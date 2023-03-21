package icu.fyi.data.dao.user

import icu.fyi.data.dto.req.ResetReq
import icu.fyi.data.dto.res.ApiRes
import icu.fyi.sec.hash.HashSHA256Srv
import icu.fyi.sec.hash.SaltHash

import icu.fyi.data.dao.role.RoleSrv
import jakarta.inject.Singleton
import org.apache.commons.lang3.RandomStringUtils

@Singleton
class UserSrv(
    private val userRepo: UserRepo,
    private val roleSrv: RoleSrv,
    private val hashSrv: HashSHA256Srv,
    ){
    fun reset(resetReq: ResetReq): ApiRes {
        val saltHash = hashSrv.generateSaltedHash(RandomStringUtils.random(20))
        var user = User(
            email = resetReq.email,
            username = resetReq.email,
            name = "",
            password = saltHash.hash,
            salt = saltHash.salt
            )
        println("User $user")
        user = userRepo.save(user)
        val role = roleSrv.findByName("ROLE_USER")
        println("Role $role")
        user.roles.add(role)
        println("User $user")
        userRepo.update(user)
        userRepo.findAll().forEach{user ->
            println("Saved User $user")
        }
        return ApiRes(message = "Reset Request Accepted")
    }

    fun signIn(email: String, password: String): Boolean {
        println("sign in")
        val user = userRepo.findByEmail(email)
        println("user ${user.id}")
        return hashSrv.verify(
            value = password,
            saltHash = SaltHash(
                hash = user.password,
                salt = user.salt
            )
        )
    }

    fun getUser(email: String): User{
        return userRepo.findByEmail(email)
    }
}