/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2017 MicroBean.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.microbean.kubernetes.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.util.Objects;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface NodeSelectorTerm {

  NodeSelectorRequirement[] matchExpressions() default {};
  
  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public @interface NodeSelectorRequirement {

    String key() default "";
    
    String[] values() default {};
    
    Operator operator() default Operator.NONE;
    
    public static enum Operator {
      
      NONE, IN, NOT_IN, EXISTS, DOES_NOT_EXIST, GT, LT;
      
    }

  }

}
