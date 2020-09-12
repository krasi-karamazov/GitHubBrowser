package kpk.dev.data.api

import kpk.dev.data.api.entity.Dto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiService @Inject constructor() {
    fun getSupportDeskItemsList(): List<Dto.SupportDeskStaffItem>?? {
        return getSupportDeskList()
    }

    private fun getSupportDeskList(): List<Dto.SupportDeskStaffItem>? {
        val jsonString = readJsonFromFile()
        val moshi = Moshi.Builder().build()
        val staffItemsListType: Type = Types.newParameterizedType(
            List::class.java,
            Dto.SupportDeskStaffItem::class.java
        )
        val jsonAdapter: JsonAdapter<List<Dto.SupportDeskStaffItem>> = moshi.adapter(staffItemsListType)
        val list = jsonAdapter.fromJson(jsonString)
        return if (list != null && list.isNotEmpty()) {
           list
        } else {
            emptyList()
        }
    }

    private fun readJsonFromFile(): String {
        val jsonString: StringBuilder = StringBuilder("")
        val bufferedReader = BufferedReader(InputStreamReader(appContext.assets.open("api.json")))
        val readLines = bufferedReader.readLines()
        readLines.forEach {
            jsonString.append(it)
        }
        return jsonString.toString()
    }
}