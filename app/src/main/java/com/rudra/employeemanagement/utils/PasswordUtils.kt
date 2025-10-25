package com.rudra.employeemanagement.utils

import at.favre.lib.crypto.bcrypt.BCrypt

object PasswordUtils {
    fun generatePassword(length: Int = 10): String {
        val allowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+"
        return (1..length).map { allowed.random() }.joinToString("")
    }

    fun hashPassword(plain: String): String {
        return BCrypt.withDefaults().hashToString(12, plain.toCharArray())
    }

    fun verify(plain: String, hash: String): Boolean {
        val result = BCrypt.verifyer().verify(plain.toCharArray(), hash)
        return result.verified
    }
}
