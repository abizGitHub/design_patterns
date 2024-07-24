package combine

class Tv(private val name: String) : Device {
    override fun turnOn() {
        println("tv is on")
    }

    override fun turnOff() {
        println("tv is off")
    }

    override fun getName() = name
}

class Radio(private val name: String) : Device {
    override fun turnOn() {
        println("radio is on")
    }

    override fun turnOff() {
        println("radio is off")
    }

    override fun getName() = name
}

//------------------------------------------------------
class ClassicRemoteControl(private var device: Device, setting: ToggleSetting = SinglePush()) :
    RemoteControl(setting = setting) {
    override fun connectToDevice(): Device {
        println("connecting to ${device.getName()} via bluetooth..")
        return device
    }
}

class AdvancedRemoteControl(private var device: Device, setting: ToggleSetting = SinglePush()) :
    RemoteControl(setting) {
    override fun connectToDevice(): Device {
        println("connecting to ${device.getName()} via wifi..")
        return device
    }
}

fun main() {

    val classicSonyTvRemote = ClassicRemoteControl(Tv("sony"))
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()

    println("--------------CHANGE SETTING------------------")
    classicSonyTvRemote.changeSetting(DoublePush())
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()
    classicSonyTvRemote.togglePower()

    println("--------------NEW REMOTE CONTROL------------------")
    val advancedSamsungRadioRemote = AdvancedRemoteControl(Radio("samsung"), setting = DoublePush())
    advancedSamsungRadioRemote.togglePower()
    advancedSamsungRadioRemote.togglePower()
    advancedSamsungRadioRemote.togglePower()
    advancedSamsungRadioRemote.togglePower()
}


