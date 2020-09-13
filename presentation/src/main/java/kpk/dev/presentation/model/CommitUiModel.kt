package kpk.dev.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kpk.dev.presentation.adapter.OnClickActionModel

@Parcelize
data class CommitUiModel (
    val author: String,
    val date: String,
    val message: String,
    val hash: String,
    val repoName: String
) : OnClickActionModel(), Parcelable