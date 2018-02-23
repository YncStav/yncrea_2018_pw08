package yncrea.pw08.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yncrea.pw08.web.data.Slide;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(
        value = "/control",
        decoders = {SlideDecoder.class},
        encoders = {SlideEncoder.class})
public class RevealController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RevealController.class);

    @OnOpen
    public void open(Session s) {
        send(s, CurrentSlideHolder.getInstance().getSlide());
    }


    @OnMessage
    public void slide(Slide slide, Session session) throws IOException {
        CurrentSlideHolder.getInstance().setSlide(slide);
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {

                send(s, slide);
            }
        }
    }


    private void send(Session session, Slide slide) {
        if(slide == null){
            LOGGER.debug("Slide was null");
            return;
        }
        try {
            LOGGER.info ("sending {} to {}",slide,session.getId());
            session.getBasicRemote().sendObject(slide);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
