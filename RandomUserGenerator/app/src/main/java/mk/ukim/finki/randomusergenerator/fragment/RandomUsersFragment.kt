package mk.ukim.finki.randomusergenerator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import mk.ukim.finki.randomusergenerator.adapter.UserAdapter
import mk.ukim.finki.randomusergenerator.databinding.FragmentGenerateBinding
import mk.ukim.finki.randomusergenerator.databinding.FragmentRandomusersBinding
import mk.ukim.finki.randomusergenerator.viewmodel.UsersViewModel
import mk.ukim.finki.randomusergenerator.viewmodel.UsersViewModelFactory

class RandomUsersFragment: Fragment() {

  private var _binding: FragmentRandomusersBinding? = null
  private val binding get() = _binding!!

  private val usersViewModel: UsersViewModel by activityViewModels {
    UsersViewModelFactory(requireContext())
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentRandomusersBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    var adapter : UserAdapter = UserAdapter()
    binding.list.adapter = adapter

    usersViewModel.users.observe(viewLifecycleOwner) { users ->
      adapter.updateUsers(users)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}