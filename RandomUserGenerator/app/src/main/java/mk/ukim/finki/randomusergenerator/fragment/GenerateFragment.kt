package mk.ukim.finki.randomusergenerator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import mk.ukim.finki.randomusergenerator.R
import mk.ukim.finki.randomusergenerator.databinding.FragmentGenerateBinding
import mk.ukim.finki.randomusergenerator.viewmodel.UsersViewModel
import mk.ukim.finki.randomusergenerator.viewmodel.UsersViewModelFactory
import kotlin.getValue

class GenerateFragment: Fragment() {

  private var _binding: FragmentGenerateBinding? = null
  private val binding get() = _binding!!

  private val usersViewModel: UsersViewModel by activityViewModels {
    UsersViewModelFactory(requireContext())
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentGenerateBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnGenerate.setOnClickListener {
      usersViewModel.generateUser()
    }

    usersViewModel.latestGeneratedUser.observe(viewLifecycleOwner) { user ->
      binding.randomUserName.text = user?.email ?: ""

     Glide.with(binding.randomUserAvatar)
        .load(user.picture.large)
        .centerCrop()
        .into(binding.randomUserAvatar)
    }


    binding.btnViewAll.setOnClickListener {
      parentFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container_view, RandomUsersFragment())
        .addToBackStack(null)
        .commit()
    }
  }



  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}