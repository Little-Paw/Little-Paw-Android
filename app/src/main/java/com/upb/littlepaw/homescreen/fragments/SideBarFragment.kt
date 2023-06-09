package com.upb.littlepaw.homescreen.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.upb.littlepaw.LoginActivity
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentSideBarBinding
import com.upb.littlepaw.homescreen.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class SideBarFragment : Fragment(R.layout.fragment_side_bar) {
    lateinit var binding: FragmentSideBarBinding

    private val homeViewModel: HomeViewModel by activityViewModel()

    companion object {
        const val TAG = "SideBarFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSideBarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            homeViewModel.getUser()
            binding.profileSideBarName.text = homeViewModel.user.value?.name
        }

        val navHostFragment =
            parentFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.sideBarButtons.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            homeViewModel.setSideBarOpen(false)
        }

        binding.logOutButton.setOnClickListener {
            activity?.finish()
        }
        homeViewModel.user.observe(viewLifecycleOwner) {
            binding.profileSideBarName.text = it.name
        }

    }
}