package com.kinsidelibs.find_combination.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.kinsidelibs.find_combination.adapter.ResultAdapter
import com.kinsidelibs.find_combination.adapter.ValuesAdapter
import com.kinsidelibs.find_combination.core.BaseActivity
import com.kinsidelibs.find_combination.databinding.ActivityMainBinding
import com.kinsidelibs.find_combination.listener.OnDeleteValueListener
import com.kinsidelibs.find_combination.view_model.MainActivityViewModel

class MainActivity : BaseActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _viewModel: MainActivityViewModel

    // adapters
    private lateinit var _valueAdapter: ValuesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setContentView(_binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(_binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    private fun init() {
        //config
        disableNightMode()
        //core
        setValuesAdapter()
        //observe
        observers()
        //events
        eventAddButtonClick()
        eventOnDeleteValueOnClick()
        eventToolBarClearButtonClick()
        eventOnTargetValueChange()

    }

    private fun setValuesAdapter() {
        _valueAdapter = ValuesAdapter(this, _viewModel.inputValues)
        _binding.valuesRecycleView.adapter = _valueAdapter
    }

    private fun observers() {
        _viewModel.inputCounter.observe(this) {
            _binding.inputCounter.text = _viewModel.getInputValueCounter(this)
        }
        _viewModel.combinationCounter.observe(this) {
            _binding.combinationsTextView.text = _viewModel.getCombinationsCounter(this)
        }
        _viewModel.resultValues.observe(this) {
            _binding.resultRecycleView.adapter =
                ResultAdapter(this, _viewModel.resultValues.value!!)
        }
    }

    /*##### Events #####*/
    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun eventAddButtonClick() {
        _binding.addImageButton.setOnClickListener {
            val inputValue = _binding.numberEditText.text
            if (inputValue.isNotBlank()) {
                _viewModel.addInputValue(inputValue.toString().toInt())
                _binding.valuesRecycleView.adapter?.notifyDataSetChanged()
                _binding.valuesRecycleView.scrollToPosition(0)
                _viewModel.changeInputValueCounter()
                _binding.numberEditText.text.clear()
                calculate()
            }

        }
    }

    private fun eventOnDeleteValueOnClick() {
        _valueAdapter.onDeleteItem(object : OnDeleteValueListener {
            override fun onDeleteValue(position: Int, itemsSize: Int) {
                _viewModel.removeInputValue(position)
                _viewModel.changeInputValueCounter()
                _valueAdapter.notifyItemRemoved(position)
                _valueAdapter.notifyItemRangeChanged(position, itemsSize - position)
                calculate()
            }
        })
    }

    private fun eventToolBarClearButtonClick() {
        _binding.toolBar.menu[0].setOnMenuItemClickListener {
            Log.d("debug", "ok")
            _viewModel.resetInputCounter()
            _viewModel.resetCombinationCounter()
            _viewModel.clearInputValues()
            _viewModel.clearResultValues()
            _binding.numberEditText.text.clear()
            _binding.targetEditText.text.clear()
            _binding.valuesRecycleView.adapter = _valueAdapter
            true
        }
    }

    private fun eventOnTargetValueChange() {
        _binding.targetEditText.doOnTextChanged { text, start, before, count ->
            calculate()
        }

    }

    private fun calculate() {
        val target = _binding.targetEditText.text
        if (target.isNotBlank()) {
            _viewModel.calc(target.toString().toInt())
        } else {
            _viewModel.calc(0)
        }
        _viewModel.changeCombinationsCounter()
    }
}