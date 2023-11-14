package com.example.restpart.dto;

import com.example.restpart.model.enums.DocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class DocumentDto {

    @Pattern(regexp = "[0-9]{4}", message = "Введена некорректная серия паспорта")
    private int series;

    @Pattern(regexp = "[0-9]{6}", message = "Введен некорректный номер паспорта")
    private int number;

    @NotNull
    private String issueDate;

    @NotNull
    private DocumentType type;
}
