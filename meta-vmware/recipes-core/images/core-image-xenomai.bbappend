FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://core-image-xenomai.vmx \
           file://core-image-xenomai.vmxf \
          "

DEPENDS = "zip-native"

python do_get_vmware_files () {
    bb.build.exec_func('base_do_fetch', d)
    bb.build.exec_func('base_do_unpack', d)
}
addtask do_get_vmware_files before do_rootfs

create_bundle_files () {
	cd ${WORKDIR}
	mkdir -p ${IMAGE_NAME}
	cp *.vmx* ${IMAGE_NAME}
	ln -sf ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.vmdk ${IMAGE_NAME}/core-image-xenomai.vmdk
	rm -f ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.zip
	zip -r ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.zip ${IMAGE_NAME}
}

python do_bundle_files() {
    bb.build.exec_func('create_bundle_files', d)
}

addtask bundle_files after do_vmdkimg before do_build
do_bundle_files[nostamp] = "1"
