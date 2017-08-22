package com.thelightstudiosparis.lumo.middleware.authentification;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import javax.ws.rs.NameBinding;

/**
 * création d'une annotation @Authentifie
 *
 */
@NameBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Authentifie {

}
