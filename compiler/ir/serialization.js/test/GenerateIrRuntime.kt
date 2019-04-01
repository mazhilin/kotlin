/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.js

import com.intellij.openapi.vfs.StandardFileSystems
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.config.*
import org.jetbrains.kotlin.js.config.JSConfigurationKeys
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.serialization.js.ModuleKind

import com.intellij.openapi.Disposable
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment

fun buildConfiguration(environment: KotlinCoreEnvironment, moduleName: String): CompilerConfiguration {
    val runtimeConfiguration = environment.configuration.copy()
    runtimeConfiguration.put(CommonConfigurationKeys.MODULE_NAME, moduleName)
    runtimeConfiguration.put(JSConfigurationKeys.MODULE_KIND, ModuleKind.PLAIN)

    runtimeConfiguration.languageVersionSettings = LanguageVersionSettingsImpl(
        LanguageVersion.LATEST_STABLE, ApiVersion.LATEST_STABLE,
        specificFeatures = mapOf(
            LanguageFeature.AllowContractsForCustomFunctions to LanguageFeature.State.ENABLED,
            LanguageFeature.MultiPlatformProjects to LanguageFeature.State.ENABLED
        ),
        analysisFlags = mapOf(
            AnalysisFlags.useExperimental to listOf("kotlin.contracts.ExperimentalContracts", "kotlin.Experimental"),
            AnalysisFlags.allowResultReturnType to true
        )
    )

    return runtimeConfiguration
}

private val fullRuntimeKlibPath = "js/js.translator/testData/out/klibs/runtimeFull/"
private val defaultRuntimeKlibPath = "js/js.translator/testData/out/klibs/runtimeDefault/"
private val kotlinTestKlibPath = "js/js.translator/testData/out/klibs/kotlin.test/"

fun main() {

    val environment = KotlinCoreEnvironment.createForTests(Disposable { }, CompilerConfiguration(), EnvironmentConfigFiles.JS_CONFIG_FILES)

    fun createPsiFile(fileName: String): KtFile {
        val psiManager = PsiManager.getInstance(environment.project)
        val fileSystem = VirtualFileManager.getInstance().getFileSystem(StandardFileSystems.FILE_PROTOCOL)

        val file = fileSystem.findFileByPath(fileName) ?: error("File not found: $fileName")

        return psiManager.findFile(file) as KtFile
    }


    fun buildKlib(moduleName: String, sources: List<String>, outputPath: String, vararg dependencies: KlibModuleRef): KlibModuleRef {
        return generateKLib(
            project = environment.project,
            files = sources.map(::createPsiFile),
            configuration = buildConfiguration(environment, moduleName),
            immediateDependencies = dependencies.toList(),
            allDependencies = dependencies.toList(),
            outputKlibPath = outputPath
        )
    }

    val fullRuntime = buildKlib("JS_IR_RUNTIME", JsIrTestRuntime.FULL.sources, fullRuntimeKlibPath)
    buildKlib("JS_IR_RUNTIME", JsIrTestRuntime.DEFAULT.sources, defaultRuntimeKlibPath)
    buildKlib("kotlin.test", JsIrTestRuntime.KOTLIN_TEST.sources, kotlinTestKlibPath, fullRuntime)
}