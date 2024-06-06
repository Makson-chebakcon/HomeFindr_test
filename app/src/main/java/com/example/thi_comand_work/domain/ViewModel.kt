import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TokenViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("saveData", Context.MODE_PRIVATE)

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    init {
        _token.value = getTokenFromPreferences()
    }

    fun saveToken(token: String) {
        _token.value = token
        saveTokenToPreferences(token)
    }

    private fun saveTokenToPreferences(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    private fun getTokenFromPreferences(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    fun clearToken() {
        _token.value = null
        sharedPreferences.edit().remove("auth_token").apply()
    }
}
