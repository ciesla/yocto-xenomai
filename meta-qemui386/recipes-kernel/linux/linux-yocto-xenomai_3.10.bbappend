FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${PR}.1"

COMPATIBLE_MACHINE_qemui386 = "qemui386"


KERNEL_FEATURES_append_qemui386 += " cfg/smp.scc"

SRC_URI += "file://qemui386-standard.scc \
            file://qemui386-user-config.cfg \
            file://qemui386-user-patches.scc \
            file://qemui386-user-features.scc \
           "

# uncomment and replace these SRCREVs with the real commit ids once you've had
# the appropriate changes committed to the upstream linux-yocto repo
#SRCREV_machine_pn-linux-yocto_qemui386 ?= "0143c6ebb4a2d63b241df5f608b19f483f7eb9e0"
#SRCREV_meta_pn-linux-yocto_qemui386 ?= "8f55bee2403176a50cc0dd41811aa60fcf07243c"
#LINUX_VERSION = "3.10"
