package com.fake.simplicity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fake.simplicity.adapters.ElementsAdapter
import com.fake.simplicity.viewmodels.ElementsViewModel
import com.fake.simplicity.MainActivity
import com.fake.simplicity.databinding.FragmentElementsBinding

class ElementsFragment: Fragment() {

    private lateinit var mBinding: FragmentElementsBinding
    private lateinit var elementsViewModel: ElementsViewModel

    private var mAdapter: ElementsAdapter<*>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentElementsBinding.inflate(inflater,container,false)
        initList()
        elementsViewModel = ViewModelProvider(requireActivity()).get(ElementsViewModel::class.java)
        elementsViewModel.elements.observe(requireActivity(), Observer {
            (mAdapter as ElementsAdapter<Any>).submitList(it)
        })
        return mBinding.root
    }

    private fun initList() {
        mAdapter =
            ElementsAdapter<Any>(requireActivity() as MainActivity)
        val layoutManager = LinearLayoutManager(context)
        mBinding.elementsList.layoutManager = layoutManager
        mBinding.elementsList.adapter = mAdapter
    }
}