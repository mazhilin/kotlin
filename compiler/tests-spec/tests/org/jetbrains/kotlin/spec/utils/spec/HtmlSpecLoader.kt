/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.spec.utils.spec

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.BufferedInputStream
import java.net.URL
import java.nio.charset.Charset

object HtmlSpecLoader {
    private const val SPEC_DOCS_TC_CONFIGURATION_ID = "Kotlin_Spec_DocsMaster"
    private const val TC_URL = "https://teamcity.jetbrains.com"
    private const val TC_PATH_PREFIX = "guestAuth/app/rest/builds"
    private const val HTML_SPEC_PATH = "/html/kotlin-spec.html"

    private fun loadRawHtmlSpec(specVersion: String, buildNumber: String): String {
        val htmlSpecLink =
            "$TC_URL/$TC_PATH_PREFIX/buildType:(id:$SPEC_DOCS_TC_CONFIGURATION_ID),number:$buildNumber,branch:default:any/artifacts/content/kotlin-spec-$specVersion-$buildNumber.zip%21$HTML_SPEC_PATH"

        return BufferedInputStream(URL(htmlSpecLink).openStream()).readBytes().toString(Charset.forName("UTF-8"))
    }

    private fun parseHtmlSpec(htmlSpecContent: String) = Jsoup.parse(htmlSpecContent).body()

    fun loadSpec(version: String): Element? {
        val specVersion = version.substringBefore("-")
        val buildNumber = version.substringAfter("-")

        return parseHtmlSpec(loadRawHtmlSpec(specVersion, buildNumber))
    }
}