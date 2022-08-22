package com.nasa.nasaimages.data.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.data.models.NasaImagesResponseModelItem
import com.nasa.nasaimages.databinding.ImagesRowItemBinding
import com.nasa.nasaimages.utils.load


class ImagesListAdapter(private val list: MutableList<NasaImageEntity>) :
    RecyclerView.Adapter<NasaImageViewHolder>() {

    var itemClick: ((NasaImageEntity, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaImageViewHolder {
        return NasaImageViewHolder(
            ImagesRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NasaImageViewHolder, position: Int) {
        with(holder.viewItem) {
            with(list[position]) {
                //nasaImageTitle.text = title
                nasaImageImg.load(imgUrl)
            }
        }

        holder.viewItem.view1.setOnClickListener {
            itemClick?.invoke(list[position], position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}


class NasaImageViewHolder(val viewItem: ImagesRowItemBinding) : RecyclerView.ViewHolder(viewItem.root) {

}