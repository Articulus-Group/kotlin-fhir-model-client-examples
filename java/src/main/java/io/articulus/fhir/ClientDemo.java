package io.articulus.fhir;

import com.google.gson.Gson;
import io.articulus.fhir.model.Utils;
import io.articulus.fhir.model.dstu3.Patient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ClientDemo {

    public static void main(String[] args) throws IOException {

        String url = "http://test.fhir.org/r3/Patient/06064333-cc2c-45fb-b0de-3cdfd1a2cf/_history/1?_format=json";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();

        Gson gson = Utils.gsonFhirBuilder().create();

        Patient patient = gson.fromJson(json, Patient.class);


        System.out.printf("Patient Id: %s%n", patient.getId());
        System.out.printf("Patient Name: %s %s%n", patient.getName().get(0).getGiven().get(0), patient.getName().get(0).getFamily());
        System.out.printf("Patient DoB: %s%n", patient.getBirthDate());

    }
}
