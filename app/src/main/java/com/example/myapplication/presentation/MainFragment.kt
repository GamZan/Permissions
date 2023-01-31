package com.example.myapplication.presentation

import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.MainDb
import com.example.myapplication.data.PhotoInfo
import com.example.myapplication.databinding.FragmentMainBinding
import kotlin.concurrent.thread

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: MainDb

    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            Toast.makeText(requireContext(), "permissions is $isGranted", Toast.LENGTH_SHORT).show()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MainDb.getDb(requireContext())
        checkPermissions()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoList = db.photoDao().getAll()
        val mainAdapter = MainAdapter(photoList)
        binding.recyclerView.adapter = mainAdapter




        binding.goToCameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(requireContext(), "Permissions is Granted", Toast.LENGTH_SHORT).show()
        } else {
            launcher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

//    private fun photoFromDb(): List<PhotoInfo>  {
//        val listPhoto = mutableListOf<PhotoInfo>()
//        thread {
//            listPhoto.addAll(db.photoDao().getAll())
//        }
//        return listPhoto
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}