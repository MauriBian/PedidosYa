package Filters

import org.uqbar.arena.filters.TextFilter
import org.uqbar.arena.widgets.TextInputEvent

class JustOneDotFilter : TextFilter {

    override fun accept(event: TextInputEvent): Boolean {
        val regex  = Regex("^(\\d|-)*\\,?\\d*\$")
        return event.potentialTextResult.matches(regex)
    }
}