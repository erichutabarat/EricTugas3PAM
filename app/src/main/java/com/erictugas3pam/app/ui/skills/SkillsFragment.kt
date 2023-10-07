package com.erictugas3pam.app.ui.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erictugas3pam.app.R
import com.erictugas3pam.app.databinding.FragmentSkillsBinding
import java.util.Locale

class SkillsFragment : Fragment() {

    private var _binding: FragmentSkillsBinding? = null
    private val binding get() = _binding!!
    val data = ArrayList<itemsViewModel>()
    val adapter = CustomAdapter(data)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val skillsViewModel =
            ViewModelProvider(this).get(SkillsViewModel::class.java)

        _binding = FragmentSkillsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView = binding.textforskills


        val dataSkill = resources.getStringArray(R.array.daftarSkill)
        val dataPengalaman = resources.getIntArray(R.array.pengalamanSkill)

        val recyclerview : RecyclerView = root.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        for(i in 0 until dataSkill.size){
            val data_pengalaman = dataPengalaman.get(i).toString()
            data.add(itemsViewModel(dataSkill.get(i).toString(), "$data_pengalaman  Tahun"))
        }
        recyclerview.adapter = adapter
        val searchViewID : SearchView = root.findViewById(R.id.searchViewID)
        searchViewID.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // Inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })


        skillsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun filter(text: String){
        val filteredlist : ArrayList<itemsViewModel> = ArrayList()
        for(item in data){
            if(item.skillnama.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))){
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Data Found..", Toast.LENGTH_SHORT).show()
            activity?.runOnUiThread {
                adapter.filterList(filteredlist)
            }
        }

    }

}
