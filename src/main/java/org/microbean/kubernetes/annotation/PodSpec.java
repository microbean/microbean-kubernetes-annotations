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
public @interface PodSpec {

  int activeDeadlineSeconds() default -1;

  Affinity affinity() default @Affinity;

  boolean automountServiceAccountToken() default true;

  Container[] containers() default {};

  DnsPolicy dnsPolicy() default DnsPolicy.ClusterFirst;

  HostAlias[] hostAliases() default {};

  boolean hostIPC() default false;

  boolean hostNetwork() default false;

  boolean hostPID() default false;

  String hostname() default "";

  LocalObjectReference[] imagePullSecrets() default {};

  Container[] initContainers() default {};

  String nodeName() default "";

  Label[] nodeSelector() default {}; // TODO: really defined as "object", which means "JSON map"; Label[] is pretty close

  int priority() default 0;

  String priorityClassName() default "";

  RestartPolicy restartPolicy() default RestartPolicy.Always;

  String schedulerName() default "";

  PodSecurityContext securityContext() default @PodSecurityContext;

  String serviceAccountName() default "";

  String subdomain() default "";

  int terminationGracePeriodSeconds() default 30;

  Toleration[] tolerations() default {};

  Volume[] volumes() default {};
  
  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface Affinity {

    NodeAffinity nodeAffinity() default @NodeAffinity;

    PodAffinity podAffinity() default @PodAffinity;

    PodAntiAffinity podAntiAffinity() default @PodAntiAffinity;
    
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public static @interface NodeAffinity {

      PreferredSchedulingTerm[] preferredDuringSchedulingIgnoredDuringExecution() default {};

      NodeSelector requiredDuringSchedulingIgnoredDuringExecution() default @NodeSelector;

      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target({})
      public static @interface NodeSelector {

        NodeSelectorTerm[] nodeSelectorTerms() default {};
        
      }
      
      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target({})
      public static @interface PreferredSchedulingTerm {

        NodeSelectorTerm preference() default @NodeSelectorTerm;

        int weight() default -1;
        
      }
      
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public static @interface PodAffinity {

      WeightedPodAffinityTerm[] preferredDuringSchedulingIgnoredDuringExecution() default {};

      PodAffinityTerm[] requiredDuringSchedulingIgnoredDuringExecution() default {};
      
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public static @interface PodAntiAffinity {

      WeightedPodAffinityTerm[] preferredDuringSchedulingIgnoredDuringExecution() default {};

      PodAffinityTerm[] requiredDuringSchedulingIgnoredDuringExecution() default {};
      
    }
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface Container {

    String[] args() default {};

    String[] command() default {};

    EnvVar[] env() default {};

    EnvFromSource[] envFrom() default {};

    String image() default "";

    ImagePullPolicy imagePullPolicy() default ImagePullPolicy.IF_NOT_PRESENT;

    Lifecycle lifecycle() default @Lifecycle;

    Probe livenessProbe() default @Probe;

    String name() default ""; // required

    ContainerPort[] ports() default {};

    Probe readinessProbe() default @Probe;

    ResourceRequirements resources() default @ResourceRequirements;

    SecurityContext securityContext() default @SecurityContext;

    boolean stdin() default false;

    boolean stdinOnce() default false;

    String terminationMessagePath() default "/dev/termination-log";

    TerminationMessagePolicy terminationMessagePolicy() default TerminationMessagePolicy.FILE;

    boolean tty() default false; // requires stdin() to return true

    VolumeMount[] volumeMounts() default {};

    String workingDir() default "";
      
    public static enum ImagePullPolicy {

      ALWAYS, NEVER, IF_NOT_PRESENT;
        
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public static @interface Lifecycle {

      Handler postStart() default @Handler;

      Handler preStop() default @Handler;

      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target({})
      public static @interface Handler {

        ExecAction exec() default @ExecAction;

        HttpGetAction httpGet() default @HttpGetAction;

        TCPSocketAction tcpSocket() default @TCPSocketAction;
          
      }
        
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public static @interface Probe {

      ExecAction exec() default @ExecAction;

      int failureThreshold() default 3; // minimum legal value is 1

      HttpGetAction httpGet() default @HttpGetAction;

      int initialDelaySeconds() default 0; // not sure what the default is

      int periodSeconds() default 10;

      int successThreshold() default 1;

      TCPSocketAction tcpSocket() default @TCPSocketAction;

      int timeoutSeconds() default 1;

    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public @interface HttpGetAction {
        
      String host() default "";
        
      HttpHeader[] httpHeaders() default {};
        
      String path() default "";
        
      String port() default "";
        
      String scheme() default "http";
        
      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target({})
      public @interface HttpHeader {
          
        String name() default "";
          
        String value() default "";
          
      }
        
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public @interface ExecAction {
        
      String[] command() default {};
        
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public @interface TCPSocketAction {
        
      String host() default "";
        
      String port() default "";
        
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public @interface ContainerPort {

      int containerPort() default -1;

      String hostIP() default "";

      int hostPort() default -1;

      String name() default "";

      Protocol protocol() default Protocol.TCP;

      public static enum Protocol {
        TCP, UDP;
      }
        
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public @interface SecurityContext {

      boolean allowPrivilegeEscalation() default false;

      Capabilities capabilities() default @Capabilities;

      boolean privileged() default false;

      boolean readOnlyRootFilesystem() default false;

      boolean runAsNonRoot() default false;

      int runAsUser() default -1;

      SELinuxOptions seLinuxOptions() default @SELinuxOptions;
        
      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target({})
      public @interface Capabilities {

        String[] add() default {};

        String[] drop() default {};

      }        
        
    }

    public static enum TerminationMessagePolicy {
      FILE, FALLBACK_TO_LOGS_ON_ERROR;
    }
  }

  public static enum DnsPolicy {
    ClusterFirstWithHostNet, ClusterFirst, Default;
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public @interface HostAlias {

    String[] hostnames() default {};

    String ip() default "";
    
  }

  public enum RestartPolicy {
    Always, OnFailure, Never;
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public @interface PodSecurityContext {

    int fsGroup() default -1;

    boolean runAsNonRoot() default false;

    int runAsUser() default -1;

    SELinuxOptions seLinuxOptions() default @SELinuxOptions;
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public @interface Toleration {

    String effect() default "";

    String key() default "";

    Operator operator() default Operator.Equal;

    int tolerationSeconds() default -1;

    String value() default "";
    
    public static enum Operator {
      Exists, Equal;
    }
    
  }
  
}
