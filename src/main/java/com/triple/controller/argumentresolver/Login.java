package com.triple.controller.argumentresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)      // 파라미터에만 사용하겠음
@Retention(RetentionPolicy.RUNTIME) // 리플렉션 등 활용할 수 있도록 런타임까지 어노테이션 정보가 남아있음
public @interface Login {
}
