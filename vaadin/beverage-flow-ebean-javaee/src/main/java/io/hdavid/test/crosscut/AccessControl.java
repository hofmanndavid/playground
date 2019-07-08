package io.hdavid.test.crosscut;

import io.hdavid.test.entity.User;
import io.hdavid.test.entity.query.QUser;

import javax.ejb.Stateless;

@Stateless
public class AccessControl {


    public User attemptLogin(String username, String password) {
        // todo, if wrong we should increment loginattemp
        return new QUser().username.eq(username).password.eq(password).findOne();
    }


}
