FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${PR}.1"

COMPATIBLE_MACHINE_ape140x = "ape140x"



KERNEL_FEATURES_append_ape140x += " cfg/smp.scc"

SRC_URI += "file://ape140x-standard.scc \
            file://ape140x-user-config.cfg \
            file://ape140x-user-patches.scc \
            file://ape140x-user-features.scc \
           "

# uncomment and replace these SRCREVs with the real commit ids once you've had
# the appropriate changes committed to the upstream linux-yocto-xenomai repo
#SRCREV_machine_pn-linux-yocto_ape140x ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
#SRCREV_meta_pn-linux-yocto_ape140x ?= "4fd76cc4f33e0afd8f906b1e8f231b6d13b6c993"
#LINUX_VERSION = "3.10"
