package com.progastination.web;

import com.progastination.dto.ProductDto;
import com.progastination.dto.ProductFilterDto;
import com.progastination.service.BuckwheatService;
import com.progastination.service.ProductService;
import com.progastination.utils.pagination.PageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/buckwheat")
public class BuckwheatController {
    private final BuckwheatService buckwheatService;

    @GetMapping("/search")
    public List<ProductDto> buckwheatSearch() {
        log.info("Request on getting buckwheat");
        return buckwheatService.buckwheatSearch();
    }

}
