package kr.co.ganeg.introducemarvelapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.ganeg.introducemarvelapp.databinding.ListItemCharacterBinding
import kr.co.ganeg.introducemarvelapp.model.CharacterModel

class CharacterAdapter() :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(
    ) {

    private var mItems: MutableList<CharacterModel> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ListItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        if (character != null) {
            holder.bind(character)
        }

    }

    fun getItem(position: Int): CharacterModel? {
        if (mItems == null) {
            return null
        }
        return mItems[position]
    }

    fun setItems(results: ArrayList<CharacterModel>) {
        if (results == null) {
            Log.e("CharacterAdapter", "setItems result is null")
            return
        }
        Log.d("CharacterAdapter", "response.data.results to collection: $results")

        mItems.clear()
        mItems.addAll(results)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mItems.count()
    }

    class CharacterViewHolder(
        private val binding: ListItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterModel?) {
            binding.apply {
                character = item
                Log.e("KHJI", "character: $character")
                Glide.with(binding.root.context)
                    .load(character?.thumbnail)
                    .into(binding.characterItemImage)
                executePendingBindings()
            }
        }
    }
}

private class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.detail == newItem.detail
    }
}
