package com.data.app.row;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RowDirectRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public void deleteByIds(List<Long> ids) {
        Query query = entityManager.createNativeQuery("DELETE FROM rows_data WHERE id IN :rows");
        query.setParameter("rows", ids);
        query.executeUpdate();
    }

    public List<Row> getByIds(List<Long> ids) {
        List<Row> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder("SELECT id, title FROM rows_data WHERE id IN :ids");
        Query query = entityManager.createNativeQuery(builder.toString());
        query.setParameter("ids", ids);
        List<Object[]> rows = (List<Object[]>) query.getResultList();
        for (Object[] row : rows) {
            Row obj = new Row();
            obj.setId(Long.valueOf(String.valueOf(row[0])));
            obj.setTitle(String.valueOf(row[1]));
            res.add(obj);
        }
        return res;
    }
}
