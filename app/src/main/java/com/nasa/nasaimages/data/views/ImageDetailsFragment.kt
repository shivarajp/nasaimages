package com.nasa.nasaimages.data.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maxkeppeler.sheets.info.InfoSheet
import com.nasa.nasaimages.R
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.data.views.adapters.ImagesDetailsListAdapter
import com.nasa.nasaimages.data.views.adapters.ImagesListAdapter
import com.nasa.nasaimages.databinding.FragmentImageDetailsBinding
import com.nasa.nasaimages.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.FieldPosition


@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    private var _binding: FragmentImageDetailsBinding? = null
    private val viewModel by viewModels<HomeViewModel>()

    private val binding get() = _binding!!
    private var position = 0
    var imagesList = mutableListOf<NasaImageEntity>()
    lateinit var adapter: ImagesDetailsListAdapter

    private val args: ImageDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        position = args.position

        adapter = ImagesDetailsListAdapter(imagesList, requireActivity())
        binding.viewpager.adapter = adapter

        viewModel.getImagesFromLocalDb().observe(viewLifecycleOwner, Observer {

            imagesList.addAll(it)
            adapter.notifyDataSetChanged()
            binding.viewpager.currentItem = position

        })

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}