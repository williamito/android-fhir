import com.google.fhir.r4.core.Patient;
import com.google.fhir.r4.core.PatientOrBuilder;
import com.google.fhir.shaded.protobuf.AbstractMessage;
import com.google.fhir.stu3.proto.Patient.Builder;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import java.io.IOException;

public class PatientAdapter extends TypeAdapter<Patient> {

  @Override
  public Patient read(JsonReader jsonReader) throws IOException {
    Patient.Builder patientBuilder = Patient.newBuilder();

    AbstractMessage.Builder patientBuilder2 =
        com.google.fhir.stu3.proto.Patient.newBuilder();

//    GeneratedMessageV3.Builder<com.google.fhir.stu3.proto.Patient.Builder> patientBuilder3
//        = com.google.fhir.stu3.proto.Patient.newBuilder();

    JsonParser jsonParser = new JsonParser();

    JsonFormat.parser().merge(jsonParser.parse(jsonReader).toString(),
        (Message.Builder) patientBuilder2);

    return patientBuilder.build();
  }

  @Override
  public void write(JsonWriter jsonWriter, Patient patient) throws IOException {
    jsonWriter.jsonValue(
        JsonFormat.printer().print((MessageOrBuilder) patient.getBirthDateOrBuilder()));

//    patient.ge

  }
}
