package com.example.tasktest

import android.app.Application
import com.example.tasktest.manager.NumberGeneratorManager
import com.example.tasktest.ui.main.PageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TaskTestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val module = module {
            single { NumberGeneratorManager() }

            viewModel { PageViewModel() }
        }

        startKoin {
            androidContext(this@TaskTestApp)

            modules(module)
        }
    }
}