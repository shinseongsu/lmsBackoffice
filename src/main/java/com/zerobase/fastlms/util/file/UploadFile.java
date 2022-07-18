package com.zerobase.fastlms.util.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {
    private String originFileName;
    private String storeFileName;
    private String fileUploadUrl;


}
