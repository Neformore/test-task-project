package com.example.restpart.dto;

import com.example.restpart.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClientDto {

    @Pattern(regexp = "[А-Я][а-я]+", message = "Введено некорректное имя")
    private String name;

    @Pattern(regexp = "[А-Я][а-я]+", message = "Введена некорректная фамилия")
    private String surname;

    @Pattern(regexp = "[А-Я][а-я]+", message = "Введено некорректное отчество")
    private String patronymic;

    @NotNull
    private String birthDate;

    @NotNull
    private Gender gender;

    @NotNull
    private DocumentDto document;
}
