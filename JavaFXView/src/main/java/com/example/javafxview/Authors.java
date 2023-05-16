package com.example.javafxview;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"author1", "Marcin Szymajda"},
            {"author2", "Maciej Wilczynski"}
    };
}