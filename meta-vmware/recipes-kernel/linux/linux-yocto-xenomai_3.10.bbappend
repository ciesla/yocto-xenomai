FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${PR}.1"

COMPATIBLE_MACHINE_vmware = "vmware"



KERNEL_FEATURES_append_vmware += " cfg/smp.scc"

SRC_URI += "file://vmware-standard.scc \
            file://vmware-user-config.cfg \
            file://vmware-user-patches.scc \
            file://vmware-user-features.scc \
           "

# uncomment and replace these SRCREVs with the real commit ids once you've had
# the appropriate changes committed to the upstream linux-yocto repo
#SRCREV_machine_pn-linux-yocto_vmware ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
#SRCREV_meta_pn-linux-yocto_vmware ?= "4fd76cc4f33e0afd8f906b1e8f231b6d13b6c993"
#LINUX_VERSION = "3.10"
