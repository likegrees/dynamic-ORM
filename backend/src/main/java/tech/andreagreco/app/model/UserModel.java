package tech.andreagreco.app.model;

import tech.andreagreco.app.dynamicbatis.mapping.Column;
import tech.andreagreco.app.dynamicbatis.mapping.Table;

/**
 * @author LikeGrees
 */
@Table(name = "users")
public class UserModel {

    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;


}
