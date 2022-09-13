package kr.co.ganeg.introducemarvelapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.ganeg.introducemarvelapp.adapter.CharacterAdapter
import kr.co.ganeg.introducemarvelapp.data.CharacterData
import kr.co.ganeg.introducemarvelapp.databinding.FragmentCharacterListBinding

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    companion object {
        private const val TAG = "CharacterListFragment"
    }

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharacterListViewModel
    private val adapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "$TAG onCreated")

        viewModel = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        viewModel.getCharacterResponseLiveData().observe(this, Observer { response ->
            if (response != null) {
                Log.d(TAG, "response: $response")
                Log.i(TAG, "entries: ${response.entries[0]}")
                adapter.setItems(response.entries.toCollection(ArrayList()))
                //binding.kidsCafeResultTextView.text = GsonBuilder().setPrettyPrinting().create().toJson(response)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        context ?: return binding.root

        binding.rvCharacterList.layoutManager = LinearLayoutManager(context)
        binding.rvCharacterList.setHasFixedSize(true)
        binding.rvCharacterList.adapter = adapter

        viewModel.getCharacterList(null, 0)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        //TODO. item 눌렀을 때, 리스너 설정 or adapter 에 설정해줘야 할지도?
//        binding.searchBtn.setOnClickListener {
//            viewModel.searchKidsCafe()
//        }
    }
}
