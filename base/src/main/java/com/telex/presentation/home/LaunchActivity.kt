package com.telex.presentation.home

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.telex.R
import com.telex.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_launch.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

/**
 * @author Sergey Petrov
 */
class LaunchActivity : BaseActivity(), LaunchView {

    override val layoutRes: Int = R.layout.activity_launch

    @InjectPresenter lateinit var presenter: LaunchPresenter

    @ProvidePresenter
    fun providePresenter(): LaunchPresenter {
        return scope.getInstance(LaunchPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupStatusBar()
        router.setup(supportFragmentManager)

        presenter.login(intent.data)
    }

    override fun onResume() {
        super.onResume()
        router.setup(supportFragmentManager)
    }

    override fun onPause() {
        super.onPause()
        router.clear()
    }

    override fun showProgress(isVisible: Boolean) {
        if (isVisible) {
            progressBar.visibility = VISIBLE
        } else {
            progressBar.visibility = GONE
        }
    }

    override fun showNext() {
        router.showHomeActivity(this)
        finish()
        overridePendingTransition(0, 0)
    }

    override fun onLogout() {
        router.showHomeActivity(this)
        finish()
        overridePendingTransition(0, 0)
    }
}
