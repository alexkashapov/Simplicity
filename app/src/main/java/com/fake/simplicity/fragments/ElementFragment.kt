package com.fake.simplicity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fake.simplicity.databinding.FragmentElementBinding
import com.fake.simplicity.utils.creators.ViewCreator
import com.fake.simplicity.viewmodels.ElementViewModel

class ElementFragment : Fragment() {

    lateinit var mBinding: FragmentElementBinding
    lateinit var elementViewModel: ElementViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentElementBinding.inflate(inflater, container, false)
        elementViewModel = ViewModelProvider(requireActivity()).get(ElementViewModel::class.java)
        elementViewModel.element.observe(requireActivity(), Observer {
            val views = ViewCreator.createViews(requireActivity(), it)
            views.forEach { view ->
                mBinding.fields.addView(view)
            }
        })
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        elementViewModel.element.removeObservers(requireActivity())
    }
}