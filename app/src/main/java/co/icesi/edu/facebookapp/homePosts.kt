package co.icesi.edu.facebookapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.icesi.edu.facebookapp.databinding.FragmentHomePostsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [homePosts.newInstance] factory method to
 * create an instance of this fragment.
 */
class homePosts : Fragment() {
    private var _binding: FragmentHomePostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePostsBinding.inflate(inflater, container, false)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())


        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = homePosts()
    }
}