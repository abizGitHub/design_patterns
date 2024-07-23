package common

interface Fabric {
    fun getFiber(): String
}

class Wool : Fabric {
    override fun getFiber() = "wool"
}

class Silk : Fabric {
    override fun getFiber() = "silk"
}

class Cotton : Fabric {
    override fun getFiber() = "cotton"
}
