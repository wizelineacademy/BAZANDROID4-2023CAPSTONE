package mx.jramon.subias.dbmovieproyect.movies.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Tkn(
    @SerializedName("success") @Expose val success:Boolean,
    @SerializedName("expires_at") @Expose val expireAt:String,
    @SerializedName("request_token") @Expose val requestToken:String,
)

data class Session(
    @SerializedName("success") @Expose val success:Boolean,
    @SerializedName("session_id") @Expose val idSession:String,
)