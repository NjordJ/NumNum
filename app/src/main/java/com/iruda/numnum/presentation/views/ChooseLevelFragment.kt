package com.iruda.numnum.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iruda.numnum.R
import com.iruda.numnum.databinding.FragmentChooseLevelBinding
import com.iruda.numnum.domain.entities.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonTestLevel.setOnClickListener { launchLevel(Level.TEST) }
            buttonEasyLevel.setOnClickListener { launchLevel(Level.EASY) }
            buttonNormalLevel.setOnClickListener { launchLevel(Level.NORMAl) }
            buttonHardLevel.setOnClickListener { launchLevel(Level.HARD) }

        }
    }

    private fun launchLevel(difficulty: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(difficulty))
            .addToBackStack(GameFragment.STACK_NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val STACK_NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}