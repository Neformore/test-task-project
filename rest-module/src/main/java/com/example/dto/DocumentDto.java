package com.example.dto;

import com.example.model.enums.DocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class DocumentDto {

    @Pattern(regexp = "[0-9]{4}", message = "Введена некорректная серия паспорта")
    private int series;

    @Pattern(regexp = "[0-9]{6}", message = "Введен некорректный номер паспорта")
    private int number;

    @NotNull(message = "issueDate is null")
    private String issueDate;

    @NotNull(message = "type is null")
    private DocumentType type;
}
