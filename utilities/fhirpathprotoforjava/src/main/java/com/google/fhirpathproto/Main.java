package com.google.fhirpathproto;

import com.google.fhir.r4.core.Appointment;
import com.google.fhir.r4.core.Patient;
import com.google.fhirpathproto.JsonFormatBase.FileType;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import org.hl7.fhir.exceptions.FHIRException;

public class Main {

  public static void main(String[] args) throws IOException, FHIRException {
    FHIRPathProtoEvaluator fhirPathProtoEvaluator = new FHIRPathProtoEvaluator();

    Patient.Builder patientBuilder = Patient.newBuilder();

    JsonFormatBase jsonFormatBase = new JsonFormatBase();

    fhirPathProtoEvaluator.evaluateProtoTxtFileName("PatientExample",
        "telecom.where(use='mobile').system.first()",
        patientBuilder, jsonFormatBase);

    fhirPathProtoEvaluator.evaluateProtoTxtFileName("Appointment-example",
        "participant.actor.display.substring(0,5)",
        Appointment.newBuilder(), jsonFormatBase);

//    File file = jsonFormatBase.getExampleFile("PatientExample", FileType.PROTOBINARY);
//
//    jsonFormatBase.createProtoBinaryFile("PatientExample", Patient.newBuilder());
//
//    fhirPathProtoEvaluator.evaluateBinaryResource(file, "name.given",
//        Patient.newBuilder());


  }


}
