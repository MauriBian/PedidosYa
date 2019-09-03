package Filters

import org.uqbar.arena.filters.TextFilter
import org.uqbar.arena.widgets.TextInputEvent

class IntFilter : TextFilter {
    override fun accept(event: TextInputEvent): Boolean {
        val regex : Regex = Regex("^\\d{0,5}\$")
        return event.potentialTextResult.matches(regex)
    }
}