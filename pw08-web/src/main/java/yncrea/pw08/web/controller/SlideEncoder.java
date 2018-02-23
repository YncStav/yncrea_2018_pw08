package yncrea.pw08.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yncrea.pw08.web.data.Slide;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class SlideEncoder implements Encoder.Text<Slide>{


     private final static Logger LOGGER = LoggerFactory.getLogger(SlideEncoder.class);
    private ObjectMapper objectMapper;

    @Override
    public String encode(Slide slide) throws EncodeException {
        try {
            String s = objectMapper.writeValueAsString(slide);
            LOGGER.debug("Encoded {} to '{}'",slide,s);
            return s;
        }catch (JsonProcessingException e){
            LOGGER.error("Error while encoding{}",slide);
            return "FAILED";
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void destroy() {

    }
}
