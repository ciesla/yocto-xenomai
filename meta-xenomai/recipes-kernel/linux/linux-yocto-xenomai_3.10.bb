KBRANCH ?= "standard/base"

require recipes-kernel/linux/linux-yocto.inc

# board specific branches
KBRANCH_qemuarm  ?= "standard/arm-versatile-926ejs"
KBRANCH_qemumips ?= "standard/mti-malta32"
KBRANCH_qemuppc  ?= "standard/qemuppc"
KBRANCH_qemux86  ?= "standard/common-pc/base"
KBRANCH_qemux86-64  ?= "standard/common-pc-64/base"
KBRANCH_qemumips64 ?= "standard/mti-malta64"

SRCREV_machine_qemuarm ?= "c295c53987007a5d0c87cc9b23125e9ff85900bd"
SRCREV_machine_qemumips ?= "2833be8c96671b24a203a5dc45a21c38d5d841c3"
SRCREV_machine_qemuppc ?= "cf413ddca8b974a473b72635e1499223a02d7949"
SRCREV_machine_qemux86 ?= "78afd3095c9b37efbbfbfdc25eb3833ef3c6a718"
SRCREV_machine_qemux86-64 ?= "78afd3095c9b37efbbfbfdc25eb3833ef3c6a718"
SRCREV_machine_qemumips64 ?= "3e1899546c014808fed8d336b777bf8d81f50d7b"
SRCREV_machine ?= "78afd3095c9b37efbbfbfdc25eb3833ef3c6a718"
SRCREV_meta ?= "7b3b87d4d5e4c41c235da13aaa9f45d5d338e2c6"

SRC_URI = "git://git.yoctoproject.org/linux-yocto-3.10.git;bareclone=1;branch=${KBRANCH},${KMETA};name=machine,meta"

#xenomai source (prepare_kernel.sh script)
SRC_URI += "file://ipipe-core-3.10.32-x86-6.patch"
SRC_URI += "http://xenomai.org/downloads/xenomai/testing/latest/xenomai-3.0-rc5.tar.bz2"
SRC_URI[md5sum] = "fcc997bb5c5c286e4524977d4f992cf3"
SRC_URI[sha256sum] = "8447a6cb99fe9c33deace109ae61227050ed3c35343221f964bf37f32bbfed7a"

LINUX_VERSION ?= "3.10.32"

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "meta"

COMPATIBLE_MACHINE = "qemuarm|qemux86|qemuppc|qemumips|qemumips64|qemux86-64"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append_qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append_qemux86-64=" cfg/sound.scc"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "" ,d)}"

do_prepare_kernel () {

    #set linux kernel source directory
    linux_src="${S}"

    #set xenomai source directory 
    xenomai_src="${TMPDIR}/work/${MACHINE}-poky-${TARGET_OS}/${PN}/${EXTENDPE}${PV}-${PR}/xenomai-3.0-rc5"

    #prepare kernel
	# arch=i386 (32 bits) or x86 (64 bits)
    $xenomai_src/scripts/prepare-kernel.sh --arch=i386 \
	--linux=$linux_src
	
	cd ${S}
	git add -A
	git commit -m "add xenomai files to repository"
}

addtask prepare_kernel after do_patch before do_kernel_configme
