package com.iwomi.projet.file.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FileImage")
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "File-Image", length = 1000)
    private byte[] fileImage;
}