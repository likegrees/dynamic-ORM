package tech.andreagreco.dynamicsql.sqlutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.andreagreco.dynamicsql.exception.TableNotDefinedException;
import tech.andreagreco.dynamicsql.sqlutil.mapping.Column;
import tech.andreagreco.dynamicsql.sqlutil.mapping.Table;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

@Configuration
public class SchemaMapper {

    @Value("${model.package.name}")
    private String modelPackageName;

    @Bean(name = "tablesMap")
    public Map<String, Map<String, String[]>> tablesMap() throws ClassNotFoundException, IOException {

        Map<String, Map<String, String[]>> tablesMap = new HashMap<>();

        //TODO: get this from application.properties
        List<Class<?>> classList = getClasses();
        classList.forEach(clazz -> {
            if(isAnnotated(clazz)){
                Map<String, String[]> tableColumns = new HashMap<>();

                tableColumns.put("tableName", getTable(clazz));
                tableColumns.put("columnsName", getColumns(clazz));

                tablesMap.put(clazz.getName(), tableColumns);
            }
        });

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

    //TODO: fix this bad thing
    private boolean isAnnotated(Class<?> clazz) {
        try{
            if (Objects.isNull(clazz)) {
                throw new TableNotDefinedException("The name of the table is null");
            }

            if (!clazz.isAnnotationPresent(Table.class)) {
                throw new TableNotDefinedException("The class "
                        + clazz.getSimpleName()
                        + " is not annotated with JsonSerializable");
            }

            return true;
        } catch(TableNotDefinedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Class<?>> getClasses()
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = modelPackageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, modelPackageName));
        }
        return classes;
    }
    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
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
