package ducksim

object DuckFactory : Subject() {
    fun createDuck(baseDuck: Duck, starCount: Int, moonCount: Int, crossCount: Int): Duck {
        var duck = baseDuck
        repeat(starCount) {
            duck = StarBling(duck)
        }
        repeat(moonCount) {
            duck = MoonBling(duck)
        }
        repeat(crossCount) {
            duck = CrossBling(duck)
        }
        notifyObserver()
        return duck
    }
}