package com.elkiplangat.galleryview.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elkiplangat.galleryview.R
import com.elkiplangat.galleryview.databinding.GalleryLayoutBinding


/*This is another adapter that extends RecyclerView.Adapter rather than ListView Adapter*/

class ImagesDiffCallBack : DiffUtil.ItemCallback<MediaStoreImage>() {
    override fun areItemsTheSame(oldItem: MediaStoreImage, newItem: MediaStoreImage): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MediaStoreImage, newItem: MediaStoreImage): Boolean {
        return  oldItem == newItem
    }
}

class ImagesAdapterRecycler : ListAdapter<MediaStoreImage, ImagesAdapterRecycler.MyViewHolder>(ImagesDiffCallBack()) {
    private lateinit var binding: GalleryLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.gallery_layout, parent, false)
        // val view = layoutInflater.inflate(R.layout.gallery_layout, parent, false)
        return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val item = getItem(position)
        holder.rootview.tag = item
        Glide.with(holder.imageView).load(item.contentUri).thumbnail(0.33f).into(holder.imageView)
    }

    inner class MyViewHolder(binding: GalleryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rootview = itemView
        val imageView = binding.imageView
        val cardView = binding.cardView

        init {
            cardView.setOnLongClickListener {

                cardView.isChecked = !(cardView.isChecked)


                Log.d("ClickListener", "Item Long Clicked")
                true
            }
        }
    }
}
