import com.google.fhir.r4.core.Address;
import com.google.fhir.r4.core.Boolean;
import com.google.fhir.r4.core.Date;
import com.google.fhir.r4.core.HumanName;
import com.google.fhir.r4.core.Identifier;
import com.google.fhir.r4.core.String;
import com.google.fhir.r4.core.Patient;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.AbstractMessage.Builder;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class PatientExample {
  
  public static void main(java.lang.String[] args) throws Exception {
    String name = String.newBuilder().setValue("Kent").build();

    HumanName humanName = HumanName.newBuilder().addGiven(name).build();

    Date newDate = Date.newBuilder().setValueUs(1628174386).build();

    Address.Builder addressBuilder = Address.newBuilder();

    String city = String.newBuilder().setValue("London").build();

    Address address = addressBuilder.setCity(city).build();

    Identifier identifier
        = Identifier.newBuilder().setValue(String.newBuilder().setValue("1600ax").build()).build();

    Boolean trueValue = Boolean.newBuilder().setValue(true).build();

    Patient newPatient = Patient.newBuilder().addAddress(address)
        .addName(humanName).addIdentifier(identifier).setBirthDate(newDate)
        .setActive(trueValue).build();


    System.out.println();

    System.out.println(newPatient);

    java.lang.String fhirJson = "{ \"active\": true, \"deceasedBoolean\": false, \"gender\": \"male\", \"address\": [ { \"use\": \"home\", \"period\": { \"start\": \"1974-12-25\" }, \"postalCode\": \"3999\", \"type\": \"both\", \"district\": \"Rainbow\", \"line\": [ \"534 Erewhon St\" ], \"text\": \"534 Erewhon St PeasantVille, Rainbow, Vic 3999\", \"state\": \"Vic\", \"city\": \"PleasantVille\" } ], \"id\": \"examle\", \"name\": [ { \"use\": \"official\", \"family\": \"Chalmers\", \"given\": [ \"Peter\", \"James\" ] }, { \"given\": [ \"Jim\" ], \"use\": \"usual\" } ], \"_birthDate\": { \"extension\": [ { \"url\": \"http://hl7.org/fhir/StructureDefinition/patient-birthTime\", \"valueDateTime\": \"1974-12-25T14:35:45-05:00\" } ] }, \"resourceType\": \"Patient\", \"telecom\": [ { \"rank\": 2, \"value\": \"(03) 3410 5613\", \"system\": \"phone\", \"use\": \"mobile\" } ], \"birthDate\": \"1974-12-25\" }";
    
  }



}
