package com.example.dto;

import com.example.model.enums.DocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "document")
public class DocumentDto {

    @XmlAttribute(name = "series")
    @Pattern(regexp = "[0-9]{4}", message = "Введена некорректная серия паспорта")
    private String series;

    @XmlAttribute(name = "number")
    @Pattern(regexp = "[0-9]{6}", message = "Введен некорректный номер паспорта")
    private String number;

    @XmlAttribute(name = "issueDate")
    @NotNull(message = "issueDate is null")
    private String issueDate;

    @XmlAttribute(name = "type")
    @NotNull(message = "type is null")
    private DocumentType type;
}
