package combine

interface Device {
    fun turnOn()
    fun turnOff()
    fun getName(): String
}

interface ToggleSetting {
    fun pushButton(): Boolean
}

class SinglePush : ToggleSetting {
    private var turnedOn: Boolean = false

    override fun pushButton(): Boolean {
        turnedOn = when (turnedOn) {
            true -> false
            false -> true
        }
        return turnedOn
    }
}

class DoublePush : ToggleSetting {
    private var turnedOn: Boolean = false
    private var pushCount = 0

    override fun pushButton(): Boolean {
        pushCount++
        if (pushCount == 2) {
            turnedOn = when (turnedOn) {
                true -> false
                false -> true
            }
            pushCount = 0
        }
        return turnedOn
    }
}

abstract class RemoteControl(setting: ToggleSetting) {
    private var toggleSetting: ToggleSetting = setting

    protected abstract fun connectToDevice(): Device

    fun togglePower() {
        val on = toggleSetting.pushButton()
        when (on) {
            true -> connectToDevice().turnOn()
            false -> connectToDevice().turnOff()
        }
    }

    fun changeSetting(toggleSetting: ToggleSetting) {
        this.toggleSetting = toggleSetting
    }
}

