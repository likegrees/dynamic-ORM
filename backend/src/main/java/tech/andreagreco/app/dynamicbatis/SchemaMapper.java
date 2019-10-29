package tech.andreagreco.app.dynamicbatis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.andreagreco.app.dynamicbatis.exception.TableNotDefinedException;
import tech.andreagreco.app.dynamicbatis.mapping.Column;
import tech.andreagreco.app.dynamicbatis.mapping.Table;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Configuration
public class SchemaMapper {

    @Value("${model.package.name}")
    private String modelPackageName;

    @Bean(name = "tablesMap")
    public Map<String, Map<String, String[]>> tablesMap() throws ClassNotFoundException, IOException,
            TableNotDefinedException {

        Map<String, Map<String, String[]>> tablesMap = new HashMap<>();

        List<Class<?>> classList = getClasses();

        for (Class<?> clazz : classList) {
            isAnnotated(clazz);
            Map<String, String[]> tableColumns = new HashMap<>();

            tableColumns.put("tableName", getTable(clazz));
            tableColumns.put("columnsName", getColumns(clazz));

            tablesMap.put(clazz.getName(), tableColumns);
        }
        return tablesMap;
    }

    private String[] getTable(Class<?> clazz) {

        return new String[]{clazz.getAnnotation(Table.class).name()};
    }

    private String[] getColumns(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> columns = new ArrayList<>();

        List.of(fields).forEach(field -> {
            if(field.isAnnotationPresent(Column.class)){
                columns.add(field.getAnnotation(Column.class).name());
            }
        });

        return columns.stream().toArray(String[]::new);
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

    private List<Class<?>> getClasses()
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = modelPackageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, modelPackageName));
        }
        return classes;
    }

    // this will recursively find every class under the specified package
    // would be better to check from the base package instead specify the model package ???
    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
