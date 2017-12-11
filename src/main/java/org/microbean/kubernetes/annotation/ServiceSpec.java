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
public @interface ServiceSpec {

  String clusterIP() default "";

  String[] externalIPs() default {};

  String externalName() default "";

  ExternalTrafficPolicy externalTrafficPolicy() default ExternalTrafficPolicy.Cluster;

  int healthCheckNodePort() default -1;

  String loadBalancerIP() default "";

  String[] loadBalancerSourceRanges() default {};

  Port port() default @Port; // simple override for ports[0]
  
  Port[] ports() default {};

  boolean publishNotReadyAddresses() default false;

  String[] selector() default {}; // using a string array here to fake an object

  SessionAffinity sessionAffinity() default SessionAffinity.None;

  SessionAffinityConfig sessionAffinityConfig() default @SessionAffinityConfig;

  Type type() default Type.ClusterIP;

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface SessionAffinityConfig {

    ClientIPConfig clientIP() default @ClientIPConfig;

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public static @interface ClientIPConfig {
      
      int timoutSeconds() default 10800;
      
    }
    
  }
  
  public static enum SessionAffinity {
    ClientIP, None;
  }

  public static enum ExternalTrafficPolicy {
    Local, Cluster;
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface Port {

    /**
     * The name of this port within the service. 
     *
     * <p>This must be a
     * DNS_LABEL.</p>
     *
     * <p>All ports within a {@code ServiceSpec} must have unique
     * names.</p>
     *
     * <p>This maps to the {@code Name} field in {@code EndpointPort}
     * objects.</p>
     *
     * <p>Optional if only one ServicePort is defined on this
     * service.</p>
     */
    String name() default ""; // DNS_LABEL

    int nodePort() default -1;

    int value() default -1; // maps to port

    Protocol protocol() default Protocol.TCP;

    String targetPort() default "";
    
    public static enum Protocol {
      TCP, UDP;
    }
    
  }
  
  public static enum Type {
    ClusterIP, NodePort, LoadBalancer;
  }
  
}
