package nl.jpoint.quizapp;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ManagedService(path = "/quiz")
public final class Quiz{
    private static final Logger logger = LoggerFactory.getLogger(Quiz.class);

    /**
     * Invoked when the connection as been fully established and suspended, e.g ready for receiving messages.
     *
     * @param r the atmosphere resource
     */
    @Ready
    public final void onReady(final AtmosphereResource r){
        System.out.println("Browser connected: "+r.uuid());
    }

    /**
     * Invoked when the client disconnect or when an unexpected closing of the underlying connection happens.
     *
     * @param event the event
     */
    @Disconnect
    public final void onDisconnect(final AtmosphereResourceEvent event){
        if(event.isCancelled())
            logger.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
        else if(event.isClosedByClient())
            logger.info("Browser {} closed the connection", event.getResource().uuid());
    }

    @Message(encoders = {MessageEncoderDecoder.class}, decoders = {MessageEncoderDecoder.class})
    public final QuizMessage onMessage(final QuizMessage message) throws IOException{
        logger.info("Just send {}", message.getText());
        return message;
    }

}