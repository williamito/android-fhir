import com.google.fhir.shaded.common.base.CaseFormat;
import com.google.fhir.shaded.common.base.Converter;
import com.google.fhir.shaded.gson.JsonElement;
import com.google.fhir.shaded.gson.JsonSerializationContext;
import com.google.fhir.shaded.gson.JsonSerializer;
import com.google.protobuf.Extension;
import com.google.protobuf.GeneratedMessage;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class ProtoTypeAdapter2 implements JsonSerializer<GeneratedMessage> {

  public static enum EnumSerialization {
    NUMBER,
    NAME;
  }

  public static class Builder {
    private final Set<Extension> serializedNameExtensions;
    private final Set<Extension> seralizedEnumValueExtensions;
    private EnumSerialization enumSerialization;
    private Converter<String, String> fieldNameSerializationFormat;

    public Builder(
        EnumSerialization enumSerialization,
        CaseFormat fromFieldNameFormat, CaseFormat toFieldNameFormat) {
      this.serializedNameExtensions = new HashSet<>();
      this.seralizedEnumValueExtensions = new HashSet<>();
      this.enumSerialization = enumSerialization;
      setFieldNameSerializationFormat(fromFieldNameFormat, toFieldNameFormat);
    }

    public Builder setFieldNameSerializationFormat(CaseFormat fromFieldNameFormat,
        CaseFormat toFieldNameFormat) {
      fieldNameSerializationFormat = fromFieldNameFormat.converterTo(toFieldNameFormat);
      return this;
    }
  }

  @Override
  public JsonElement serialize(GeneratedMessage generatedMessage, Type type,
      JsonSerializationContext jsonSerializationContext) {
    return null;
  }
}
