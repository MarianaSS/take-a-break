package br.com.mariana.take_a_break.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
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
    private var timerProgress: Long = 0
    private var counterProgress: Long = 0
    private var isPaused: Boolean = false

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

        startTimer(100000)
        buttonPauseTimer.setOnClickListener(onClickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cancelTimer()
    }

    private val onClickListener = View.OnClickListener {
        isPaused = if (isPaused) {
            setTimerAsRunning()
        } else {
            setTimerAsPaused()
        }
    }

    private fun setTimerAsPaused(): Boolean {
        buttonPauseTimer.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_baseline_play_arrow_24,
                context?.theme
            )
        )
        counterProgress = countDown.base - SystemClock.elapsedRealtime()
        cancelTimer()
        return true
    }

    private fun setTimerAsRunning(): Boolean {
        buttonPauseTimer.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_baseline_pause_24,
                context?.theme
            )
        )
        countDown.base = SystemClock.elapsedRealtime() + counterProgress
        startTimer(timerProgress)
        return false
    }

    private fun startTimer(millisInFuture: Long) {
        countDownTimer = object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 1000).toInt()
                progressBar.progress = progressBar.max - progress
                timerProgress = millisUntilFinished
                countDown.start()
            }

            override fun onFinish() {
                setTimerAsPaused()
                buttonPauseTimer.isEnabled = false
            }
        }
        (countDownTimer as CountDownTimer).start()
    }

    private fun cancelTimer() {
        if (countDownTimer != null) countDownTimer!!.cancel()
        countDown.stop()
    }
}