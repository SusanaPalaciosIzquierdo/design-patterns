package com.kreitek.editor.formatos;

import com.kreitek.editor.BadFactoryException;

public class FormatFactoryProducer {
    public FormatoFactory getFactory(String type) throws BadFactoryException {
        switch (type) {
            case "text":
                return new FormatoTextoFactory();
            case "json":
                return new FormatoJsonFactory();
            default:
                throw new BadFactoryException();

        }
    }
}
