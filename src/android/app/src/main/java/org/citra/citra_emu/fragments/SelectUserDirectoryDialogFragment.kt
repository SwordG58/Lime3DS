// Copyright 2023 Citra Emulator Project
// Licensed under GPLv2 or any later version
// Refer to the license.txt file included.

package io.github.lime3ds.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.lime3ds.R
import io.github.lime3ds.ui.main.MainActivity
import io.github.lime3ds.viewmodel.HomeViewModel

class SelectUserDirectoryDialogFragment : DialogFragment() {
    private lateinit var mainActivity: MainActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mainActivity = requireActivity() as MainActivity

        isCancelable = false
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.select_citra_user_folder)
            .setMessage(R.string.cannot_skip_directory_description)
            .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                mainActivity.openCitraDirectory.launch(null)
            }
            .show()
    }

    companion object {
        const val TAG = "SelectUserDirectoryDialogFragment"

        fun newInstance(activity: FragmentActivity): SelectUserDirectoryDialogFragment {
            ViewModelProvider(activity)[HomeViewModel::class.java].setPickingUserDir(true)
            return SelectUserDirectoryDialogFragment()
        }
    }
}
