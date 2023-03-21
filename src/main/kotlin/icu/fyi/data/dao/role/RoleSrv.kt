package icu.fyi.data.dao.role

import jakarta.inject.Singleton
import java.util.*

@Singleton
class RoleSrv(private val repo: RoleRepo) {
    fun create(name: String): Role {
        return repo.save(Role(name = name))
    }
    fun findById(id: Long): Role {
        return repo.findById(id).get()
    }
    fun findAll(): List<Role> {
        return repo.findAll().toList()
    }
    fun findByName(name: String): Role {
        return repo.findByName(name)
    }
}