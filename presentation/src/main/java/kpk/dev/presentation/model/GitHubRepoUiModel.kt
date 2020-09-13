package kpk.dev.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kpk.dev.domain.entity.CommitItem
import kpk.dev.presentation.adapter.OnClickActionModel

@Parcelize
data class GitHubRepoUiModel (
    val id: Long,
    val name: String,
    val private: Boolean,

    //Rainbow Rising!!!
    val stargazers: Int,
    val watchers: Int,
    val forks: Int,
    val language: String,
    var commits:List<CommitUiModel>,
    var image:String,
    var ownerName: String
) : OnClickActionModel(), Parcelable