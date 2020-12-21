package br.com.mariana.take_a_break.ui.settings

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SeekBarPreference
import br.com.mariana.take_a_break.R

class SettingsFragment : PreferenceFragmentCompat() {

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        retainInstance = true
        findPreference<Preference>("trabalho")?.let { bindPreferenceSummaryToValue(it) }
        findPreference<Preference>("pausa")?.let { bindPreferenceSummaryToValue(it) }

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        settingsViewModel.updateValue(prefs.getInt("trabalho", 25))
    }

    companion object {

        /**
         * A preference value change listener that updates the preference's summary
         * to reflect its new value.
         */
        private val sBindPreferenceSummaryToValueListener =
            Preference.OnPreferenceChangeListener { preference, value ->

                val stringValue = value.toString()

                if (preference is SeekBarPreference) {
                    preference.summary = stringValue
                }

                true
            }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            // Set the listener to watch for value changes.
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

            // Trigger the listener immediately with the preference's
            // current value.
            sBindPreferenceSummaryToValueListener.onPreferenceChange(
                preference,
                PreferenceManager
                    .getDefaultSharedPreferences(preference.context)
                    .getInt(preference.key, 25)
            )
        }
    }
}