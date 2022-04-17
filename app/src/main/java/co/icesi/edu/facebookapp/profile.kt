package co.icesi.edu.facebookapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import co.icesi.edu.facebookapp.databinding.FragmentProfileBinding


/**
 * A simple [Fragment] subclass.
 * Use the [profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class profile : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentProfileBinding? = null
    private val  binding get() = _binding!!
    private var permissionGranted = false



    private val startForActivityGallery=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){rs->
        if(rs.resultCode== Activity.RESULT_OK){
            val photo = rs.data?.data!!
            binding.profileImgInEdit.setImageURI(photo)
        }
    }

    private val requestPermissionLauncher=registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){granted->
        if(granted){
            selectPhoto()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==1){
            var allGrand = true
            for(result in grantResults){
                if(result == PackageManager.PERMISSION_DENIED){
                    allGrand = false
                    break
                }
            }
            permissionGranted = allGrand
        }
    }

    private fun selectPhoto(){
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        intent.type="image/*"
        startForActivityGallery.launch(intent)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProfileBinding.inflate(inflater, container, false)
        binding.editProfBtn.setOnClickListener{

            requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            if (permissionGranted){
                binding.confirmEdit.visibility = View.VISIBLE
                binding.editProfilePhoto.visibility = View.VISIBLE

                binding.confirmEdit.setOnClickListener{

                    binding.editProfilePhoto.visibility = View.INVISIBLE
                    binding.confirmEdit.visibility = View.INVISIBLE


                    binding.editProfilePhoto.setOnClickListener{

                        when{
                            ContextCompat.checkSelfPermission(requireActivity().applicationContext,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED->{
                                selectPhoto()
                            }
                            else->{
                                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            }
                        }
                            selectPhoto()

                    }
                }
            }


        }
        binding.logOutBtn.setOnClickListener{
            val intent= Intent(context,Login ::class.java)
            startActivity(intent)
        }
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = profile()
    }



}