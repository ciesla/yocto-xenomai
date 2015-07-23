LINUX_VERSION ?= "3.18.12"

SRCREV = "b054bbf075793ed1110b84e11f25d731e2a060c2"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.18.y"

#xenomai source (prepare_kernel.sh script)
SRC_URI += "file://ipipe-core-3.18.12-arm-2.patch"

SRC_URI += "http://xenomai.org/downloads/xenomai/testing/latest/xenomai-3.0-rc5.tar.bz2"
SRC_URI[md5sum] = "fcc997bb5c5c286e4524977d4f992cf3"
SRC_URI[sha256sum] = "8447a6cb99fe9c33deace109ae61227050ed3c35343221f964bf37f32bbfed7a"
		  
require linux-raspberrypi.inc

EXTRA_OECONF += "--enable-smp"

do_prepare_kernel () {

    #set linux kernel source directory
    linux_src="${S}"

    #set xenomai source directory 
    xenomai_src="${TMPDIR}/work/${MACHINE}-poky-${TARGET_OS}/${PN}/${EXTENDPE}${PV}-${PR}/xenomai-3.0-rc5"

    #prepare kernel
	# arch=arm
    $xenomai_src/scripts/prepare-kernel.sh --arch=arm \
	--linux=$linux_src
	
	cd ${S}
	git add -A
	git commit -m "add xenomai files to repository"
}

addtask prepare_kernel after do_patch before do_kernel_configme
