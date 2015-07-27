SUMMARY = "A console-only image that fully supports the target device hardware with xenomai support."

IMAGE_FEATURES += "splash"
IMAGE_INSTALL += "xenomai"

LICENSE = "MIT"

inherit core-image

PREFERRED_PROVIDER_virtual/kernel = "linux-yocto-xenomai"
