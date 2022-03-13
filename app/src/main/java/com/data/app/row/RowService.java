package com.data.app.row;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RowService {

    private final RowRepository rowRepository;
    private final RowDirectRepository rowDirectRepository;

    public Row create(String title) {
        Row row = new Row();
        row.setTitle(title);
        return rowRepository.save(row);
    }

    public void delete(List<Long> ids) {
        rowDirectRepository.deleteByIds(ids);
    }

    public List<Row> getById(List<Long> ids) {
        return rowDirectRepository.getByIds(ids);
    }

    public void save(Row row) {
        rowRepository.save(row);
    }
}
