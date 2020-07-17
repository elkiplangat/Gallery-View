//package com.elkiplangat.galleryview.ui
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.elkiplangat.galleryview.R
//import kotlinx.android.synthetic.main.gallery_layout.view.*
//
///*class ImageClickListener(onLongClick:(itemId:Long)->Unit){
//    fun onClick(image:MediaStoreImage) =  onLongClick(image.id)
//    }
//}*/
//
//
//
//class ImagesRecyclerAdapter: ListAdapter<MediaStoreImage, ImagesRecyclerAdapter.ImagesViewHolder>(MediaStoreImage.DiffCallback) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater.inflate(R.layout.gallery_layout, parent, false)
//        return ImagesViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
//        val mediaStoreImage = getItem(position)
//        holder.rootview.tag = mediaStoreImage
//        Glide.with(holder.imageView)
//            .load(mediaStoreImage.contentUri)
//            .thumbnail(0.33f)
//            .centerCrop()
//            .into(holder.imageView)
//
//
//    }
//
//    inner class ImagesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
//        val rootview = itemView
//        val imageView: ImageView = itemView.imageView
//        val cardView = itemView.cardView
//        init {
//            cardView.setOnLongClickListener {
//                cardView.isChecked = !(cardView.isChecked)
//                Log.d("Click", "OnLongClick")
//                true
//            }
//        }
//
//    }
//
//
//
//}
