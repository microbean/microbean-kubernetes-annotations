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
public @interface Volume {

  AWSElasticBlockStoreVolumeSource awsElasticBlockStore() default @AWSElasticBlockStoreVolumeSource;

  AzureDiskVolumeSource azureDisk() default @AzureDiskVolumeSource;

  AzureFileVolumeSource azureFile() default @AzureFileVolumeSource;

  CephFSVolumeSource cephfs() default @CephFSVolumeSource;

  CinderVolumeSource cinder() default @CinderVolumeSource;

  ConfigMapVolumeSource configMap() default @ConfigMapVolumeSource;

  DownwardAPIVolumeSource downwardAPI() default @DownwardAPIVolumeSource;

  EmptyDirVolumeSource emptyDir() default @EmptyDirVolumeSource;

  FCVolumeSource fc() default @FCVolumeSource;

  FlexVolumeSource flexVolume() default @FlexVolumeSource;

  FlockerVolumeSource flocker() default @FlockerVolumeSource;

  GCEPersistentDiskVolumeSource gcePersistentDisk() default @GCEPersistentDiskVolumeSource;

  GitRepoVolumeSource gitRepo() default @GitRepoVolumeSource;

  GlusterfsVolumeSource glusterfs() default @GlusterfsVolumeSource;

  HostPathVolumeSource hostPath() default @HostPathVolumeSource;

  ISCSIVolumeSource iscsi() default @ISCSIVolumeSource;

  String name() default "";

  NFSVolumeSource nfs() default @NFSVolumeSource;

  PersistentVolumeClaimVolumeSource persistentVolumeClaim() default @PersistentVolumeClaimVolumeSource;

  PhotonPersistentDiskVolumeSource photonPersistentDisk() default @PhotonPersistentDiskVolumeSource;

  PortworxVolumeSource portworxVolume() default @PortworxVolumeSource;

  ProjectedVolumeSource projected() default @ProjectedVolumeSource;

  QuobyteVolumeSource quobyte() default @QuobyteVolumeSource;

  RBDVolumeSource rbd() default @RBDVolumeSource;

  ScaleIOVolumeSource scaleIO() default @ScaleIOVolumeSource;

  SecretVolumeSource secret() default @SecretVolumeSource;

  StorageOSVolumeSource storageos() default @StorageOSVolumeSource;

  VsphereVirtualDiskVolumeSource vsphereVolume() default @VsphereVirtualDiskVolumeSource;
  
  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface AzureFileVolumeSource {
    
    boolean readOnly() default false;
    
    String secretName() default "";
    
    String shareName() default "";
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface CephFSVolumeSource {
    
    String[] monitors() default {};
    
    String path() default "/";
    
    boolean readOnly() default false;
    
    String secretFile() default "/etc/ceph/user.secret";
    
    LocalObjectReference secretRef() default @LocalObjectReference;
    
    String user() default "admin";
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface ConfigMapVolumeSource {

    int defaultMode() default 0644;

    KeyToPath[] items() default {};

    String name() default "";

    boolean optional() default false;

  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface DownwardAPIVolumeSource {

    int defaultMode() default 0644;

    DownwardAPIVolumeFile[] items() default {};
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface EmptyDirVolumeSource {

    String medium() default "";

    String quantity() default ""; // defined as type Quantity
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface GitRepoVolumeSource {

    String directory() default "";

    String repository() default "";

    String revision() default "";
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface PersistentVolumeClaimVolumeSource {

    String claimName() default "";

    boolean readOnly() default false;
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface ProjectedVolumeSource {

    int defaultMode() default -1;

    VolumeProjection[] sources() default {};

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public static @interface VolumeProjection {

      ConfigMapProjection configMap() default @ConfigMapProjection;

      DownwardAPIProjection downwardAPI() default @DownwardAPIProjection;

      SecretProjection secret() default @SecretProjection;
      
      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target({})
      public static @interface ConfigMapProjection {

        KeyToPath[] items() default {};

        String name() default "";

        boolean optional() default false;
        
      }

      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target({})
      public static @interface DownwardAPIProjection {

        DownwardAPIVolumeFile[] items() default {};
        
      }

      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target({})
      public static @interface SecretProjection {

        KeyToPath[] items() default {};

        String name() default "";

        boolean optional() default false;
        
      }
      
    }
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface SecretVolumeSource {

    int defaultMode() default 0644;

    KeyToPath[] items() default {};

    boolean optional() default false;

    String secretName() default "";
    
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({})
  public static @interface StorageOSVolumeSource {

    String fsType() default "";

    boolean readOnly() default false;

    LocalObjectReference secretRef() default @LocalObjectReference;

    String volumeName() default "";

    String volumeNamespace() default "";
    
  }
  
}
