package br.com.mariana.take_a_break.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.mariana.take_a_break.R
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()
        buttonPauseTimer.setOnClickListener(onClickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cancelTimer()
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            R.id.buttonPauseTimer -> {
                cancelTimer()
            }
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(100000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished/1000).toInt()
                progressBar.progress = progressBar.max - progress
                countDown.start()

            }
            override fun onFinish() {
                cancelTimer()
            }
        }
        (countDownTimer as CountDownTimer).start()
    }

    private fun cancelTimer() {
        if (countDownTimer != null) countDownTimer!!.cancel()
        countDown.stop()
    }
}