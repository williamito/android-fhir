import com.google.fhir.common.InvalidFhirException;
import com.google.fhir.r4.core.Patient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.hl7.fhir.r5.model.HumanName;

public class Main {

  public static void main(String[] args) throws IOException, InvalidFhirException {
//    new JsonFormatGenerate()
//        .generateProtoTxt(new String[] {"label"}, null);

    File filename = new File("/android-fhir/filename.prototxt").getAbsoluteFile();

//    String parsedFilename = new JsonFormatBase().parseToJson(filename, Patient.newBuilder());
//
//    System.out.println(new JsonProcess2().processJSON(parsedFilename, "name.count()"));
    File newFile2 = new File("/android-fhir/testbinary.proto");

    Patient newPatient = Patient.parseFrom(new FileInputStream(newFile2));

    String firstPatient = new JsonFormatBase().parseToJson(newPatient);

    System.out.println(new ProtoFHIRPathFiles().processJSON(firstPatient,
        "name.given"));

//    System.out.println(new ProtoFHIRPathFiles().evaluate(filename,
//        "name.where(use='official')"));

//    System.out.println(((HumanName) (new ProtoFHIRPathFiles().evaluate(filename,
//        "name.where(use='official')")).get(0)).getFamily());
//
//    System.out.println(new ProtoFHIRPathFiles().evaluate("id {\n"
//        + "  value: \"examle\"\n"
//        + "}\n"
//        + "active {\n"
//        + "  value: true\n"
//        + "}\n"
//        + "name {\n"
//        + "  use {\n"
//        + "    value: OFFICIAL\n"
//        + "  }\n"
//        + "  family {\n"
//        + "    value: \"Chalmers\"\n"
//        + "  }\n"
//        + "  given {\n"
//        + "    value: \"Peter\"\n"
//        + "  }\n"
//        + "  given {\n"
//        + "    value: \"James\"\n"
//        + "  }\n"
//        + "}\n"
//        + "name {\n"
//        + "  use {\n"
//        + "    value: USUAL\n"
//        + "  }\n"
//        + "  given {\n"
//        + "    value: \"Jim\"\n"
//        + "  }\n"
//        + "}\n"
//        + "telecom {\n"
//        + "  system {\n"
//        + "    value: PHONE\n"
//        + "  }\n"
//        + "  value {\n"
//        + "    value: \"(03) 3410 5613\"\n"
//        + "  }\n"
//        + "  use {\n"
//        + "    value: MOBILE\n"
//        + "  }\n"
//        + "  rank {\n"
//        + "    value: 2\n"
//        + "  }\n"
//        + "}\n"
//        + "gender {\n"
//        + "  value: MALE\n"
//        + "}\n"
//        + "birth_date {\n"
//        + "  value_us: 157161600000000\n"
//        + "  timezone: \"Europe/London\"\n"
//        + "  precision: DAY\n"
//        + "  extension {\n"
//        + "    url {\n"
//        + "      value: \"http://hl7.org/fhir/StructureDefinition/patient-birthTime\"\n"
//        + "    }\n"
//        + "    value {\n"
//        + "      date_time {\n"
//        + "        value_us: 157232145000000\n"
//        + "        timezone: \"-05:00\"\n"
//        + "        precision: SECOND\n"
//        + "      }\n"
//        + "    }\n"
//        + "  }\n"
//        + "}\n"
//        + "deceased {\n"
//        + "  boolean {\n"
//        + "  }\n"
//        + "}\n"
//        + "address {\n"
//        + "  use {\n"
//        + "    value: HOME\n"
//        + "  }\n"
//        + "  type {\n"
//        + "    value: BOTH\n"
//        + "  }\n"
//        + "  text {\n"
//        + "    value: \"534 Erewhon St PeasantVille, Rainbow, Vic  3999\"\n"
//        + "  }\n"
//        + "  line {\n"
//        + "    value: \"534 Erewhon St\"\n"
//        + "  }\n"
//        + "  city {\n"
//        + "    value: \"PleasantVille\"\n"
//        + "  }\n"
//        + "  district {\n"
//        + "    value: \"Rainbow\"\n"
//        + "  }\n"
//        + "  state {\n"
//        + "    value: \"Vic\"\n"
//        + "  }\n"
//        + "  postal_code {\n"
//        + "    value: \"3999\"\n"
//        + "  }\n"
//        + "  period {\n"
//        + "    start {\n"
//        + "      value_us: 157161600000000\n"
//        + "      timezone: \"Europe/London\"\n"
//        + "      precision: DAY\n"
//        + "    }\n"
//        + "  }\n"
//        + "}\n", "name.count()"));
  }

}
