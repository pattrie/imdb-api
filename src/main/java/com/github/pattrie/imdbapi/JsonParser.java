package com.github.pattrie.imdbapi;

import java.util.List;

public interface JsonParser {
  List<? extends Content> parse();
}
