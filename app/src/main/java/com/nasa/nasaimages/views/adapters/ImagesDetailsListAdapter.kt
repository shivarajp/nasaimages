package com.nasa.nasaimages.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxkeppeler.sheets.info.InfoSheet
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.databinding.ImagesDetailsRowItemBinding
import com.nasa.nasaimages.utils.load


class ImagesDetailsListAdapter(
    private val list: MutableList<NasaImageEntity>,
    val context: Context
) :
    RecyclerView.Adapter<NasaImageDetailsViewHolder>() {

    var itemClick: ((NasaImageEntity, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaImageDetailsViewHolder {
        return NasaImageDetailsViewHolder(
            ImagesDetailsRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NasaImageDetailsViewHolder, position: Int) {
        with(holder.viewItem) {
            with(list[position]) {
                //nasaImageTitle.text = imageTitle
                nasaImageImg.load(imgUrl)
            }
        }

        /*holder.viewItem.view1.setOnClickListener {
            itemClick?.invoke(list[position], position)
        }*/
        holder.viewItem.nasaImageTitle.setOnClickListener {
            showInfoSheet(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun showInfoSheet(data: NasaImageEntity) {

        InfoSheet().show(context) {
            title("${data.imageTitle}")
            content("Date: ${data.date} \nMedia: ${data.mediaType} \n Copyright: ${data.copyright} \n" +
                    " \n Explanation: ${data.imageExplanation}")

            onNegative("close") {
                
            }

        }
    }
}


class NasaImageDetailsViewHolder(val viewItem: ImagesDetailsRowItemBinding) :
    RecyclerView.ViewHolder(viewItem.root) {

}