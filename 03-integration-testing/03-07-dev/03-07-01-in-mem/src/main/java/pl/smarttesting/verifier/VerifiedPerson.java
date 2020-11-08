package pl.smarttesting.verifier;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Encja bazodanowa. Wykorzystujemy ORM (mapowanie obiektowo relacyjne) i obiekt
 * tej klasy mapuje się na tabelę {@code verified}. Każde pole klasy to osobna kolumna
 * w bazie danych.
 */
@Entity
@Table(name = "verified")
class VerifiedPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private UUID userId;
	@NotBlank
	private String nationalIdentificationNumber;
	@NotBlank
	private String status;

	VerifiedPerson(UUID userId, String nationalIdentificationNumber, CustomerVerificationResult.Status status) {
		this.userId = userId;
		this.nationalIdentificationNumber = nationalIdentificationNumber;
		this.status = status.toString();
	}

	protected VerifiedPerson() { }

	Long getId() {
		return id;
	}

	void setID(Long id) {
		this.id = id;
	}

	UUID getUserId() {
		return userId;
	}

	String getNationalIdentificationNumber() {
		return nationalIdentificationNumber;
	}

	String getStatus() {
		return status;
	}
}