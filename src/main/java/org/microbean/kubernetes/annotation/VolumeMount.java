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
public @interface VolumeMount {

  String mountPath() default "";

  MountPropagation mountPropagation() default MountPropagation.HOST_TO_CONTAINER;

  String name() default "";

  boolean readOnly() default false;

  String subPath() default "";
  
  /**
   * @see <a
   * href="https://kubernetes.io/docs/concepts/storage/volumes/#mount-propagation">Mount
   * Propagation</a>
   */
  public static enum MountPropagation {
    HOST_TO_CONTAINER, BIDIRECTIONAL;
  }

}
