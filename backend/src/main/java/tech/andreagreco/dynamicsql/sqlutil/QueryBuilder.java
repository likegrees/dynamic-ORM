package tech.andreagreco.dynamicsql.sqlutil;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import tech.andreagreco.dynamicsql.exception.TableNotDefinedException;
import tech.andreagreco.dynamicsql.sqlutil.mapping.Column;
import tech.andreagreco.dynamicsql.sqlutil.mapping.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author LikeGrees
 */
@Component
public class QueryBuilder {

    private SQL query;

    QueryBuilder() {
        query = new SQL();
    }

    public QueryBuilder select(Class<?> table) {
        query.SELECT(getColumns(table)).FROM(getTable(table));
        return this;
    }

    public QueryBuilder where(String[] conditions) {
        query.WHERE(conditions);
        return this;
    }

    public SQL build() {
        return query;
    }

    private String getTable(Class<?> clazz) {

        return clazz.getAnnotation(Table.class).name();
    }

    //TODO: how to empty the list?
    private String[] getColumns(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> columns = new ArrayList<>();

        List.of(fields).forEach(field -> {
            if(field.isAnnotationPresent(Column.class)){
                columns.add(field.getAnnotation(Column.class).name());
            }
        });

        String[] col = columns.toArray(new String[]{});

        columns.clear();

        return col;
    }

    private void isAnnotated(Class<?> clazz) throws TableNotDefinedException {
        if (Objects.isNull(clazz)) {
            throw new TableNotDefinedException("The name of the table is null");
        }

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new TableNotDefinedException("The class "
                    + clazz.getSimpleName()
                    + " is not annotated with JsonSerializable");
        }
    }

}
