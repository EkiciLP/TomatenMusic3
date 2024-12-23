package net.tomatentum.tomatenmusic3;

import java.io.PrintWriter;

import org.jline.terminal.Terminal;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

public class JlineAppender extends ConsoleAppender<ILoggingEvent> {

    public static Terminal Terminal;

    @Override
    protected void append(ILoggingEvent event) {
        PrintWriter terminalWriter = Terminal.writer();
        terminalWriter.write(new String(super.encoder.encode(event)));
        terminalWriter.flush();
    }
    
}
