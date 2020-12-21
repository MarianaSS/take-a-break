package br.com.mariana.take_a_break.ui.timer

import android.content.Context
import androidx.preference.PreferenceManager

class PreferencesUtils {

    companion object {

        private const val TIMER_LENGTH_ID = "trabalho"
        fun getTimerLength(context: Context): Int{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(TIMER_LENGTH_ID, 25)
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "previous_timer_length_seconds"

        fun getPreviousTimerLengthSeconds(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(seconds: Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "timer_state"

        fun getTimerState(context: Context?): TimerFragment.TimerState{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return TimerFragment.TimerState.values()[ordinal]
        }

        fun setTimerState(state: TimerFragment.TimerState, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }
        private const val SECONDS_REMAINING_ID = "seconds_remaining"

        fun getSecondsRemaining(context: Context?): Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(seconds: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }
    }
}