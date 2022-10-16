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

    companion object {
        private const val TAG = "CharacterAdapter"
    }

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

    //FIXME. [BUG] 아이템 4개만 반복해서 나옴 > 원인: 해당 콜백이 4번만 불린다. 그 다음 스크롤 시 나오는 아이템들은 해당 콜백이 호출되지 않고, 기존에 있던 정보를 재활용한다.
    //TODO. 스크롤 할 때 마다, 해당 함수가 호출되게 한다. && 문제요구사항인 스크롤할 때마다 20개의 데이터를 호출하는 것도 추가해야 됨 !
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        Log.e(TAG, "onBindViewHolder($position) called!!!!!!!!!!")
        Log.d(TAG, "mItems: $mItems")    //mItems 에 값이 잘 들어옴
        val character = getItem(position)
        Log.i(TAG, "character: $character")
        if (character != null) {
            holder.bind(character)
        }
    }

    private fun getItem(position: Int): CharacterModel? {
        if (mItems == null) {
            return null
        }
        return mItems[position]
    }

    fun setItems(results: ArrayList<CharacterModel>) {
        if (results == null) {
            Log.e(TAG, "setItems result is null")
            return
        }
        Log.d(TAG, "response.data.results to collection: $results")

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
                Log.e(TAG, "character: $character")
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
