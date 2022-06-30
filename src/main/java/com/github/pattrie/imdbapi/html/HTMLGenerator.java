package com.github.pattrie.imdbapi.html;

import static j2html.TagCreator.head;
import static j2html.TagCreator.link;
import static j2html.TagCreator.meta;

import com.github.pattrie.imdbapi.Content;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {

  private final Writer writer;

  public HTMLGenerator(Writer writer) {
    this.writer = writer;
  }

  public void generate(final List<? extends Content> movies) throws IOException {

    final String head =
        head(
                meta()
                    .withCharset("utf-8")
                    .withName("viewport")
                    .withContent("width=device-width, initial-scale=1, shrink-to-fit=no")
                    .withTitle("Top 250 Movies - IMDB"),
                link()
                    .withRel("stylesheet")
                    .withHref(
                        "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"))
            .render();

    writer.append(head);

    writer.append("<body>\n" + "<div class=\"row\">");

    movies.forEach(
        movie -> {
          final String body =
              String.format(
                  "\n<div class=\"card text-white bg-dark mb-3\" style=\"width: 18rem;\">\n"
                      + " <div class=\"card-body\">\n"
                      + "    <h5 class=\"card-text\">%s</h5>\n"
                      + "    <img class=\"card-img-top\" src=%s alt=\"%s\">\n"
                      + "    <p class=\"card-text\">Rating: %s Year: %s</p>\n"
                      + " </div>\n"
                      + "</div>\n",
                  movie.title(),
                  movie.urlImage(),
                  movie.title(),
                  movie.rating(),
                  movie.year());

          try {
            writer.append(body);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });

    writer.append("\n</div>" + "\n</body>");

    writer.close();
  }
}
