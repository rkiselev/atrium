// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.exactly
import ch.tutteli.atrium.api.infix.en_GB.value
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
class IterableContainsInAnyOrderExactlyValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderExactlyValuesAssertionsSpec(
    AssertionVerbFactory,
    getExactlyTriple(),
    getContainsNotPair(),
    "◆ "
) {

    companion object : IterableContainsSpecBase() {

        private fun getExactlyTriple() = Triple(
            "$toContain $inAnyOrder $exactly",
            { what: String, times: String -> "$toContain $what $exactly $times" },
            Companion::containsExactly
        )

        private fun containsExactly(plant: Assert<Iterable<Double>>, exactly: Int, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                (plant.asExpect().contains(o) inAny ch.tutteli.atrium.api.infix.en_GB.order).exactly(exactly).value(a).asAssert()
            } else {
                val values = Values(a, *aX)
                (plant.asExpect().contains(o) inAny ch.tutteli.atrium.api.infix.en_GB.order exactly exactly).the<Double, Iterable<Double>>(
                    values(
                        values.expected,
                        *values.otherExpected
                    )
                ).asAssert()
            }
        }

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use `$containsNotValues` instead of `$exactly $times`"

    }
}
