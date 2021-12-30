package ducksim

class StarBling(duck: Duck) : Bling(duck) {
    override fun display(): String {
        return duck.display() + ":*"
    }
}