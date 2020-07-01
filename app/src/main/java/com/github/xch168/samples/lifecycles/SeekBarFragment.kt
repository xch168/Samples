package com.github.xch168.samples.lifecycles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.github.xch168.samples.R


class SeekBarFragment : Fragment() {

    private lateinit var mSeekBar: SeekBar

    private val mSeekBarViewModel by activityViewModels<SeekBarViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_seek_bar, container, false)
        mSeekBar = root.findViewById(R.id.seekBar)

        subscribeSeekBar();

        return root
    }

    private fun subscribeSeekBar() {
        mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    Log.d("SeekBar", "Progress changed!")
                    mSeekBarViewModel.seekBarValue.value = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        mSeekBarViewModel.seekBarValue.observe(requireActivity(), Observer<Int> {
            if (it != null) {
                mSeekBar.progress = it
            }
        })
    }


}