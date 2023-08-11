package com.example.appcc.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appcc.CreateIconEvent
import com.example.appcc.adapter.MyAppLauncherAdapter
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentAppLauncherBinding
import com.example.appcc.databinding.FragmentIconDetail2Binding
import com.example.appcc.extension.createShortcut
import com.example.appcc.extension.getBitmapFromAsset
import com.example.appcc.extension.getDeviceName
import com.example.appcc.extension.gone
import com.example.appcc.extension.isPackageInstalled
import com.example.appcc.extension.visibble
import com.example.appcc.model.ContentX
import com.example.appcc.model.MyAppIcon
import com.example.appcc.viewmodel.ShortcutViewModel
import org.greenrobot.eventbus.EventBus
//import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.Locale

class FragmentIconDetail2(contentX: ContentX) : BaseFragment() {
    private lateinit var binding: FragmentIconDetail2Binding
    private val shortcutViewModel: ShortcutViewModel by activityViewModels()
    val someList = mutableListOf<MyAppIcon>()
//    val arg: FragmentIconDetailArgs by navArgs()
    val arg = contentX
    private var changeIconPosition = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIconDetail2Binding.inflate(layoutInflater)
        return binding.root
    }

    val adapter = MyAppLauncherAdapter { position, flag ->
        when (flag) {
            MyAppLauncherAdapter.FLAG_ADD_ICON -> {
                changeIconPosition = position
//                loadingView.visibble()
                val action =
                    FragmentIconDetailDirections.actionFragmentAppLauncher2ToSelectApp(someList[position].icon)
                findNavController().navigate(action)
                someList[position].check = false
            }

            MyAppLauncherAdapter.FLAG_INSTALL -> {
                if (someList[position].pkg == "" || someList[position].appIcon == null) {
                    Toast.makeText(
                        requireContext(),
                        "You must select related app before install icon",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@MyAppLauncherAdapter
                }
                val packageManager = requireContext().packageManager
                val x = packageManager.getApplicationInfo(someList[position].pkg, 0)
                val bitmap = requireContext().getBitmapFromAsset(someList[position].icon)
                bitmap?.let {
                    createShortcut(requireContext(), x, it, someList[position].label, true)
                }
            }

            MyAppLauncherAdapter.FLAG_REMOVE -> {
                someList[position].appIcon = null
                someList[position].label = ""
                someList[position].pkg = ""
                someList[position].check = false
                setSelectedAppText()
            }
            MyAppLauncherAdapter.FLAG_COUNT -> {
                setSelectedAppText()
            }

        }
    }

    private fun setSelectedAppText() {
        var count = 0
        var countCheck = 0
        someList.forEach {
            if (it.appIcon != null && it.pkg != "" && it.icon != "" && it.check) {
                count++
            }
            if (it.appIcon != null && it.pkg != "" && it.icon != "") {
                countCheck++
            }
        }
        if (count == 0) {
            binding.unchecked.gone()
        }
        if (countCheck > 0) {
            if (count == countCheck) {
                binding.unchecked.visibble()
                binding.selectAll.gone()
            }
        } else {
            binding.selectAll.visibble()
        }

        binding.tvInstallAll.setText("Install $count App Icons")
    }

    private fun removeAllAppIcon() {
        for (i in someList) {
            i.check = false
//            btn_check.isChecked = false
            setSelectedAppText()
            binding.unchecked.gone()
            binding.selectAll.visibble()
            adapter.notifyDataSetChanged()
        }
    }

    private fun checkAllAppIcon() {
        someList.forEach {
            if (it.pkg != "") {
                it.check = true
            }
            setSelectedAppText()
            binding.unchecked.visibble()
            binding.selectAll.gone()
            adapter.notifyDataSetChanged()
        }
    }

    override fun bindView() {

        if (someList.isEmpty()) {
            arg.icon.forEach {
                someList.add(MyAppIcon(it, ""))
            }

            val deviceName = getDeviceName()
            val packageManager = requireActivity().packageManager
            when {
                deviceName.toLowerCase(Locale.US).contains("oppo") -> {
                    if (isPackageInstalled("com.oppo.music", packageManager)) {
                        someList.get(31).appIcon =
                            packageManager.getApplicationIcon("com.oppo.music")
                        someList.get(31).label = "Music"
                        someList.get(31).pkg = "com.oppo.music"
                    }

                    if (isPackageInstalled("com.android.settings", packageManager)) {
                        someList.get(16).appIcon =
                            packageManager.getApplicationIcon("com.android.settings")
                        someList.get(16).label = "Setting"
                        someList.get(16).pkg = "com.android.settings"

                    }

                    if (isPackageInstalled("com.android.email", packageManager)) {
                        someList.get(3).appIcon =
                            packageManager.getApplicationIcon("com.android.email")
                        someList.get(3).label = "Reminder"
                        someList.get(3).pkg = "com.android.email"
                    }

                    if (isPackageInstalled("com.oppo.camera", packageManager)) {
                        someList.get(0).appIcon =
                            packageManager.getApplicationIcon("com.oppo.camera")
                        someList.get(0).label = "Camera"
                        someList.get(0).pkg = "com.oppo.camera"

                    }

                    if (isPackageInstalled("com.android.vending", packageManager)) {
                        someList.get(10).appIcon =
                            packageManager.getApplicationIcon("com.android.vending")
                        someList.get(10).label = "Play Store"
                        someList.get(10).pkg = "com.android.vending"
                    }

                    if (isPackageInstalled("com.ss.android.ugc.trill", packageManager)) {
                        someList.get(21).appIcon =
                            packageManager.getApplicationIcon("com.ss.android.ugc.trill")
                        someList.get(21).label = "Tiktok"
                        someList.get(21).pkg = "com.ss.android.ugc.trill"
                    }

                    if (isPackageInstalled("com.google.android.youtube", packageManager)) {
                        someList.get(24).appIcon =
                            packageManager.getApplicationIcon("com.google.android.youtube")
                        someList.get(24).label = "Youtube"
                        someList.get(24).pkg = "com.google.android.youtube"
                    }

                    if (isPackageInstalled("com.facebook.orca", packageManager)) {
                        someList.get(19).appIcon =
                            packageManager.getApplicationIcon("com.facebook.orca")
                        someList.get(19).label = "Messenger"
                        someList.get(19).pkg = "com.facebook.orca"
                    }

                    if (isPackageInstalled("com.facebook.katana", packageManager)) {
                        someList.get(17).appIcon =
                            packageManager.getApplicationIcon("com.facebook.katana")
                        someList.get(17).label = "Facebook"
                        someList.get(17).pkg = "com.facebook.katana"
                    }

                    if (isPackageInstalled("com.twitter.android", packageManager)) {
                        someList.get(20).appIcon =
                            packageManager.getApplicationIcon("com.twitter.android")
                        someList.get(20).label = "Twitter"
                        someList.get(20).pkg = "com.twitter.android"
                    }

                    if (isPackageInstalled("com.snapchat.android", packageManager)) {
                        someList.get(23).appIcon =
                            packageManager.getApplicationIcon("com.snapchat.android")
                        someList.get(23).label = "Snapchat"
                        someList.get(23).pkg = "com.snapchat.android"
                    }
                    if (isPackageInstalled("com.spotify.music", packageManager)) {
                        someList.get(22).appIcon =
                            packageManager.getApplicationIcon("com.spotify.music")
                        someList.get(22).label = "Spotify"
                        someList.get(22).pkg = "com.spotify.music"
                    }

                    if (isPackageInstalled("com.instagram.android", packageManager)) {
                        someList.get(18).appIcon =
                            packageManager.getApplicationIcon("com.instagram.android")
                        someList.get(18).label = "Instagram"
                        someList.get(18).pkg = "Instagram"
                    }
                }

                deviceName.toLowerCase(Locale.US).contains("samsung") -> {
                    if (isPackageInstalled("com.android.email", packageManager)) {
                        someList.get(3).appIcon =
                            packageManager.getApplicationIcon("com.android.email")
                        someList.get(3).label = "Reminder"
                        someList.get(3).pkg = "com.android.email"
                    }

                    if (isPackageInstalled("com.android.settings", packageManager)) {
                        someList.get(16).appIcon =
                            packageManager.getApplicationIcon("com.android.settings")
                        someList.get(16).label = "Setting"
                        someList.get(16).pkg = "com.android.settings"

                    }

                    if (isPackageInstalled("com.samsung.android.app.reminder", packageManager)) {
                        someList.get(5).appIcon =
                            packageManager.getApplicationIcon("com.samsung.android.app.reminder")
                        someList.get(5).label = "Reminder"
                        someList.get(5).pkg = "com.samsung.android.app.reminder"
                    }


                    if (isPackageInstalled("com.android.email", packageManager)) {
                        someList.get(3).appIcon =
                            packageManager.getApplicationIcon("com.android.email")
                        someList.get(3).label = "Reminder"
                        someList.get(3).pkg = "com.android.email"
                    }


                    if (isPackageInstalled("com.google.android.apps.messaging", packageManager)) {
                        someList.get(29).appIcon =
                            packageManager.getApplicationIcon("com.google.android.apps.messaging")
                        someList.get(29).label = "Message"
                        someList.get(29).pkg = "com.google.android.apps.messaging"

                    }

                    if (isPackageInstalled("com.google.android.contacts", packageManager)) {
                        someList.get(27).appIcon =
                            packageManager.getApplicationIcon("com.google.android.contacts")
                        someList.get(27).label = "Contact"
                        someList.get(27).pkg = "com.google.android.contacts"

                    }

                    if (isPackageInstalled("com.google.android.apps.maps", packageManager)) {
                        someList.get(4).appIcon =
                            packageManager.getApplicationIcon("com.google.android.apps.maps")
                        someList.get(4).label = "Map"
                        someList.get(4).pkg = "com.google.android.apps.maps"

                    }

                    if (isPackageInstalled("com.samsung.android.app.notes", packageManager)) {
                        someList.get(7).appIcon =
                            packageManager.getApplicationIcon("com.samsung.android.app.notes")
                        someList.get(7).label = "Notes"
                        someList.get(7).pkg = "com.samsung.android.app.notes"

                    }

                    if (isPackageInstalled("com.android.vending", packageManager)) {
                        someList.get(10).appIcon =
                            packageManager.getApplicationIcon("com.android.vending")
                        someList.get(10).label = "App Store"
                        someList.get(10).pkg = "com.android.vending"

                    }

                    if (isPackageInstalled("com.android.settings", packageManager)) {
                        someList.get(16).appIcon =
                            packageManager.getApplicationIcon("com.android.settings")
                        someList.get(16).label = "Setting"
                        someList.get(16).pkg = "com.android.settings"

                    }

                    if (isPackageInstalled("com.ss.android.ugc.trill", packageManager)) {
                        someList.get(21).appIcon =
                            packageManager.getApplicationIcon("com.ss.android.ugc.trill")
                        someList.get(21).label = "Tiktok"
                        someList.get(21).pkg = "com.ss.android.ugc.trill"

                    }

                    if (isPackageInstalled("com.google.android.youtube", packageManager)) {
                        someList.get(24).appIcon =
                            packageManager.getApplicationIcon("com.google.android.youtube")
                        someList.get(24).label = "Youtube"
                        someList.get(24).pkg = "com.google.android.youtube"

                    }

                    if (isPackageInstalled("com.samsung.android.calendar", packageManager)) {
                        someList.get(1).appIcon =
                            packageManager.getApplicationIcon("com.samsung.android.calendar")
                        someList.get(1).label = "Calendar"
                        someList.get(1).pkg = "com.samsung.android.calendar"

                    }

                    if (isPackageInstalled("com.facebook.orca", packageManager)) {
                        someList.get(19).appIcon =
                            packageManager.getApplicationIcon("com.facebook.orca")
                        someList.get(19).label = "Messenger"
                        someList.get(19).pkg = "com.facebook.orca"

                    }

                    if (isPackageInstalled("com.facebook.katana", packageManager)) {
                        someList.get(17).appIcon =
                            packageManager.getApplicationIcon("com.facebook.katana")
                        someList.get(17).label = "Facebook"
                        someList.get(17).pkg = "com.facebook.katana"

                    }

                    if (isPackageInstalled("com.twitter.android", packageManager)) {
                        someList.get(20).appIcon =
                            packageManager.getApplicationIcon("com.twitter.android")
                        someList.get(20).label = "Twitter"
                        someList.get(20).pkg = "com.twitter.android"

                    }

                    if (isPackageInstalled("com.snapchat.android", packageManager)) {
                        someList.get(23).appIcon =
                            packageManager.getApplicationIcon("com.snapchat.android")
                        someList.get(23).label = "Snapchat"
                        someList.get(23).pkg = "com.snapchat.android"

                    }
                    if (isPackageInstalled("com.spotify.music", packageManager)) {
                        someList.get(22).appIcon =
                            packageManager.getApplicationIcon("com.spotify.music")
                        someList.get(22).label = "Spotify"
                        someList.get(22).pkg = "com.spotify.music"

                    }
                }

                deviceName.toLowerCase(Locale.US).contains("xiaomi") -> {
                    if (isPackageInstalled("com.miui.screenrecorder", packageManager)) {
                        someList.get(0).appIcon =
                            packageManager.getApplicationIcon("com.miui.screenrecorder")
                        someList.get(0).label = "Screen Recorder"
                        someList.get(0).pkg = "com.miui.screenrecorder"
                    }

                    if (isPackageInstalled("com.miui.gallery", packageManager)) {
                        someList.get(2).appIcon =
                            packageManager.getApplicationIcon("com.miui.gallery")
                        someList.get(2).label = "Gallery"
                        someList.get(2).pkg = "com.miui.gallery"

                    }

                    if (isPackageInstalled("com.android.email", packageManager)) {
                        someList.get(3).appIcon =
                            packageManager.getApplicationIcon("com.android.email")
                        someList.get(3).label = "Reminder"
                        someList.get(3).pkg = "com.android.email"
                    }


                    if (isPackageInstalled("com.miui.player", packageManager)) {
                        someList.get(31).appIcon =
                            packageManager.getApplicationIcon("com.miui.player")
                        someList.get(31).label = "Music Player"
                        someList.get(31).pkg = "com.miui.player"

                    }

                    if (isPackageInstalled("com.miui.compass", packageManager)) {
                        someList.get(30).appIcon =
                            packageManager.getApplicationIcon("com.miui.compass")
                        someList.get(30).label = "Compass"
                        someList.get(30).pkg = "com.miui.compass"

                    }
                    if (isPackageInstalled("com.google.android.apps.messaging", packageManager)) {
                        someList.get(29).appIcon =
                            packageManager.getApplicationIcon("com.google.android.apps.messaging")
                        someList.get(29).label = "Message"
                        someList.get(29).pkg = "com.google.android.apps.messaging"

                    }

                    if (isPackageInstalled("com.google.android.contacts", packageManager)) {
                        someList.get(27).appIcon =
                            packageManager.getApplicationIcon("com.google.android.contacts")
                        someList.get(27).label = "Contact"
                        someList.get(27).pkg = "com.google.android.contacts"

                    }

                    if (isPackageInstalled("com.google.android.apps.maps", packageManager)) {
                        someList.get(4).appIcon =
                            packageManager.getApplicationIcon("com.google.android.apps.maps")
                        someList.get(4).label = "Map"
                        someList.get(4).pkg = "com.google.android.apps.maps"

                    }

                    if (isPackageInstalled("com.miui.notes", packageManager)) {
                        someList.get(9).appIcon =
                            packageManager.getApplicationIcon("com.miui.notes")
                        someList.get(9).label = "Notes"
                        someList.get(9).pkg = "com.miui.notes"

                    }

                    if (isPackageInstalled("com.xiaomi.calendar", packageManager)) {
                        someList.get(1).appIcon =
                            packageManager.getApplicationIcon("com.xiaomi.calendar")
                        someList.get(1).label = "Calendar"
                        someList.get(1).pkg = "com.xiaomi.calendar"

                    }

                    if (isPackageInstalled("com.miui.calculator", packageManager)) {
                        someList.get(33).appIcon =
                            packageManager.getApplicationIcon("com.miui.calculator")
                        someList.get(33).label = "Calculator"
                        someList.get(33).pkg = "com.miui.calculator"

                    }

                    if (isPackageInstalled("com.android.vending", packageManager)) {
                        someList.get(10).appIcon =
                            packageManager.getApplicationIcon("com.android.vending")
                        someList.get(10).label = "App Store"
                        someList.get(10).pkg = "com.android.vending"

                    }

                    if (isPackageInstalled("com.android.settings", packageManager)) {
                        someList.get(16).appIcon =
                            packageManager.getApplicationIcon("com.android.settings")
                        someList.get(16).label = "Setting"
                        someList.get(16).pkg = "com.android.settings"

                    }

                    if (isPackageInstalled("com.ss.android.ugc.trill", packageManager)) {
                        someList.get(21).appIcon =
                            packageManager.getApplicationIcon("com.ss.android.ugc.trill")
                        someList.get(21).label = "Tiktok"
                        someList.get(21).pkg = "com.ss.android.ugc.trill"

                    }

                    if (isPackageInstalled("com.google.android.youtube", packageManager)) {
                        someList.get(24).appIcon =
                            packageManager.getApplicationIcon("com.google.android.youtube")
                        someList.get(24).label = "Youtube"
                        someList.get(24).pkg = "com.google.android.youtube"

                    }

                    if (isPackageInstalled("com.facebook.orca", packageManager)) {
                        someList.get(19).appIcon =
                            packageManager.getApplicationIcon("com.facebook.orca")
                        someList.get(19).label = "Messenger"
                        someList.get(19).pkg = "com.facebook.orca"

                    }

                    if (isPackageInstalled("com.facebook.katana", packageManager)) {
                        someList.get(17).appIcon =
                            packageManager.getApplicationIcon("com.facebook.katana")
                        someList.get(17).label = "Facebook"
                        someList.get(17).pkg = "com.facebook.katana"

                    }

                    if (isPackageInstalled("com.twitter.android", packageManager)) {
                        someList.get(20).appIcon =
                            packageManager.getApplicationIcon("com.twitter.android")
                        someList.get(20).label = "Twitter"
                        someList.get(20).pkg = "com.twitter.android"

                    }

                    if (isPackageInstalled("com.snapchat.android", packageManager)) {
                        someList.get(23).appIcon =
                            packageManager.getApplicationIcon("com.snapchat.android")
                        someList.get(23).label = "Snapchat"
                        someList.get(23).pkg = "com.snapchat.android"

                    }
                    if (isPackageInstalled("com.spotify.music", packageManager)) {
                        someList.get(22).appIcon =
                            packageManager.getApplicationIcon("com.spotify.music")
                        someList.get(22).label = "Spotify"
                        someList.get(22).pkg = "com.spotify.music"

                    }
                }

                deviceName.toLowerCase(Locale.US).contains("huawei") -> {
                    if (isPackageInstalled("com.android.email", packageManager)) {
                        someList.get(3).appIcon =
                            packageManager.getApplicationIcon("com.android.email")
                        someList.get(3).label = "Reminder"
                        someList.get(3).pkg = "com.android.email"
                    }

                    if (isPackageInstalled("com.google.android.apps.messaging", packageManager)) {
                        someList.get(29).appIcon =
                            packageManager.getApplicationIcon("com.google.android.apps.messaging")
                        someList.get(29).label = "Message"
                        someList.get(29).pkg = "com.google.android.apps.messaging"

                    }
                    if (isPackageInstalled("com.huawei.compass", packageManager)) {
                        someList.get(30).appIcon =
                            packageManager.getApplicationIcon("com.huawei.compass")
                        someList.get(30).label = "Compass"
                        someList.get(30).pkg = "com.huawei.compass"

                    }
                    if (isPackageInstalled("com.android.email", packageManager)) {
                        someList.get(30).appIcon =
                            packageManager.getApplicationIcon("com.android.email")
                        someList.get(30).label = "Email"
                        someList.get(30).pkg = "com.android.email"

                    }

                    if (isPackageInstalled("com.google.android.contacts", packageManager)) {
                        someList.get(27).appIcon =
                            packageManager.getApplicationIcon("com.google.android.contacts")
                        someList.get(27).label = "Contact"
                        someList.get(27).pkg = "com.google.android.contacts"

                    }

                    if (isPackageInstalled("com.google.android.apps.maps", packageManager)) {
                        someList.get(4).appIcon =
                            packageManager.getApplicationIcon("com.google.android.apps.maps")
                        someList.get(4).label = "Map"
                        someList.get(4).pkg = "com.google.android.apps.maps"

                    }

                    if (isPackageInstalled("android.process.media", packageManager)) {
                        someList.get(31).appIcon =
                            packageManager.getApplicationIcon("android.process.media")
                        someList.get(31).label = "Music"
                        someList.get(31).pkg = "android.process.media"

                    }
                    if (isPackageInstalled("com.example.android.notepad", packageManager)) {
                        someList.get(7).appIcon =
                            packageManager.getApplicationIcon("com.example.android.notepad")
                        someList.get(7).label = "Phone"
                        someList.get(7).pkg = "com.example.android.notepad"

                    }

                    if (isPackageInstalled("com.huawei.appmarket", packageManager)) {
                        someList.get(10).appIcon =
                            packageManager.getApplicationIcon("com.huawei.appmarket")
                        someList.get(10).label = "App Market"
                        someList.get(10).pkg = "com.huawei.appmarket"

                    }

                    if (isPackageInstalled("com.android.settings", packageManager)) {
                        someList.get(16).appIcon =
                            packageManager.getApplicationIcon("com.android.settings")
                        someList.get(16).label = "Setting"
                        someList.get(16).pkg = "com.android.settings"

                    }

                    if (isPackageInstalled("com.ss.android.ugc.trill", packageManager)) {
                        someList.get(21).appIcon =
                            packageManager.getApplicationIcon("com.ss.android.ugc.trill")
                        someList.get(21).label = "Tiktok"
                        someList.get(21).pkg = "com.ss.android.ugc.trill"

                    }

                    if (isPackageInstalled("com.google.android.youtube", packageManager)) {
                        someList.get(24).appIcon =
                            packageManager.getApplicationIcon("com.google.android.youtube")
                        someList.get(24).label = "Youtube"
                        someList.get(24).pkg = "com.google.android.youtube"

                    }

                    if (isPackageInstalled("com.android.calendar", packageManager)) {
                        someList.get(1).appIcon =
                            packageManager.getApplicationIcon("com.android.calendar")
                        someList.get(1).label = "Calendar"
                        someList.get(1).pkg = "com.android.calendar"

                    }

                    if (isPackageInstalled("com.facebook.orca", packageManager)) {
                        someList.get(19).appIcon =
                            packageManager.getApplicationIcon("com.facebook.orca")
                        someList.get(19).label = "Messenger"
                        someList.get(19).pkg = "com.facebook.orca"

                    }

                    if (isPackageInstalled("com.facebook.katana", packageManager)) {
                        someList.get(17).appIcon =
                            packageManager.getApplicationIcon("com.facebook.katana")
                        someList.get(17).label = "Facebook"
                        someList.get(17).pkg = "com.facebook.katana"

                    }

                    if (isPackageInstalled("com.twitter.android", packageManager)) {
                        someList.get(20).appIcon =
                            packageManager.getApplicationIcon("com.twitter.android")
                        someList.get(20).label = "Twitter"
                        someList.get(20).pkg = "com.twitter.android"

                    }

                    if (isPackageInstalled("com.snapchat.android", packageManager)) {
                        someList.get(23).appIcon =
                            packageManager.getApplicationIcon("com.snapchat.android")
                        someList.get(23).label = "Snapchat"
                        someList.get(23).pkg = "com.snapchat.android"

                    }
                    if (isPackageInstalled("com.spotify.music", packageManager)) {
                        someList.get(22).appIcon =
                            packageManager.getApplicationIcon("com.spotify.music")
                        someList.get(22).label = "Spotify"
                        someList.get(22).pkg = "com.spotify.music"

                    }
                }
            }


        }
        adapter.submitList(someList)
        binding.rvLauncher.adapter = adapter

        binding.selectAll.setOnClickListener {
            checkAllAppIcon()
        }
        binding.unchecked.setOnClickListener {
            removeAllAppIcon()
        }

//        binding.btnBack.setOnClickListener {
//            requireActivity().onBackPressed()
//        }

        binding.tvInstallAll.setOnClickListener {
            var isNotEmpty = false
            for (i in someList) {
                if (i.pkg != "") {
                    isNotEmpty = true
                    break
                }
            }
            if (isNotEmpty) {
                shortcutViewModel.installShorcut(requireContext(), someList)
            } else {
                Toast.makeText(context, "Please select at lease one app!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private var showToast = true
    override fun observeData() {
        shortcutViewModel.showLoading.observe(this) {
            if (!it) {
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun createIconEventHandle(createIconEvent: CreateIconEvent) {
        when (createIconEvent) {
            is CreateIconEvent.SelectRelatedApp -> {
                someList[changeIconPosition].pkg = createIconEvent.installedApp.pkgName
                someList[changeIconPosition].appIcon = createIconEvent.installedApp.icon
                someList[changeIconPosition].label = createIconEvent.installedApp.label
                adapter.notifyItemChanged(changeIconPosition)


            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Set after event bus sent
        setSelectedAppText()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }


}