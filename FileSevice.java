package com.iwomi.projet.file.service;

import com.iwomi.projet.file.model.FileImage;

public interface FileSevice {

    FileSevice rename (long id , FileImage fileImage);

    String delete (long id);
}
