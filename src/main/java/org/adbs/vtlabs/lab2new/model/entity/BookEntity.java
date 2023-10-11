package org.adbs.vtlabs.lab2new.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class BookEntity {
    public static String COLUMN_BOOK_ID = "BOOK_ID";
    public static String COLUMN_NAME = "NAME";
    public static String COLUMN_AUTHOR = "AUTHOR";
    public static String COLUMN_PRICE = "PRICE";
    public static String COLUMN_DESCRIPTION = "description";

    private Long bookId;
    private String name;
    private String author;
    private BigDecimal price;
    private String description;
}
