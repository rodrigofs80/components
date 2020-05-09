package br.com.components.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Botao(val id: Int, val descricao: String) : Parcelable