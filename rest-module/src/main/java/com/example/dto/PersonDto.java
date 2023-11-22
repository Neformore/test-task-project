package com.example.dto;

import com.example.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto {

    @XmlAttribute(name = "name")
    @Pattern(regexp = "[А-Я][а-я]+", message = "Введено некорректное имя")
    private String name;

    @XmlAttribute(name = "surname")
    @Pattern(regexp = "[А-Я][а-я]+", message = "Введена некорректная фамилия")
    private String surname;

    @XmlAttribute(name = "patronymic")
    @Pattern(regexp = "[А-Я][а-я]+", message = "Введено некорректное отчество")
    private String patronymic;

    @XmlAttribute(name = "birthDate")
    @NotNull(message = "birthDate is null")
    private String birthDate;

    @XmlAttribute(name = "gender")
    @NotNull(message = "gender is null")
    private Gender gender;

    @XmlElement(name = "document")
    @NotNull(message = "document is null")
    private DocumentDto document;
}
