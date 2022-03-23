package com.example.mvvm_aplication.data.storage.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo

@Entity(tableName = "users")
data class User(
    val login: String,
    val email: String = "",
    val userName: String,
    val photoEtag: ByteArray = byteArrayOf(),
    val presenceId: Int? = 0,
    val presenceUpdatedAt: Long? = 0,
    val avatarColor: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (login != other.login) return false
        if (email != other.email) return false
        if (userName != other.userName) return false
        if (!photoEtag.contentEquals(other.photoEtag)) return false
        if (presenceId != other.presenceId) return false
        if (presenceUpdatedAt != other.presenceUpdatedAt) return false
        if (avatarColor != other.avatarColor) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = login.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + photoEtag.contentHashCode()
        result = 31 * result + (presenceId ?: 0)
        result = 31 * result + (presenceUpdatedAt?.hashCode() ?: 0)
        result = 31 * result + avatarColor
        result = 31 * result + id
        return result
    }

    companion object {
        fun map(userRegisterInfo: UserRegisterInfo): User = User(
            login = userRegisterInfo.login,
            email = userRegisterInfo.email,
            userName = userRegisterInfo.userName)
    }
}