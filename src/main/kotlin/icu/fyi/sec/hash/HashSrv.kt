package icu.fyi.sec.hash

interface HashSrv {
    fun generateSaltedHash(value: String, saltLength: Int = 32): SaltHash
    fun verify(value: String, saltHash: SaltHash): Boolean
}