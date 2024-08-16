package com.project.scm.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

    String uploadImage(MultipartFile contactImage,String fileName);
    String getUrlFromPublicId(String publicId);
}
