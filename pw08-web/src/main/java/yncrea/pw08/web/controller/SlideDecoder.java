package yncrea.pw08.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yncrea.pw08.web.data.Slide;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class SlideDecoder implements Decoder.Text<Slide> {
    private ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SlideDecoder.class);
    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void destroy() {
    }

    @Override
    public Slide decode(String s) throws DecodeException{
        try {
            Slide slide = objectMapper.readValue(s, Slide.class);
            LOGGER.debug("decoded '{}' to {}",s,slide);
            return slide;
        }catch (IOException e){
            LOGGER.error("DECODE exception for {}",s);
            return null;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        this.objectMapper = new ObjectMapper();
    }
}
