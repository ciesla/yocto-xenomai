DESCRIPTION = "Xenomai RTOS userspace libraries and tools for dual kernel (cobalt) implementation"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://include/COPYING;md5=60faa041c8d4a75ab87e115a9469d94d"
SECTION = "RTOS"
HOMEPAGE = "http://www.xenomai.org/"
PR = "r0"

SRC_URI = "http://download.gna.org/xenomai/testing/latest/xenomai-${PV}.tar.bz2"

SRC_URI[md5sum] = "281790d044007ddbfc62a38a51520dd5"
SRC_URI[sha256sum] = "349d257ace595b07edb3bea4f90abb2b8bdbcfc15463c99739b6d7bdad499610"

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
