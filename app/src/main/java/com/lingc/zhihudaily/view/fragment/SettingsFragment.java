package com.lingc.zhihudaily.view.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.lingc.zhihudaily.R;
import com.lingc.zhihudaily.util.CacheUtil;
import com.squareup.picasso.Picasso;

/**
 * Create by LingC on 2019/7/9 19:06
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
        Preference clearCachePref = findPreference("clearCache");
        clearCachePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                CacheUtil.clearCache(preference.getContext());
                Toast.makeText(preference.getContext(), "清除成功", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
