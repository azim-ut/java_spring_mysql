package com.data.app;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.app.row.Row;
import com.data.app.row.RowService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class AppApplication {

    private final RowService rowService;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @PostConstruct
    public void init() {
        this.rowService.create("test 1");
        this.rowService.create("test 2");
        this.rowService.create("test 3");
        this.rowService.create("test 4");
        this.rowService.delete(Arrays.asList(1L, 2L));
        List<Row> rows = this.rowService.getById(Arrays.asList(3L, 4L));
        rows.forEach(row -> {
            log.info("Row: {}", row);
        });
    }
}
