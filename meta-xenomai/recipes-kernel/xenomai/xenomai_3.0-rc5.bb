DESCRIPTION = "Xenomai RTOS userspace libraries and tools for dual kernel (cobalt) implementation"
LICENSE = "GPLv1"
LIC_FILES_CHKSUM = "file://include/COPYING;md5=79ed705ccb9481bf9e7026b99f4e2b0e"
SECTION = "RTOS"
HOMEPAGE = "http://www.xenomai.org/"
PR = "r0"

require xenomai.inc

SRC_URI = "http://xenomai.org/downloads/xenomai/testing/latest/xenomai-${PV}.tar.bz2"
SRC_URI[md5sum] = "fcc997bb5c5c286e4524977d4f992cf3"
SRC_URI[sha256sum] = "8447a6cb99fe9c33deace109ae61227050ed3c35343221f964bf37f32bbfed7a"

LDFLAGS_append =" -lm"

do_configure() {
	cd ${S}

	# install xenomai source first to the temp folder - original source code of xenomai
	xenomaitmpdir=${WORKDIR}/xensrc
	if [ ! -e $xenomaitmpdir ] ; then
		install -d $xenomaitmpdir
		cp -fR * $xenomaitmpdir
		rm -fR  $xenomaitmpdir/lib/vxworks/testsuite
	fi
	
    ${S}/configure --with-core=cobalt --enable-smp --enable-pshared --build=${BUILD_SYS} --host=${HOST_SYS} --target=${TARGET_SYS}
}

do_compile () {
	cd ${S}
	make
}

do_install () {
	cd ${S}
	make DESTDIR=${D} install
	
	# remove /dev entry - it will be created later in image
	rm -fR ${D}/dev
	
	#Rename files to standard name (without host, ex i586-poky-linux)
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-clocktest ${D}/usr/xenomai/bin/clocktest
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-cmd_bits ${D}/usr/xenomai/bin/cmd_bits
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-cmd_read ${D}/usr/xenomai/bin/cmd_read
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-cmd_write ${D}/usr/xenomai/bin/cmd_write
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-dohell ${D}/usr/xenomai/bin/dohell
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-insn_bits ${D}/usr/xenomai/bin/insn_bits
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-insn_read ${D}/usr/xenomai/bin/insn_read
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-insn_write ${D}/usr/xenomai/bin/insn_write
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-latency ${D}/usr/xenomai/bin/latency
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-rtcanrecv ${D}/usr/xenomai/bin/rtcanrecv
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-rtcansend ${D}/usr/xenomai/bin/rtcansend
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-smokey ${D}/usr/xenomai/bin/smokey
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-switchtest ${D}/usr/xenomai/bin/switchtest
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-wf_generate ${D}/usr/xenomai/bin/wf_generate
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-wrap-link.sh ${D}/usr/xenomai/bin/wrap-link.sh
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-xeno ${D}/usr/xenomai/bin/xeno
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-xeno-config ${D}/usr/xenomai/bin/xeno-config
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-xeno-test ${D}/usr/xenomai/bin/xeno-test
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-xeno-test-run ${D}/usr/xenomai/bin/xeno-test-run
	mv ${D}/usr/xenomai/bin/${HOST_SYS}-xeno-test-run-wrapper ${D}/usr/xenomai/bin/xeno-test-run-wrapper

	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-analogy_calibrate ${D}/usr/xenomai/sbin/analogy_calibrate
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-analogy_config ${D}/usr/xenomai/sbin/analogy_config
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-autotune ${D}/usr/xenomai/sbin/autotune
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-corectl ${D}/usr/xenomai/sbin/corectl
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-hdb ${D}/usr/xenomai/sbin/hdb
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-nomaccfg ${D}/usr/xenomai/sbin/nomaccfg
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-rtcanconfig ${D}/usr/xenomai/sbin/rtcanconfig
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-rtcfg ${D}/usr/xenomai/sbin/rtcfg
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-rtifconfig ${D}/usr/xenomai/sbin/rtifconfig
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-rtiwconfig ${D}/usr/xenomai/sbin/rtiwconfig
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-rtnet ${D}/usr/xenomai/sbin/rtnet
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-rtping ${D}/usr/xenomai/sbin/rtping
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-rtps ${D}/usr/xenomai/sbin/rtps
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-rtroute ${D}/usr/xenomai/sbin/rtroute
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-slackspot ${D}/usr/xenomai/sbin/slackspot
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-tdmacfg ${D}/usr/xenomai/sbin/tdmacfg
	mv ${D}/usr/xenomai/sbin/${HOST_SYS}-version ${D}/usr/xenomai/sbin/version
	
	
	# install sources of xenomai from temp folder created at configure stage
	cd ${WORKDIR}/xensrc
	xenomaidir=${D}${XENOMAI_SRC_PATH}
	install -d $xenomaidir
	cp -fR * $xenomaidir
}

sysroot_stage_all_append() {
        sysroot_stage_dir ${D}${XENOMAI_SRC_PATH} ${SYSROOT_DESTDIR}${XENOMAI_SRC_PATH}
}
