package tech.andreagreco.app.model;

import tech.andreagreco.app.dynamicbatis.mapping.Column;
import tech.andreagreco.app.dynamicbatis.mapping.Table;

/**
 * @author LikeGrees
 */
@Table(name = "users")
public class UserModelTwo {

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

}
