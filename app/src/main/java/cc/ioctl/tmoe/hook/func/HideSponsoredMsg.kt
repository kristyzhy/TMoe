package cc.ioctl.tmoe.hook.func

import cc.ioctl.tmoe.hook.base.CommonDynamicHook
import cc.ioctl.tmoe.base.annotation.FunctionHookEntry
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.loadAndFindMethods
import com.github.kyuubiran.ezxhelper.utils.tryOrFalse

/**
 * From `https://github.com/shatyuka/Killergram`
 */
@FunctionHookEntry
object HideSponsoredMsg : CommonDynamicHook() {
    override fun initOnce(): Boolean = tryOrFalse {
        arrayOf(
            "org.telegram.messenger.MessagesController",
            "org.telegram.ui.ChatActivity"
        ).loadAndFindMethods {
            name.contains("SponsoredMessages")
        }.hookBefore { if (isEnabled) it.result = null }
    }
}