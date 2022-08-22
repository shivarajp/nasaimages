package com.nasa.nasaimages.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.views.adapters.ImagesListAdapter
import com.nasa.nasaimages.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel by viewModels<HomeViewModel>()
    lateinit var adapter: ImagesListAdapter
    var imagesList = mutableListOf<NasaImageEntity>()

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
        adapter = ImagesListAdapter(imagesList)

        binding.recyclerview.layoutManager = GridLayoutManager(requireActivity(), 2)
        adapter = ImagesListAdapter(imagesList)
        binding.recyclerview.adapter = adapter

        adapter.itemClick = { data, position ->
            val action = HomeFragmentDirections.actionFirstFragmentToSecondFragment()
            action.position = position
            findNavController().navigate(action)
        }

    }

    private fun loadData() {
        viewModel.getImagesFromApi().observe(viewLifecycleOwner, Observer {

            it?.let {
                imagesList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}