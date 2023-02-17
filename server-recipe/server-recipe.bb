DESCRIPTION = "SERVER RECIPE"
LICENSE     = "MIT"

inherit dpkg

FILESPATH_prepend = "${THISDIR}/files:"

SRC_URI = "file://src"

S = "${WORKDIR}/src"

PROVIDES = "server-recipe"
