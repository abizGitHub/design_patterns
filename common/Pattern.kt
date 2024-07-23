package common

interface Pattern {
    fun describe(): String
}

class Plaid : Pattern {
    override fun describe() = "plaid"
}

class Dot : Pattern {
    override fun describe() = "dot"
}

class Stripe : Pattern {
    override fun describe() = "stripe"
}

class Twill : Pattern {
    override fun describe() = "twill"
}