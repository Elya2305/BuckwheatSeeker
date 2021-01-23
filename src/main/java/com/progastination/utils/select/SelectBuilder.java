package com.progastination.utils.select;

import com.progastination.exception.SqlRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * 23.01.21
 *
 * @author Elya
 * <p>
 * v1.0
 * <p>
 * available where conditions with operations equals/like/in, joined with the same condition (wait for improvements soon)
 */

@Data
public class SelectBuilder {
    private String table;
    private String limit;
    private String offset;
    private JoinCondition joinCondition;
    private Map<SelectCondition, List<Column>> where = new HashMap<>();
    private Map<String, SortPrice> sort = new HashMap<>();

    public SelectBuilder table(String table) {
        this.table = table;
        return this;
    }

    public SelectBuilder where(String field, String value, SelectCondition condition) {
        if (checkEmpty(field) || isNull(value) || value.isEmpty()) return this;
        List<Column> columns = where.getOrDefault(condition, new ArrayList<>());
        columns.add(Column.of(field, Collections.singletonList(value)));
        where.put(condition, columns);
        return this;
    }

    public SelectBuilder where(String field, List<?> values, SelectCondition condition) {
        if (checkEmpty(field) || isNull(values) || values.isEmpty()) return this;
        List<Column> columns = where.getOrDefault(condition, new ArrayList<>());
        columns.add(Column.of(field, convert(values)));
        where.put(condition, columns);
        return this;
    }


    public SelectBuilder limit(Integer limit) {
        if (isNull(limit)) return this;
        this.limit = String.valueOf(limit);
        return this;
    }

    public SelectBuilder offset(Integer offset) {
        if (isNull(offset)) return this;
        this.offset = String.valueOf(offset);
        return this;
    }

    public SelectBuilder condition(JoinCondition condition) {
        this.joinCondition = condition;
        return this;
    }

    public SelectBuilder orderBy(String field, SortPrice sort) {
        if (isNull(field) || isNull(sort)) return this;
        this.sort.put(field, sort);
        return this;
    }

    public String build() {
        checkBuild();
        StringBuilder select = new StringBuilder();
        select.append("SELECT * FROM ").append(table);
        if (!where.isEmpty()) {
            select.append(" WHERE ");
            String where = buildWhere();
            select.append(where);
        }
        if (nonNull(limit)) {
            select.append(" LIMIT ").append(limit);
        }
        if (nonNull(offset)) {
            select.append(" OFFSET ").append(offset);
        }
        if (!sort.isEmpty()) {
//            select.append(" ORDER BY " + sort.get())
        }
        return select.toString();
    }

    /**
     * -> v1.0
     * join columns with the same condition, then join 'joined' columns
     */
    private String buildWhere() {
        return this.where.entrySet().stream().map(entry -> entry.getValue().stream().map(o ->
                String.format(entry.getKey().pattern, o.getFiled(), convertValues(o.getValue())))
                .collect(Collectors.joining(getConditionOrDefault())))
                .collect(Collectors.joining(getConditionOrDefault()));
    }

    private String convertValues(List<String> values) {
        return String.join(",", values);
    }

    private List<String> convert(List<?> values) {
        return values.stream().map(o -> {
            if (o instanceof String) return "'" + o + "'";
            return String.valueOf(o);
        }).collect(Collectors.toList());
    }

    private void checkBuild() {
        if (isNull(table) || table.isEmpty()) {
            throw new SqlRequestException("Table name is empty");
        }
    }

    private boolean checkEmpty(String value) {
        return isNull(value) || value.isEmpty();
    }

    private String getConditionOrDefault() {
        return isNull(joinCondition) ? JoinCondition.AND.getOperation() : joinCondition.getOperation();
    }


    @Data(staticConstructor = "of")
    @AllArgsConstructor(staticName = "of")
    private static class Column {
        private final String filed;
        private final List<String> value;
    }


    @Getter
    public enum SelectCondition {
        EQ("=", "%s=%s"),
        LIKE("LIKE", "%s LIKE '%%%s%%'"),
        IN("IN", "%s IN(%s)");

        private final String operation;
        private final String pattern;

        SelectCondition(String operation, String pattern) {
            this.operation = operation;
            this.pattern = pattern;
        }
    }

    @Getter
    public enum JoinCondition {
        AND(" AND "), OR(" OR ");

        private final String operation;

        JoinCondition(String operation) {
            this.operation = operation;
        }
    }
}
