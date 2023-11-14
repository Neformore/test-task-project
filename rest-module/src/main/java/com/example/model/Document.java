package com.example.model;

import com.example.model.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "document")
@Data
public class Document implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Column(name = "series")
    private int series;

    @Column(name = "number")
    private int number;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date issueDate;
}
