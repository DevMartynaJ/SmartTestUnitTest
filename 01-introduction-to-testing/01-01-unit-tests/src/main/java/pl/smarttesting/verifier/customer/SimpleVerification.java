package pl.smarttesting.verifier.customer;

import pl.smarttesting.customer.Person;
import pl.smarttesting.verifier.Verification;

/**
 * Implementacja weryfikacji obrazującej problemy z testami, które nie weryfikują poprawnie implementacji.
 */
public class SimpleVerification implements Verification {

	/**
	 * Przykład problemu w testach: metoda z niezaimplementowaną logiką, dla której przechodzą źle napisane testy
	 */
	@Override
	public boolean passes(Person person) {
		// TODO
		// use someLogicResolvingToBoolean(person);
		return false;
	}

	private boolean someLogicResolvingToBoolean(Person person) {
		throw new UnsupportedOperationException("Not yet implemented!");
	}
}
