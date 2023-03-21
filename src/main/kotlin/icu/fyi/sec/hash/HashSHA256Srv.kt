package icu.fyi.sec.hash

import jakarta.inject.Singleton
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.codec.binary.Hex
import java.security.SecureRandom
@Singleton
class HashSHA256Srv(): HashSrv {
    override fun generateSaltedHash(value: String, saltLength: Int): SaltHash {
        val salt = SecureRandom.getInstance("SHA1PRNG", "SUN").generateSeed(saltLength)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$saltAsHex$value")
        return SaltHash(
            hash = hash,
            salt = saltAsHex
        )
    }

    override fun verify(value: String, saltHash: SaltHash): Boolean {
        return DigestUtils.sha256Hex(saltHash.salt + value) == saltHash.hash
    }
}