package ch.tutteli.atrium.domain.builders.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector

/**
 * Collects the assertions [assertionCreator] creates and uses them as [AssertionGroup.assertions].
 */
fun <T, G : ExplanatoryAssertionGroupType, R> AssertionsOption<G, R>.collectAssertions(
    expect: Expect<T>,
    assertionCreator: Expect<T>.() -> Unit
): R = collectAssertions(expect.maybeSubject, assertionCreator)

/**
 * Collects the assertions [assertionCreator] creates and uses them as [AssertionGroup.assertions].
 */
fun <T, G : ExplanatoryAssertionGroupType, R> AssertionsOption<G, R>.collectAssertions(
    maybeSubject: Option<T>,
    assertionCreator: Expect<T>.() -> Unit
): R = withAssertions(assertionCollector.collectForComposition(maybeSubject, assertionCreator))
