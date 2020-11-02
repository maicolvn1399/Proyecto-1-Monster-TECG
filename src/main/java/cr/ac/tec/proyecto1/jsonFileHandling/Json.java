package cr.ac.tec.proyecto1.jsonFileHandling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {

    private static ObjectMapper objectMapper = getObjectMapper();

    private static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    public static JsonNode parse(String jsonSource) throws IOException {
        return objectMapper.readTree(jsonSource);
    }

    public static <A> A fromJson(JsonNode node, Class<A> aClass) throws JsonProcessingException{
        return objectMapper.treeToValue(node,aClass);
    }



}
