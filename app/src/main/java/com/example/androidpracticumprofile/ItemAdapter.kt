package com.example.androidpracticumprofile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpracticumprofile.databinding.ItemBinding


class ItemsDiffUtilCallback(
    private val oldItemList: List<ImageInfo>,
    private val newItemList: List<ImageInfo>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItemList.size

    override fun getNewListSize(): Int = newItemList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemList[oldItemPosition]
        val newItem = newItemList[newItemPosition]
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemList[oldItemPosition]
        val newItem = newItemList[newItemPosition]
        return oldItem == newItem
    }

}

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    var itemList: List<ImageInfo> = emptyList()
        set(value) {
            val diffUtilCallback = ItemsDiffUtilCallback(field, value)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
            field = value
            diffUtilResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        with(holder.binding) {
            image.setImageResource(item.imageId)
            title.text = item.text
            val imageView = ImageView(holder.context).apply { setImageResource(item.imageBigId) }
            val dialog = AlertDialog.Builder(holder.context)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .setView(imageView)
                .create()
            image.setOnClickListener {
                dialog.show()
            }
        }
    }

    override fun getItemCount() = itemList.size

    class ItemViewHolder(val binding: ItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)
}