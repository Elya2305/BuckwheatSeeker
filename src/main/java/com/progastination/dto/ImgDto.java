package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ImgDto {
    private String s350x350;

    public static ImgDto of(String s350x350) {
        ImgDto imgDto = new ImgDto();
        imgDto.setS350x350(s350x350);
        return imgDto;
    }
}

