package kr.co.ganeg.introducemarbleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.ganeg.introducemarbleapp.databinding.FragmentCharacterListBinding

class CharacterListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        return binding.root
    }
}
