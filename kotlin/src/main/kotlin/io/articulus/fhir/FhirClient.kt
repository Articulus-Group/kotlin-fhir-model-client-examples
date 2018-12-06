package io.articulus.fhir


import khttp.get
import io.articulus.fhir.model.dstu3.Patient
import io.articulus.fhir.model.gsonFhirBuilder


fun main(args: Array<out String>) {

    val json = get("http://test.fhir.org/r3/Patient/06064333-cc2c-45fb-b0de-3cdfd1a2cf/_history/1?_format=json").text

    val gson = gsonFhirBuilder().create()

    val patient = gson.fromJson(json, Patient::class.java)

    println("Patient id: ${patient.id}")
    println("Patiend name: ${patient.name[0].given[0]} ${patient.name[0].family}")
    println("Patient dob: ${patient.birthDate}")
}