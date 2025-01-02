package org.example.pigeon.core.utils

import com.google.common.hash.Hashing
import java.nio.charset.StandardCharsets
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


object AesEncryption {

    enum class Mode(val iterations: Int) { FAST(16), NORMAL(512), STRONG(2048) }

    fun encryptECB(
        textAsBytes: ByteArray,
        password: String,
        salt: ByteArray,
        mode: Mode
    ): ByteArray {
        val key = getKeyFromPassword(password, salt, mode)
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        return cipher.doFinal(textAsBytes)
    }

    fun encryptCBC(
        textAsBytes: ByteArray,
        password: String,
        iv: ByteArray,
        salt: ByteArray,
        mode: Mode
    ): ByteArray {
        val ivSpec = IvParameterSpec(iv)
        val key = getKeyFromPassword(password, salt, mode)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)
        return cipher.doFinal(textAsBytes)
    }

    fun decryptECB(
        cipherBytes: ByteArray,
        password: String,
        salt: ByteArray,
        mode: Mode
    ): ByteArray {
        val key = getKeyFromPassword(password, salt, mode)
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key)
        return cipher.doFinal(cipherBytes)
    }


    fun decryptCBC(
        cipherBytes: ByteArray,
        password: String,
        iv: ByteArray,
        salt: ByteArray,
        mode: Mode
    ): ByteArray {
        val ivSpec = IvParameterSpec(iv)
        val key = getKeyFromPassword(password, salt, mode)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)
        return cipher.doFinal(cipherBytes)
    }

    fun decryptEcbFromBase64(
        base64: String,
        password: String,
        salt: ByteArray,
        mode: Mode = Mode.NORMAL
    ): ByteArray =
        decryptECB(Base64.getDecoder().decode(base64), password, salt, mode)

    fun decryptCbcFromBase64(
        base64: String,
        password: String,
        iv: ByteArray,
        salt: ByteArray,
        mode: Mode = Mode.NORMAL
    ): ByteArray =
        decryptCBC(Base64.getDecoder().decode(base64), password, iv, salt, mode)

    private fun getKeyFromPassword(password: String, salt: ByteArray, mode: Mode): SecretKey {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt, mode.iterations, 256)
        return SecretKeySpec(factory.generateSecret(spec).encoded, "AES")
    }

    fun generateSaltFromPassword(password: String): ByteArray {
        return password.reversed().toByteArray()
    }

    fun generateIvFromPassword(password: String): ByteArray {
        val hash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).asBytes()
        return hash.copyOf(16)
    }

}
