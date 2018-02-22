package yncrea.pw08.web.init;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try (InputStream bannerStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("banner.txt")) {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(bannerStream));
            List<String> lines = buffer.lines().collect(Collectors.toList());
            Collections.reverse(lines);
            System.err.println(lines.stream().map(this::transform).collect(Collectors.joining("\n")));
        } catch (Exception e) {
        }
    }


    private String transform(final String s) {
        String replaced = s
                .replaceAll(charAt(121), charAt(95))
                .replaceAll(charAt(120), charAt(124))
                .replaceAll(charAt(122), charAt(47))
                .replaceAll(charAt(97), charAt(92) + charAt(92))
                .replaceAll(charAt(98), charAt(46))
                .replaceAll(charAt(99), charAt(41))
                .replaceAll(charAt(100), charAt(96))
                .replaceAll(charAt(101)+charAt(102)+charAt(103)+charAt(104)+charAt(105), charAt(32));
        return new StringBuilder(replaced).reverse().toString();
    }


    private String charAt(final int c) {
        return Character.toString((char) c);
    }
}
