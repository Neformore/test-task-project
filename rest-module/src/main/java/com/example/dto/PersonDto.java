package com.example.dto;

import com.example.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
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

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlAttribute(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @XmlAttribute(name = "birthDate")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @XmlAttribute(name = "gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @XmlElement(name = "document")
    public DocumentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentDto document) {
        this.document = document;
    }
}
