package com.fftools.selectlanguage.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fftools.selectlanguage.ui.adapter.LanguageAdapter
import com.fftools.selectlanguage.databinding.ActivityLanguageBinding
import com.fftools.selectlanguage.model.language.LanguageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LanguageActivity : AppCompatActivity() {
    private val binding: ActivityLanguageBinding by lazy {
        ActivityLanguageBinding.inflate(layoutInflater)
    }
    private var languageSelected: String? = null
    private val countryAdapter: LanguageAdapter by lazy {
        LanguageAdapter(
            languageViewModel.loadCountries(),
            object : LanguageAdapter.OnLanguageAdapterListener {
                override fun onClickLanguageListener(language: String) {
                    languageSelected = language
                }
            })
    }
    private val languageViewModel: LanguageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        initAdapter()
    }

    private fun initAdapter() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                languageViewModel.searchCountries(s.toString())
            }
        })

        languageViewModel.listLanguageState.observe(this) {
            countryAdapter.setCountries(it)
            if (it.size == 0) {
                binding.tvEmpty.visibility = View.VISIBLE
            } else {
                binding.tvEmpty.visibility = View.GONE
            }
        }

        binding.ibContinue.setOnClickListener {
            languageSelected?.let { it1 -> languageViewModel.setUpLanguage(it1) }
            languageViewModel.setFirstLanguageRun()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvListLanguage.layoutManager = linearLayoutManager
        binding.rvListLanguage.adapter = countryAdapter
    }
}