LINUX_VERSION ?= "3.18.12"

SRCREV = "b054bbf075793ed1110b84e11f25d731e2a060c2"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.18.y"

#xenomai source (prepare_kernel.sh script)
SRC_URI += "file://ipipe-core-3.18.12-arm-1.patch"
SRC_URI += "file://sl030raspberrypii2ckernel.patch"
SRC_URI += "http://download.gna.org/xenomai/testing/latest/xenomai-3.0-rc4.tar.bz2"
		  
require linux-raspberrypi.inc

do_prepare_kernel () {

    #set linux kernel source directory
    linux_src="${S}"

    #set xenomai source directory 
    xenomai_src="${TMPDIR}/work/${MACHINE}-poky-${TARGET_OS}/${PN}/${EXTENDPE}${PV}-${PR}/xenomai-3.0-rc4"

    #prepare kernel
	# arch=arm
    $xenomai_src/scripts/prepare-kernel.sh --arch=arm \
	--linux=$linux_src
	
	cd ${S}
	git add -A
	git commit -m "add xenomai files to repository"
}

addtask prepare_kernel after do_patch before do_kernel_configme
