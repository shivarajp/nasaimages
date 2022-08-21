package com.nasa.nasaimages.data.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nasa.nasaimages.R
import com.nasa.nasaimages.data.remote.Status
import com.nasa.nasaimages.databinding.HomeFragmentBinding
import com.nasa.nasaimages.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun loadData() {

        viewModel.imagesObserver.observe(viewLifecycleOwner, Observer {
            when (it.status) {

                Status.SUCCESS -> {
                    requireActivity().toast(it.data.toString())
                }

                Status.ERROR -> {
                    requireActivity().toast(it.message!!)
                }

                Status.LOADING -> {
                    requireActivity().toast("Loading..")

                }
            }
        })

        viewModel.getImages()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}