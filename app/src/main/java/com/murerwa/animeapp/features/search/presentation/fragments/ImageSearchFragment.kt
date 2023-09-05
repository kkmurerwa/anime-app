package com.murerwa.animeapp.features.search.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.murerwa.animeapp.R
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.core.utils.convertToMultipart
import com.murerwa.animeapp.databinding.FragmentImageSearchBinding
import com.murerwa.animeapp.features.search.presentation.adapters.ImageSearchResultsAdapter
import com.murerwa.animeapp.features.search.presentation.viewmodels.ImageSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MultipartBody
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ImageSearchFragment : Fragment(R.layout.fragment_image_search) {
    @Inject
    lateinit var viewModel: ImageSearchViewModel

    private var _binding: FragmentImageSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentImageSearchBinding.bind(requireView())

        bindViews()
    }

    private fun bindViews() {
        binding.apply {
            buttonSelectImage.setOnClickListener {
                imageSelector()
            }
        }
    }

    private fun imageSelector() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            this.type = "image/*"
        }

        resultLauncherFile.launch(intent)
    }

    private val resultLauncherFile = this.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val extras: Bundle? = data?.extras

            val fileUri = data?.data!!

            // For null safety, make sure data is not null
            if (data?.data == null && extras?.isEmpty == true) {
                Timber.d("File Uri and extras returned is null")
                return@registerForActivityResult
            }

            val selectedImageBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        requireContext().contentResolver,
                        fileUri
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, fileUri)
            }

            val imageMultipart = selectedImageBitmap.convertToMultipart(requireContext())

            searchImage(imageMultipart)
        }
    }

    private fun searchImage(image: MultipartBody.Part) {
        viewModel.searchWithImage(image)

        viewModel.searchResults.observe(viewLifecycleOwner) { uistate ->
            when(uistate) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    Timber.d("Request Success: ${uistate.value}")
                    val adapter = ImageSearchResultsAdapter(
                        uistate.value
                    )
                    binding.recyclerViewSearchResults.adapter = adapter
                }
                is UIState.Error -> {
                    Timber.d("Request failed")
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }

}