package br.com.mariana.take_a_break.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import br.com.mariana.take_a_break.R

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}