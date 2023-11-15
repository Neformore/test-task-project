package com.example.dto;

import com.example.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PersonDto {

    @Pattern(regexp = "[А-Я][а-я]+", message = "Введено некорректное имя")
    private String name;

    @Pattern(regexp = "[А-Я][а-я]+", message = "Введена некорректная фамилия")
    private String surname;

    @Pattern(regexp = "[А-Я][а-я]+", message = "Введено некорректное отчество")
    private String patronymic;

    @NotNull(message = "birthDate is null")
    private String birthDate;

    @NotNull(message = "gender is null")
    private Gender gender;

    @NotNull(message = "document is null")
    private DocumentDto document;
}
