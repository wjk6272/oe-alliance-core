SUMMARY = "Hardware drivers for ${MACHINE_DRIVER}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PV = "${KV}+${SRCDATE}"
PR = "r0"

KV = "4.9.51"
GCCREV = "6.3.0"
SRCDATE = "20171127"

SRC_URI[md5sum] = "e89ce556261a74aaf2f35f45d7c6ba29"
SRC_URI[sha256sum] = "6b9a6ad3d0cdad10b118603c28d267bb935b781259329cbabad1b0f13526905e"

SRC_URI = "http://source.mynonpublic.com/xtrend/${MACHINE_DRIVER}-drivers-${KV}-${GCCREV}-${SRCDATE}.zip"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}
do_populate_sysroot() {
}

do_install() {
	install -d ${D}/lib/modules/${KV}/extra
	install -d ${D}/${sysconfdir}/modules-load.d
	for i in tpm modloader modloader2 dvb; do
		install -m 0755 ${WORKDIR}/$i.ko ${D}/lib/modules/${KV}/extra
		echo $i >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE_DRIVER}.conf
	done
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE_DRIVER}.conf /lib/modules/${KV}/extra"
