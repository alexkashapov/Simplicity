package com.fake.simplicity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fake.simplicity.adapters.ElementCallback
import com.fake.simplicity.data.Event
import com.fake.simplicity.data.Move
import com.fake.simplicity.data.Notice
import com.fake.simplicity.databinding.ActivityMainBinding
import com.fake.simplicity.fragments.ElementFragment
import com.fake.simplicity.fragments.ElementsFragment
import com.fake.simplicity.utils.creators.RandomCreator
import com.fake.simplicity.utils.randomEvent
import com.fake.simplicity.utils.randomMove
import com.fake.simplicity.utils.randomNotice
import com.fake.simplicity.viewmodels.ElementViewModel
import com.fake.simplicity.viewmodels.ElementsViewModel
import java.util.*

class MainActivity : AppCompatActivity(),
    ElementCallback {

    private lateinit var mBinding: ActivityMainBinding
    private val randomInstance: Random by lazy {
        Random()
    }

    private val elementsViewModel: ElementsViewModel by viewModels()
    private val elementViewModel: ElementViewModel by viewModels()
    private var openedIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val randomCreator =
            RandomCreator(hashMapOf(
                Pair(Event::class.java, { randomInstance.randomEvent() }),
                Pair(Move::class.java, { randomInstance.randomMove() }),
                Pair(Notice::class.java, { randomInstance.randomNotice() }
                )))
        if (savedInstanceState == null) {
            elementsViewModel.generateRandomData(
                randomCreator,
                listOf(Event::class.java, Move::class.java, Notice::class.java)
            )
            openedIndex = -1
        }
        supportFragmentManager.beginTransaction().replace(mBinding.container.id, ElementsFragment())
            .commit()
        if (savedInstanceState != null) {
            openedIndex = savedInstanceState.getInt("POSITION", -1)
            if (openedIndex >= 0) {
                openElementInfo(openedIndex)
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            openedIndex = -1
            supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("POSITION", openedIndex)
        super.onSaveInstanceState(outState)
    }

    override fun openElementInfo(position: Int) {
        openedIndex = position
        supportFragmentManager.beginTransaction().replace(mBinding.container.id, ElementFragment())
            .addToBackStack("elementFragment").commit()
        elementViewModel.postElement(position)
    }
}