package nl.jpoint.quizapp;

import org.atmosphere.config.managed.Decoder;
import org.atmosphere.config.managed.Encoder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class MessageEncoderDecoder implements Encoder<QuizMessage, String>, Decoder<String, QuizMessage>{

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public QuizMessage decode(String s) {
        try{
            return mapper.readValue(s, QuizMessage.class);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encode(QuizMessage message) {
        try{
            return mapper.writeValueAsString(message);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
