package com.example.dto;

import com.example.model.enums.DocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "document")
public class DocumentDto {

    @Pattern(regexp = "[0-9]{4}", message = "Введена некорректная серия паспорта")
    private String series;

    @Pattern(regexp = "[0-9]{6}", message = "Введен некорректный номер паспорта")
    private String number;

    @NotNull(message = "issueDate is null")
    private String issueDate;

    @NotNull(message = "type is null")
    private DocumentType type;

    @XmlAttribute(name = "series")
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @XmlAttribute(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlAttribute(name = "issueDate")
    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @XmlAttribute(name = "type")
    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }
}
