DESCRIPTION = "Xenomai RTOS userspace libraries and tools for dual kernel (cobalt) implementation"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://include/COPYING;md5=79ed705ccb9481bf9e7026b99f4e2b0e"
SECTION = "RTOS"
HOMEPAGE = "http://www.xenomai.org/"
PR = "r0"

S = "${WORKDIR}/xenomai-${PV}"

inherit autotools

INSANE_SKIP_${PN}-dev += " libdir "
INSANE_SKIP_${PN}-dbg += " libdir "
INSANE_SKIP_${PN} += " libdir "

XENOMAI_SRC_PATH = "/usr/src/xenomai"

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN}-dbg += "/usr/xenomai/bin/.debug/* \
			/usr/xenomai/sbin/.debug/* \
			/usr/xenomai/lib/.debug/* \
			/usr/xenomai/demo/.debug/* \
			${XENOMAI_SRC_PATH}/lib/vxworks/testsuite/.debug/* \
			"

FILES_${PN}-doc += "/usr/xenomai/share/*"
			
FILES_${PN} += "/usr/xenomai/bin/* \
		/usr/xenomai/sbin/* \
		/usr/xenomai/demo/* \
		/usr/xenomai/lib/*.so.* \
		/usr/xenomai/lib/dynlist.ld \
		/usr/xenomai/lib/cobalt.wrappers \
		/usr/xenomai/etc/* \
		"
		
FILES_${PN}-dev += "/usr/xenomai/include/* \
		/usr/xenomai/lib/*.la \
		/usr/xenomai/lib/*.so \
		${XENOMAI_SRC_PATH} \
		"

FILES_${PN}-staticdev += "/usr/xenomai/include/* \
		/usr/xenomai/lib/*.a \
		"
