import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtoJsonUtils {

  public static String toJson(Message sourceMessage) throws InvalidProtocolBufferException {
    return JsonFormat.printer().print(sourceMessage);
  }

  public static Message toProto(Message.Builder targetBuilder, String json)
      throws InvalidProtocolBufferException {
    JsonFormat.parser().merge(json, targetBuilder);
    return targetBuilder.build();
  }

}
