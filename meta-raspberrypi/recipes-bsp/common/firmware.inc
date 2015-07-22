RPIFW_SRCREV ?= "b7bbd3d1683e9f3bb11ef86b952adee71e83862f"
RPIFW_DATE ?= "20150505"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
