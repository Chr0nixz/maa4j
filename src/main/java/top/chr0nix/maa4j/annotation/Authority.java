package top.chr0nix.maa4j.annotation;

import top.chr0nix.maa4j.constant.AdminAuthority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {

    String[] value() default AdminAuthority.SUPER;

}
