package com.iruda.numnum.presentation.views

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iruda.numnum.R
import com.iruda.numnum.databinding.FragmentGameBinding
import com.iruda.numnum.domain.entities.GameResult
import com.iruda.numnum.domain.entities.Level
import com.iruda.numnum.presentation.viewmodels.GameViewModel

class GameFragment : Fragment() {

    private lateinit var level: Level
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.textViewOption1)
            add(binding.textViewOption2)
            add(binding.textViewOption3)
            add(binding.textViewOption4)
            add(binding.textViewOption5)
            add(binding.textViewOption6)
        }
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenersToOptions()
        viewModel.startGame(level)
    }

    private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel() {
        viewModel.question.observe(viewLifecycleOwner) {
            binding.apply {
                textViewSum.text = it.sum.toString()
                textViewLeftNumber.text = it.visibleNumber.toString()
                for (i in 0 until tvOptions.size) {
                    tvOptions[i].text = it.options[i].toString()
                }
            }
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.apply {
                progressBar.setProgress(it, true)
            }
        }
        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
            binding.apply {
                textViewAnswersProgress.setTextColor(getColorByState(it))
            }
        }
        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.apply {
                progressBar.progressTintList = ColorStateList.valueOf(color)
            }
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.apply {
                textViewTimer.text = it
            }
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.apply {
                progressBar.secondaryProgress = it
            }
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            binding.apply {
                launchGameFinishedFragment(it)
            }
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.apply {
                textViewAnswersProgress.text = it
            }
        }
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    companion object {

        const val STACK_NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}