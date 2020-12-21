package br.com.mariana.take_a_break.ui.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.mariana.take_a_break.R
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment() {

    enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var timerState = TimerState.Paused
    private var secondsRemaining: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonPauseTimer.setOnClickListener(onClickListener)
        buttonStopTimer.setOnClickListener {
            setTimerAsStopped()
        }
    }

    private fun setTimerAsPaused() {
        buttonPauseTimer.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_baseline_play_arrow_24,
                context?.theme
            )
        )
        timerState = TimerState.Paused
        timer.cancel()
    }

    private fun setTimerAsRunning() {
        buttonPauseTimer.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_baseline_pause_24,
                context?.theme
            )
        )
        startTimer()
    }

    private fun setTimerAsStopped() {
        buttonPauseTimer.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_baseline_play_arrow_24,
                context?.theme
            )
        )
        secondsRemaining = 0
        onPause()
        onTimerFinished()
    }

    private fun startTimer() {
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        initTimer()

        if (timerState == TimerState.Running) {
            setTimerAsRunning()
        }
    }

    private fun initTimer() {
        timerState = PreferencesUtils.getTimerState(context)

        if (timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
            PreferencesUtils.getSecondsRemaining(context)
        else
            timerLengthSeconds

        if (secondsRemaining <= 0)
            onTimerFinished()
        else if (timerState == TimerState.Running)
            startTimer()

        updateCountdownUI()
    }
    private fun onTimerFinished() {
        timerState = TimerState.Stopped

        setNewTimerLength()

        progressBar.progress = 0

        context?.let { PreferencesUtils.setSecondsRemaining(timerLengthSeconds, it) }
        secondsRemaining = timerLengthSeconds

        updateCountdownUI()
    }

    override fun onPause() {
        super.onPause()

        if (timerState == TimerState.Running) {
            timer.cancel()
        }

        context?.let {
            PreferencesUtils.setTimerState(timerState, it)
            PreferencesUtils.setPreviousTimerLengthSeconds(timerLengthSeconds, it)
            PreferencesUtils.setSecondsRemaining(secondsRemaining, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onPause()
    }

    private fun setNewTimerLength() {
        val lengthInMinutes = context?.let { PreferencesUtils.getTimerLength(it) }
        if (lengthInMinutes != null) {
            timerLengthSeconds = (lengthInMinutes * 60L)
        }
        progressBar.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength() {
        timerLengthSeconds = context?.let { PreferencesUtils.getPreviousTimerLengthSeconds(it) }!!
        progressBar.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        countDown?.text =
            "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0$secondsStr"}"
        progressBar?.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private val onClickListener = View.OnClickListener {
        when (timerState) {
            TimerState.Paused -> {
                setTimerAsRunning()
            }

            TimerState.Running -> {
                setTimerAsPaused()
            }

            TimerState.Stopped -> {
                setTimerAsRunning()
            }
        }
    }
}