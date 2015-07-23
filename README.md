# yocto-xenomai
Yocto BSP collection for xenomai 3.0 support

Instructions

Download sources

  git clone -b fido http://git.yoctoproject.org/git/poky
  cd poky
  git clone -b master https://github.com/ciesla/yocto-xenomai.git

Setup environnement

  source oe-init-build-env

Add layers in yocto configuration (build\conf\bblayers.conf)

  $install-path$/poky/yocto-xenomai/meta-raspberrypi \
  $install-path$/poky/yocto-xenomai/meta-xenomai \
             
Select machine in yocto configuration (build\conf\local.conf)

  MACHINE ?= "raspberrypi2"

Start yocto

  bitbake core-image-minimal
